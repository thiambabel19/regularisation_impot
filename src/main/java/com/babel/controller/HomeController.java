package com.babel.controller;

import com.babel.entities.Declarant;
import com.babel.service.DeclarantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private DeclarantService declarantService;
    @PostMapping("login")
    public String findByEmail(Model model, String email){
        Declarant declarant = declarantService.findByEmail(email);

        if (declarant != null){
            model.addAttribute("declarantInfo", declarant);
            return homePage();
        }else {
            model.addAttribute("errorMessage", "Cette adresse email n'existe pas ...");
            return "index";
        }
    }
    @GetMapping("home")
    public String homePage(){
        return "header";
    }
}
