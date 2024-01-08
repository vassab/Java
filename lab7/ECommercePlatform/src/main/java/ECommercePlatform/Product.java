public class Product implements Comparable<Product> {
    private Integer id;
    private String name;
    private double price;
    private int stock;

    // Конструктор без параметрів
    public Product() {
    }

    // Конструктор з параметрами
    public Product(Integer id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Гетери та сетери
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Перевизначений метод toString для зручного виводу
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    // Реалізація Comparable для сортування за ціною
    @Override
    public int compareTo(Product otherProduct) {
        return Double.compare(this.price, otherProduct.getPrice());
    }
}
