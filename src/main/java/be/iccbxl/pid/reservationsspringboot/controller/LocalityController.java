package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.List;

import be.iccbxl.pid.reservationsspringboot.model.Locality;
import be.iccbxl.pid.reservationsspringboot.service.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LocalityController {

    @Autowired
    LocalityService service;

    // Liste de toutes les localités
    @GetMapping("/localities")
    public String index2(Model model) {
        List<Locality> localities = service.getAll();
        model.addAttribute("localities", localities);
        model.addAttribute("title", "Liste des localités");
        return "locality/index";
    }

    // Afficher une localité par ID
    @GetMapping("/localities/{id}")
    public String show(Model model, @PathVariable("id") Long id) {
        Locality locality = service.getById(id).orElse(null);

        if (locality == null) {
            model.addAttribute("error", "La localité spécifiée n'existe pas.");
            return "error/404";
        }

        model.addAttribute("locality", locality);
        model.addAttribute("title", "Fiche d'une localité");
        return "locality/show";
    }
}
