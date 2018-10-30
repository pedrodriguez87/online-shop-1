package tech.bts.onlineshop.business;

import org.junit.Assert;
import org.junit.Test;
import tech.bts.onlineshop.model.Discount;

import static org.junit.Assert.*;

public class DiscountServiceTest {


    @Test
    public void apply_discount() {

        DiscountService discountService = new DiscountService();
        Discount discount1 = new Discount("Apple","Summer", 50, true);
        Discount discount2 = new Discount("Summer","HOTSALES", 50, false);

        discountService.createDiscount(discount1);
        discountService.createDiscount(discount2);

        Assert.assertEquals(50, discountService.calculateFinalAmount("Apple", 100),0.0);
        Assert.assertEquals(150, discountService.calculateFinalAmount("Summer", 200),0.0);












    }

}