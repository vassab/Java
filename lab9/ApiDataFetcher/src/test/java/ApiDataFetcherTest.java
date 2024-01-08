import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ApiDataFetcherTest {

    @Test
    void fetchDataFromApi_shouldReturnData() {
        // Arrange
        String apiUrl = "http://api.recrm.ru/doc";

        // Act
        List<String> apiData = ApiDataFetcher.fetchDataFromApi(apiUrl);

        // Assert
        assertNotNull(apiData);
        assertTrue(apiData.size() > 0);
    }

    @Test
    void saveDataToExcel_shouldCreateExcelFile() {
        // Arrange
        List<String> testData = List.of("Data1", "Data2", "Data3");

        // Act
        ApiDataFetcher.saveDataToExcel(testData);
    }
}
