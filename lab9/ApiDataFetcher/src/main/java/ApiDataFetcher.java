import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiDataFetcher {

    private static final String API_KEY = "43a0cef4dd3b4c818a5328154582ef5d";
    private static final String API_URL = "http://api.recrm.ru/doc";

    public static void main(String[] args) {
        // Отримати дані з API
        String apiData = fetchDataFromApi(API_URL);

        // Зберегти отримані дані у форматі Excel
        saveDataToExcel(apiData, "output.xlsx");
    }

    private static String fetchDataFromApi(String apiUrl) {
        StringBuilder apiResponse = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("api-key", API_KEY);

            // Отримати відповідь від API
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    apiResponse.append(line);
                }

                reader.close();
            } else {
                System.out.println("HTTP Request Failed with error code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return apiResponse.toString();
    }


    private static void saveDataToExcel(String data, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            // Створити лист у робочій книзі
            Sheet sheet = workbook.createSheet("API Data");

            // Зберегти робочу книгу
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void saveDataToExcel(List<String> data) {
        String excelFilePath = "output.xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("ApiData");

            // Створити заголовок
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < data.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(data.get(i));
            }

            // Записати дані
            for (int rowIndex = 1; rowIndex <= data.size(); rowIndex++) {
                Row row = sheet.createRow(rowIndex);
                Cell cell = row.createCell(0);
                cell.setCellValue(data.get(rowIndex - 1));
            }

            // Зберегти Excel-файл
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
            }

            System.out.println("Data saved to Excel successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Взаємодія з API та отримання даних
        List<String> apiDataList = fetchDataFromApi("your_api_endpoint_here");

        // Зберегти отримані дані у форматі Excel
        saveDataToExcel(apiDataList);
    }
}
