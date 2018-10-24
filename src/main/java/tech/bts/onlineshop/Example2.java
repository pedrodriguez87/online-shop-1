package tech.bts.onlineshop;

import tech.bts.onlineshop.business.ProductService;
import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.Product;

public class Example2 {

    public static void main(String[] args) {

        ProductDatabase productDatabase = new ProductDatabase();

        ProductService productService = new ProductService(productDatabase);

        Product p1 = new Product("Iphone X","Apple",1250);
        Product p2 = new Product("Pixel 3","Google",900);



        productService.createProduct(p1);

        productService.addProductStock(1,100);
        productService.addProductStock(1,200);
        productService.addProductStock(2,150);


    }
}
