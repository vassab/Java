import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ShoppingCartTest {
    private Product product1;
    private Product product2;
    private Cart cart;

    @Before
    public void setUp() {
        product1 = new Product(1, "Product 1", 10.0);
        product2 = new Product(2, "Product 2", 15.0);
        cart = new Cart();
    }

    @Test
    public void testAddProductToCart() {
        cart.addProduct(product1);
        cart.addProduct(product2);

        List<Product> productsInCart = cart.getProducts();
        assertTrue(productsInCart.contains(product1));
        assertTrue(productsInCart.contains(product2));
    }

    @Test
    public void testRemoveProductFromCart() {
        cart.addProduct(product1);
        cart.addProduct(product2);

        cart.removeProduct(product1);

        List<Product> productsInCart = cart.getProducts();
        assertFalse(productsInCart.contains(product1));
        assertTrue(productsInCart.contains(product2));
    }

    @Test
    public void testCreateOrderFromCart() {
        cart.addProduct(product1);
        cart.addProduct(product2);

        List<Product> productsInCart = cart.getProducts();
        assertEquals(2, productsInCart.size());

        // Створення замовлення
        Order order = new Order(1, productsInCart, "New");
        assertEquals(2, order.getProducts().size());
        assertEquals("New", order.getStatus());
    }

    @Test
    public void testUpdateOrderStatus() {
        Order order = new Order(1, null, "New");
        assertEquals("New", order.getStatus());

        order.setStatus("Shipped");
        assertEquals("Shipped", order.getStatus());
    }
}