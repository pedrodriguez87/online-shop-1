package tech.bts.onlineshop;

import tech.bts.onlineshop.business.ProductService;
import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.CartItem;
import tech.bts.onlineshop.model.Product;
import tech.bts.onlineshop.model.ShoppingCart;

public class Example2 {

    public static void main(String[] args) {

        ProductDatabase productDatabase = new ProductDatabase();

        ProductService productService = new ProductService(productDatabase);
        long iPhoneId = productService.createProduct(new Product("iPhone XS ","Apple", 1250));
        long pixelId = productService.createProduct(new Product("Pixel 3 ","Google", 900));

        productService.addProductStock(iPhoneId, 100);
        productService.addProductStock(iPhoneId, 200);
        productService.addProductStock(pixelId, 150);

        Product p = productService.getProductById(iPhoneId);
        System.out.println("there are : " + p.getQuantity() + " units of " + p.getName() + "in stock");

        boolean b = productService.availableInStock(1,310);
        System.out.println("Is it possible to buy the amount of products? " + b);

        int i = productService.quantityToDeliver(1,310);
        System.out.println("You can only buy: " + i + " products");

        ShoppingCart cart = new ShoppingCart();

        cart.add(new CartItem(iPhoneId, 20));
        cart.add(new CartItem(pixelId, 50));

        productService.purchase(cart);
        Product iPhone = productService.getProductById(iPhoneId);
        System.out.println("The amount of iphones available is: " + iPhone.getQuantity());

        Product pixel = productService.getProductById(pixelId);
        System.out.println("The amount of pixel available is: " + pixel.getQuantity());


    }
}
