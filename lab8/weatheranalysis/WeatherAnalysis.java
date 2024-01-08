import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherAnalysis {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        // Взаємодія з API та отримання даних
        List<WeatherData> weatherDataList = fetchDataFromApi("your_api_endpoint_here");

        // Виведення отриманих даних для перевірки
        weatherDataList.forEach(System.out::println);
    }

    private static List<WeatherData> fetchDataFromApi(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();

                // Перетворення рядка JSON у список WeatherData
                return parseJsonToWeatherDataList(content.toString());
            } else {
                System.out.println("HTTP GET request failed with response code " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<WeatherData> parseJsonToWeatherDataList(String jsonString) {
        // Перетворення рядка JSON у список WeatherData
        return List.of(gson.fromJson(jsonString, WeatherData[].class));
    }

    private static List<WeatherData> findHottestStations(List<WeatherData> weatherDataList, int count) {
        // Знайти найгарячіші станції за середньою температурою
        return weatherDataList.stream()
                .collect(Collectors.groupingBy(WeatherData::getStationId,
                        Collectors.averagingDouble(WeatherData::getTemperature)))
                .entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
                .limit(count)
                .flatMap(entry -> weatherDataList.stream()
                        .filter(data -> data.getStationId().equals(entry.getKey())))
                .collect(Collectors.toList());
    }

    private static List<WeatherData> findColdestStations(List<WeatherData> weatherDataList, int count) {
        // Знайти найхолодніші станції за середньою температурою
        return weatherDataList.stream()
                .collect(Collectors.groupingBy(WeatherData::getStationId,
                        Collectors.averagingDouble(WeatherData::getTemperature)))
                .entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByValue())
                .limit(count)
                .flatMap(entry -> weatherDataList.stream()
                        .filter(data -> data.getStationId().equals(entry.getKey())))
                .collect(Collectors.toList());
    }

    private static List<WeatherData> findMostHumidStations(List<WeatherData> weatherDataList, int count) {
        // Знайти найвологіши станції за середнім рівнем опадів
        return weatherDataList.stream()
                .collect(Collectors.groupingBy(WeatherData::getStationId,
                        Collectors.averagingDouble(WeatherData::getHumidity)))
                .entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
                .limit(count)
                .flatMap(entry -> weatherDataList.stream()
                        .filter(data -> data.getStationId().equals(entry.getKey())))
                .collect(Collectors.toList());
    }

    private static List<WeatherData> findStationsWithContinuousRain(List<WeatherData> weatherDataList, int days) {
        // Знайти станції, на яких було більше 7 послідовних днів опадів
        return IntStream.range(0, weatherDataList.size() - days + 1)
                .filter(i -> weatherDataList.subList(i, i + days)
                        .stream().allMatch(data -> data.getPrecipitation() > 0))
                .mapToObj(i -> weatherDataList.get(i))
                .collect(Collectors.toList());
    }

    private static List<WeatherData> findStationsWithTemperatureIncrease(List<WeatherData> weatherDataList, int days, double temperatureIncrease) {
        // Знайти станції, на яких температура зросла на щонайменше 5°C протягом 5 послідовних днів
        return IntStream.range(0, weatherDataList.size() - days + 1)
                .filter(i -> weatherDataList.subList(i, i + days)
                        .stream()
                        .mapToDouble(WeatherData::getTemperature)
                        .average()
                        .orElse(0) - weatherDataList.get(i).getTemperature() >= temperatureIncrease)
                .mapToObj(i -> weatherDataList.get(i))
                .collect(Collectors.toList());
    }

    private static void calculateMonthlyAverages(List<WeatherData> weatherDataList) {
        // Розрахувати середню глобальну температуру, вологість та рівень опадів для кожного місяця
        Map<Month, Double> averageTemperatureByMonth = weatherDataList.stream()
                .collect(Collectors.groupingBy(data -> data.getDate().getMonth(),
                        Collectors.averagingDouble(WeatherData::getTemperature)));

        Map<Month, Double> averageHumidityByMonth = weatherDataList.stream()
                .collect(Collectors.groupingBy(data -> data.getDate().getMonth(),
                        Collectors.averagingDouble(WeatherData::getHumidity)));

        Map<Month, Double> averagePrecipitationByMonth = weatherDataList.stream()
                .collect(Collectors.groupingBy(data -> data.getDate().getMonth(),
                        Collectors.averagingDouble(WeatherData::getPrecipitation)));

        // Вивести результати
        System.out.println("Average Temperature by Month: " + averageTemperatureByMonth);
        System.out.println("Average Humidity by Month: " + averageHumidityByMonth);
        System.out.println("Average Precipitation by Month: " + averagePrecipitationByMonth);
    }

    private static void findMonthWithHighestWindSpeed(List<WeatherData> weatherDataList) {
        // Знайти місяць з найвищою середньою швидкістю вітру
        Map<Month, Double> averageWindSpeedByMonth = weatherDataList.stream()
                .collect(Collectors.groupingBy(data -> data.getDate().getMonth(),
                        Collectors.averagingDouble(WeatherData::getWindSpeed)));

        // Знайти місяць з найвищою середньою швидкістю вітру
        Month monthWithHighestWindSpeed = averageWindSpeedByMonth.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        // Вивести результат
        System.out.println("Month with Highest Wind Speed: " + monthWithHighestWindSpeed);
    }
}
