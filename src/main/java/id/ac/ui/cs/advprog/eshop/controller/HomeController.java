package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping("")
    public String homePage(Model model)
    {
        return "HomePage";
    }
}
