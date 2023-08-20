package com.babel.controller;
import com.babel.dto.DeclarationDto;
import com.babel.entities.Declarant;
import com.babel.entities.Declaration;
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
    public String ajoutDeclarationForm(Model model){
        model.addAttribute("declarants", declarantService.getAllDeclarants());
        return "declaration/add";
    }
    @PostMapping("/add")
    public String addDeclaration(Model model, String dateDeclaration, double montant, Long declarant_id) {
        boolean isValidDate = Helper.isDateValid(dateDeclaration, "dd/MM/yyyy");
        if(isValidDate){
            Declarant declarant = declarantService.getDeclarant(declarant_id);
            System.out.print(declarant_id);
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
            model.addAttribute("dateMessage", "Le format de la date est valide : 'dd/MM/yyy'.");
        }

        return ajoutDeclarationForm(model);
    }

    @GetMapping("/list")
    public String getDeclarationNonReglee(Model model) {
        List<Declaration> declarations = declarationService.getDeclarationNonReglee();

        if (!declarations.isEmpty()){
            model.addAttribute("declarations", declarations);
        }else{
            model.addAttribute("errorMessage", "Pas de declaration a paye ...");
        }

        return "declaration/list";
    }

}
