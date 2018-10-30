package tech.bts.onlineshop.business;

import org.junit.Assert;
import org.junit.Test;
import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.Product;

public class ProductServiceTest {

    @Test
    public void empty_catalog_has_no_products() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        int count = productService.getCount();
        Assert.assertEquals(0, count);
    }

    @Test
    public void add_product_to_catalog() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);
        int count = productService.getCount();
        //System.out.println("1 --> " + count);
        Assert.assertEquals(1, count);
        Product p = productService.getProductById(pixelId);
        //System.out.println("pixel --> " + p.getName());
        Assert.assertEquals(p.getName(), "pixel");
    }

    @Test
    public void product_is_available() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);

        boolean availableBefore = productService.availableInStock(pixelId, 500);
        Assert.assertEquals(false, availableBefore);

        productService.addProductStock(pixelId, 500);

        boolean availableAfter = productService.availableInStock(pixelId, 500);
        Assert.assertEquals(true, availableAfter);
    }

    @Test
    public void product_available_quantity() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);

        int quantityToDeliver = productService.quantityToDeliver(pixelId, 50);
        Assert.assertEquals(quantityToDeliver, 0);
        productService.addProductStock(pixelId, 100);
        int quantityToDeliverafterMoreStock = productService.quantityToDeliver(pixelId, 50);
        Assert.assertEquals(quantityToDeliverafterMoreStock, 50);
        int quantityToDeliverbeforeMoreStock = productService.quantityToDeliver(pixelId, 200);
        Assert.assertEquals(quantityToDeliverbeforeMoreStock, 100);

    }
}
