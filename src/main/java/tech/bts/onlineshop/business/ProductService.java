package tech.bts.onlineshop.business;

import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.CartItem;
import tech.bts.onlineshop.model.Product;
import tech.bts.onlineshop.model.ShoppingCart;

public class ProductService {

    private ProductDatabase productDatabase;

    public ProductService(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public long createProduct(Product product) {
        long productId = this.productDatabase.add(product);
        return productId;
    }

    public int getCount() {
        return productDatabase.getCount();
    }

    public void addProductStock(long productId, int quantity){

        Product product = this.productDatabase.get(productId);
        int total = product.getQuantity() + quantity;
        product.setQuantity(total);
    }

    public Product getProductById(long productId) {
        Product p = productDatabase.get(productId);
        return p;

    }

    public  boolean availableInStock(long productId, int quantity ) {

        Product product = this.productDatabase.get(productId);
        return (product.getQuantity() >= quantity);
    }

    public int quantityToDeliver(long productId, int quantity) {

        Product product = this.productDatabase.get(productId);
        return Math.min(product.getQuantity(), quantity);

    }

    /**
     * Reduces the quantities of the products by the quantities in the cart
     * @param cart
     */

    public void purchase(ShoppingCart cart) {

        for (CartItem item : cart.getItems()) {
            Product product = this.productDatabase.get(item.getProductId());
            int remainingQuantity = product.getQuantity() - item.getQuantity();
            product.setQuantity(remainingQuantity);

        }
    }
}
