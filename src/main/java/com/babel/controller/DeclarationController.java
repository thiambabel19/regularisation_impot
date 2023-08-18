package com.babel.controller;
import com.babel.dto.DeclarationDto;
import com.babel.entities.Declarant;
import com.babel.helper.Helper;
import com.babel.service.DeclarantService;
import com.babel.service.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/api/declaration")
public class DeclarationController {

    @Autowired
    private DeclarationService declarationService;

    @Autowired
    private DeclarantService declarantService;

    @GetMapping("/addForm")
    public String ajoutDeclarationForm(){
        return "declaration/add";
    }
    @PostMapping("/add")
    public String addDeclaration(Model model, String dateDeclaration, double montant) {
        boolean isValidDate = Helper.isDateValid(dateDeclaration, "dd/MM/yyyy");
        if(isValidDate){
            Declarant declarant = declarantService.getDeclarant(1L); // A recuperer selon le user connecte si on integré la securite
            //System.out.print(declarant.getId());
            DeclarationDto declarationDto = new DeclarationDto();
            declarationDto.setDateDeclaration(dateDeclaration);
            declarationDto.setMontantDeclaration(montant);
            declarationDto.setDeclarant(declarant);

            DeclarationDto result = declarationService.createDeclaration(declarationDto);

            if (result != null) {
                model.addAttribute("successMessage", "Declaration ajoutée avec succès...");
            } else {
                model.addAttribute("errorMessage", "Une erreur est survenue lors de cette opération !!!");
            }

        }else{
            model.addAttribute("errorMessage", "Le format de la date est valide : 'dd/MM/yyy'.");
        }

        return ajoutDeclarationForm();
    }
    @GetMapping("/list")
    public String getAllDeclarations(Model model) {
        List<DeclarationDto> declarations = declarationService.getAllDeclarations();
        model.addAttribute("declarations", declarations);
        return "declaration/list";
    }
}
