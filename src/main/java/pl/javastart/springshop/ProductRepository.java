package pl.javastart.springshop;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rysiek on 2017-02-21.
 */
@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new Product(1L,"Czekolada", BigDecimal.valueOf(3.25)));
        products.add(new Product(2L,"Piwo", BigDecimal.valueOf(2.59)));
        products.add(new Product(3L, "Mleko", BigDecimal.valueOf(2.98)));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product findById(Long id) {
        Product product = null;
        for (Product product1 : products) {
           if (id == product1.getId()){
               product = product1;
           }
        }
        return product;
    }
}
