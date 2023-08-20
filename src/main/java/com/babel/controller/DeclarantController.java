package com.babel.controller;

import com.babel.dto.DeclarantDto;
import com.babel.service.DeclarantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/declarant")
public class DeclarantController {

    @Autowired
    private DeclarantService declarantService;

    @GetMapping("/addForm")
    public String ajoutDeclarantForm(){
        return "declarant/add";
    }
    @PostMapping("/add")
    public String addDeclaration(Model model, String raisonSociale, String adresse, String email, String telephone) {
        DeclarantDto declarantDto = new DeclarantDto();
        declarantDto.setRaisonSociale(raisonSociale);
        declarantDto.setAdresse(adresse);
        declarantDto.setEmail(email);
        declarantDto.setTelephone(telephone);
        //System.out.print(declarantDto.toString());
        DeclarantDto result = declarantService.createDeclarant(declarantDto);

        if (result != null) {
            model.addAttribute("successMessage", "Declarant ajouté avec succès...");
        } else {
            model.addAttribute("errorMessage", "Une erreur est survenue lors de cette opération !!!");
        }
        return ajoutDeclarantForm();
    }
    @GetMapping("/list")
    public String getAllDeclarants(Model model) {
        List<DeclarantDto> declarants = declarantService.getAllDeclarants();
        if (!declarants.isEmpty()){
            model.addAttribute("declarants", declarants);
        }else {
            model.addAttribute("msg", "Aucun declarant n'a ete enregistre !!!");
        }
        return "declarant/list";
    }

}
