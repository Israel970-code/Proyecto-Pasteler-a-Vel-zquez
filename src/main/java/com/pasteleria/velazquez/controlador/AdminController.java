package com.pasteleria.velazquez.controlador;

import com.pasteleria.velazquez.modelo.Contacto;
import com.pasteleria.velazquez.repositorio.ContactoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private ContactoRepository contactoRepository;

    // 🔥 PANEL ADMIN (VER USUARIOS)
    @GetMapping("/admin")
    public String admin(Model model, HttpSession session) {

        Contacto usuario = (Contacto) session.getAttribute("usuario");

        if (usuario == null || !"ADMIN".equals(usuario.getRol())) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("listaUsuarios", contactoRepository.findAll());

        return "admin"; // 👈 IMPORTANTE
    }

    // 🔥 ELIMINAR USUARIO
    @GetMapping("/admin/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, HttpSession session) {

        Contacto usuario = (Contacto) session.getAttribute("usuario");

        if (usuario == null || !"ADMIN".equals(usuario.getRol())) {
            return "redirect:/login";
        }

        contactoRepository.deleteById(id);

        return "redirect:/admin";
    }

    @GetMapping("/admin/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, HttpSession session) {

        Contacto admin = (Contacto) session.getAttribute("usuario");

        if (admin == null || !"ADMIN".equals(admin.getRol())) {
            return "redirect:/login";
        }

        Contacto usuario = contactoRepository.findById(id).orElseThrow();

        model.addAttribute("usuarioEditar", usuario);

        return "formUsuario"; // 👈 IMPORTANTE
    }

    @PostMapping("/admin/guardar")
    public String guardar(@ModelAttribute Contacto usuario, HttpSession session) {

        Contacto admin = (Contacto) session.getAttribute("usuario");

        if (admin == null || !"ADMIN".equals(admin.getRol())) {
            return "redirect:/login";
        }

        contactoRepository.save(usuario);

        return "redirect:/admin";
    }
}
