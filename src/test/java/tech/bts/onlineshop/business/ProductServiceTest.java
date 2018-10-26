package tech.bts.onlineshop.business;

import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.Product;

public class ProductServiceTest {

    public static void main(String[] args) {


        empty_catalog_has_no_products();
        add_product_to_catalog();
        product_is_available();
        product_available_quantity();

    }

    private static void empty_catalog_has_no_products() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        int count = productService.getCount();
        assertEquals(count, 0);
    }

    private static void add_product_to_catalog() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);
        int count = productService.getCount();
        //System.out.println("1 --> " + count);
        assertEquals(count, 1);
        Product p = productService.getProductById(pixelId);
        //System.out.println("pixel --> " + p.getName());
        assertEquals(p.getName(), "pixel");

    }

    private static void product_is_available() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);

        boolean availableBefore = productService.availableInStock(pixelId, 500);
        assertEquals(availableBefore, false);

        productService.addProductStock(pixelId, 500);

        boolean availableAfter = productService.availableInStock(pixelId, 500);
        assertEquals(availableAfter, true);
    }

    private static void product_available_quantity() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);

        int quantityToDeliver = productService.quantityToDeliver(pixelId, 50);
        assertEquals(quantityToDeliver, 0);
        productService.addProductStock(pixelId, 100);
        int quantityToDeliverafterMoreStock = productService.quantityToDeliver(pixelId, 50);
        assertEquals(quantityToDeliverafterMoreStock, 50);
        int quantityToDeliverbeforeMoreStock = productService.quantityToDeliver(pixelId, 200);
        assertEquals(quantityToDeliverbeforeMoreStock, 100);


    }

    /** Compares the values and display an error if they are not*/
    private static void assertEquals(Object s1, Object s2) {

        if (!s1.equals(s2)) {
            throw new RuntimeException(s1 + "is not equal to " + s2);

        }
    }


}
