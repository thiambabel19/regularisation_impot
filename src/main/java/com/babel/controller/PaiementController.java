package com.babel.controller;

import com.babel.dto.PaiementDto;
import com.babel.entities.Declaration;
import com.babel.entities.Paiement;
import com.babel.helper.Helper;
import com.babel.mapping.DeclarationMapper;
import com.babel.mapping.PaiementMapper;
import com.babel.service.DeclarationService;
import com.babel.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/paiement")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;
    @Autowired
    private PaiementMapper paiementMapper;
    @Autowired
    private DeclarationService declarationService;
    @Autowired
    private DeclarationMapper declarationMapper;

    @GetMapping("/addForm")
    public String ajoutPaiementForm(@RequestParam("id") Long declarationId, Model model){
        //System.out.print(declarationId);
        Declaration declaration = declarationService.getDeclaration(declarationId);
        model.addAttribute("declaration", declaration);
        return "paiement/add";
    }

    @PostMapping("/add")
    public String addPaiement(Model model, String datePaiement, double montantPaiement, Long id) {
        //System.out.print(id);
        boolean isValidDate = Helper.isDateValid(datePaiement, "dd/MM/yyyy");
        if (isValidDate) {
            Declaration declaration = declarationService.getDeclaration(id);

//            if (declaration.getMontantDeclaration() > montantPaiement){
//                model.addAttribute("payMessage", "Le montant à payer est inferieur à ce que vous avez saisi !!!");
//            }

            PaiementDto paiementDto = new PaiementDto();
            paiementDto.setDatePaiement(datePaiement);
            paiementDto.setMontantPaiement(montantPaiement);
            paiementDto.setDeclaration(declaration);

            PaiementDto result = paiementService.createPaiement(paiementDto);

            if (result != null) {
                List<Paiement> paiementListByDeclaration = declaration.getPaiements();
                Paiement paiement = paiementMapper.fromPaiement(paiementDto);
                paiementListByDeclaration.add(paiement);

                declarationService.updateDeclaration(declaration.getId(), declarationMapper.toDeclaration(declaration));

                model.addAttribute("successMessage", "Paiement effectué avec succès...");
            } else {
                model.addAttribute("errorMessage", "Une erreur est survenue lors de cette opération !!!");
            }
        } else {
            model.addAttribute("errorMessage", "Le format de la date est valide : 'dd/MM/yyyy'.");
        }

        return ajoutPaiementForm(id, model);
    }

    @GetMapping("/list")
    public String getAllPaiements(Model model) {
        List<PaiementDto> paiements = paiementService.getAllPaiements();
        if (!paiements.isEmpty()){
            model.addAttribute("paiements", paiements);
        }else {
            model.addAttribute("msg", "Aucun paiement n'a ete effectue !!!");
        }
        return "paiement/list";
    }

//    @GetMapping("/list")
//    public String getAllPaiementsByDeclaration(Model model) {
//        Declaration declaration = declarationService.getDeclaration(1L); // A recuperer selon le user connecte si on integré la securite
//        model.addAttribute("paiements", paiementService.getPaiementByDeclaration(declaration.getId()));
//        return "paiement/list";
//    }

}
