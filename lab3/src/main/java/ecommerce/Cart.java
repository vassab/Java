import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    // Додавання продукту до кошика
    public void addProduct(Product product) {
        products.add(product);
    }

    // Видалення продукту з кошика
    public void removeProduct(Product product) {
        products.remove(product);
    }

    // Отримання списку продуктів у кошику
    public List<Product> getProducts() {
        return products;
    }
}