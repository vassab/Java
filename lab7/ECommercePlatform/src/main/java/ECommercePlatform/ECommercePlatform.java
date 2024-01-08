import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ECommercePlatform {
    public void displaySortedProductsByName() {
        List<Product> sortedProducts = products.values().stream()
                .sorted(new ProductComparators.NameComparator())
                .collect(Collectors.toList());

        displayProducts(sortedProducts);
    }

    public void displaySortedProductsByPrice() {
        List<Product> sortedProducts = products.values().stream()
                .sorted() // Automatically uses natural ordering (PriceComparator in Product class)
                .collect(Collectors.toList());

        displayProducts(sortedProducts);
    }

    public void displaySortedProductsByStock() {
        List<Product> sortedProducts = products.values().stream()
                .sorted(new ProductComparators.StockComparator())
                .collect(Collectors.toList());

        displayProducts(sortedProducts);
    }

    public void displayAvailableProducts() {
        List<Product> availableProducts = products.values().stream()
                .filter(product -> product.getStock() > 0)
                .collect(Collectors.toList());

        displayProducts(availableProducts);
    }

    public void displayRecommendedProducts(User user) {
        List<Product> recommendedProducts = products.values().stream()
                .limit(5) // Displaying only the first 5 recommended products
                .collect(Collectors.toList());

        System.out.println("Recommended Products:");
        displayProducts(recommendedProducts);
    }

    private void displayProducts(List<Product> products) {
        System.out.println("Product List:");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println();
    }
}
