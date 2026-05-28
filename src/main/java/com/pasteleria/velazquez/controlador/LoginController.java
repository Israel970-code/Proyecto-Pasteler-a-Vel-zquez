package com.pasteleria.velazquez.controlador;

import com.pasteleria.velazquez.modelo.Contacto;
import com.pasteleria.velazquez.repositorio.ContactoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private ContactoRepository contactoRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String nombre,
            @RequestParam String password,
            HttpSession session) {

        Contacto contacto = contactoRepository.findByNombre(nombre);

        if (contacto != null && password.equals(contacto.getPassword())) {
            session.setAttribute("usuario", contacto);

            if ("ADMIN".equals(contacto.getRol())) {
                return "redirect:/admin";
            } else {
                return "redirect:/lista";
            }
        }

        return "login";
    }

    @GetMapping("/registro")
    public String registroForm() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam String direccion,
            @RequestParam String password) {

        Contacto nuevo = new Contacto();
        nuevo.setNombre(nombre);
        nuevo.setEmail(email);
        nuevo.setTelefono(telefono);
        nuevo.setDireccion(direccion);
        nuevo.setPassword(password);
        nuevo.setRol("USER");

        contactoRepository.save(nuevo);

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
