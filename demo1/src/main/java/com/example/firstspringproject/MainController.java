package com.example.firstspringproject;
import com.example.firstspringproject.DbRepository;
import com.example.firstspringproject.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;






@Controller
public class MainController {
    //new MainController(new DbRepository());
    DbRepository dr;
    public MainController(DbRepository dr) {
        this.dr = dr;
    }
    @GetMapping("/products")
    public String products(Model m) {
        List<Product> lista = dr.getAllProducts();//z bazy do Javy
        m.addAttribute("lista", lista);
        return "products";//pokaz HTML
    }





    @GetMapping("")
    public String mainPage() {
        return "mainShopPage";
    }







    @GetMapping("/laptops")
    public String laptops() {
        return "laptops";
    }

    @GetMapping("/phones")
    public String phones() {
        return "phones";
    }
    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }
}
