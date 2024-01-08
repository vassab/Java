import java.time.LocalDate;

public class WeatherData {

    private LocalDate date;
    private int stationId;
    private double temperature;
    private double humidity;
    private double precipitation;
    private double windSpeed;

    public WeatherData(LocalDate date, int stationId, double temperature, double humidity, double precipitation, double windSpeed) {
        this.date = date;
        this.stationId = stationId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getStationId() {
        return stationId;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "date=" + date +
                ", stationId=" + stationId +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", precipitation=" + precipitation +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
