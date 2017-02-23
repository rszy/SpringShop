package pl.javastart.springshop;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

/**
 * Created by Rysiek on 2017-02-21.
 */
@Controller
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String productList(Model model) {
       List<Product> allProducts = productRepository.getAllProducts();
       model.addAttribute("products", allProducts);
       model.addAttribute("title", "Super produkty");
        return "productList";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "productAdd";
    }

    @PostMapping("/add")
    public String addNewProduct(Product product) {
        productRepository.addProduct(product);
        return "redirect:/products";
    }
}
