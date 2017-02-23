package pl.javastart.springshop;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rysiek on 2017-02-23.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

//    private List<Product> products;
    private Map<Product, Integer> productIntegerMap;
    private BigDecimal totalPrice;

    public Cart() {
        productIntegerMap = new HashMap<>();
    }

    public void add(Product product) {
        if (!productIntegerMap.containsKey(product)){
            productIntegerMap.put(product, 1);
        } else {
            Integer amount = productIntegerMap.get(product);
            amount++;
            productIntegerMap.put(product, amount);
        }
    }

    public Map<Product, Integer> getProducts() {
        return productIntegerMap;
    }

    public BigDecimal priceCalc() {
        totalPrice = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry: productIntegerMap.entrySet()){
            totalPrice = totalPrice.add(entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())));
        }
        return totalPrice;
    }

    public void deleteProduct(Product product) {
        productIntegerMap.remove(product);
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}