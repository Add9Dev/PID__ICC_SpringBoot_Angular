package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.List;

import be.iccbxl.pid.reservationsspringboot.model.Role;
import be.iccbxl.pid.reservationsspringboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoleController {

    @Autowired
    RoleService service;

    // Récupérer tous les rôles
    @GetMapping("/roles")
    public String index2(Model model) {
        List<Role> roles = service.getAll();

        model.addAttribute("roles", roles);
        model.addAttribute("title", "Liste des roles");

        return "role/index";
    }

    // Récupérer un rôle par ID
    @GetMapping("/roles/{id}")
    public String show(Model model, @PathVariable("id") Long id) {
        Role role = service.getRoleById(id);

        if (role == null) {
            model.addAttribute("error", "Le rôle spécifié n'existe pas.");
            return "error/404";
        }

        model.addAttribute("role", role);
        model.addAttribute("title", "Fiche d'un rôle");

        return "role/show";
    }
}
