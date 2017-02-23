package pl.javastart.springshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rysiek on 2017-02-23.
 */
@Controller
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Cart cart;


    @GetMapping("/cart")
    public String showCart(Model model){
        Map<Product, Integer> showCart = cart.getProducts();
        BigDecimal totalPrice = cart.getTotalPrice();
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", showCart);
        model.addAttribute("title", "Twój koszyk");
        return "productCart";
    }


    @GetMapping("/cart/add")
    public String addToCart(@RequestParam("id") Long id) {
        Product productToAdd = productRepository.findById(id);
        cart.add(productToAdd);
        cart.priceCalc();
        return "redirect:/cart";
    }

    @GetMapping("/cart/delete")
    public String deleteFromCart(@RequestParam("id") Long id) {
        Product productToDelete = productRepository.findById(id);
        cart.deleteProduct(productToDelete);
        cart.priceCalc();
        return "redirect:/cart";
    }
}
