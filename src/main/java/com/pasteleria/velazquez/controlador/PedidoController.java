/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pasteleria.velazquez.controlador;

import com.pasteleria.velazquez.modelo.Contacto;
import com.pasteleria.velazquez.modelo.Pedido;
import com.pasteleria.velazquez.modelo.TipoProducto;
import com.pasteleria.velazquez.repositorio.PedidoRepository;
import com.pasteleria.velazquez.service.PasteleriaService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DAW1-M
 */
@Controller
public class PedidoController {

    private final PasteleriaService pastService;

    public PedidoController(PasteleriaService pastService) {
        this.pastService = pastService;
    }

    @GetMapping("/lista")
    public String listar(Model model, HttpSession session) {

        Contacto contacto = (Contacto) session.getAttribute("usuario");

        if (contacto == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", contacto);
        model.addAttribute("listapedidos",
                pastService.listarPorContacto(contacto));

        return "lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        pastService.eliminar(id);
        return "redirect:/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, HttpSession session) {

        Contacto contacto = (Contacto) session.getAttribute("usuario");

        if (contacto == null) {
            return "redirect:/login";
        }

        Pedido pedido = pastService.buscar(id);

        // Si NO es admin, solo puede editar lo suyo
        if (!"ADMIN".equals(contacto.getRol())) {
            if (!pedido.getContacto().getId().equals(contacto.getId())) {
                return "redirect:/lista";
            }
        }

        model.addAttribute("pedido", pedido);
        model.addAttribute("tipos", TipoProducto.values());
        model.addAttribute("modoEdicion", true);

        // 🔥 FALTABA ESTO
        model.addAttribute("usuario", contacto);

        return "form";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model, HttpSession session) {

        Contacto usuario = (Contacto) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("pedido", new Pedido());
        model.addAttribute("tipos", TipoProducto.values()); // 🔥 ESTO ES LO QUE FALTA
        model.addAttribute("usuario", usuario);

        return "form";
    }

    @PostMapping("/guardar")
    public String guardar(Pedido pedido, HttpSession session) {

        Contacto contacto = (Contacto) session.getAttribute("usuario");

        if (contacto == null) {
            return "redirect:/login";
        }

        pedido.setContacto(contacto);

        pastService.guardar(pedido);

        return "redirect:/lista";
    }

    @PostMapping("/guardarPedido")
    public String guardarPedido(Pedido pedido, HttpSession session) {

        Contacto contacto = (Contacto) session.getAttribute("usuario");

        if (contacto == null) {
            return "redirect:/login";
        }

        pedido.setContacto(contacto);

        pastService.guardar(pedido); // 👈 CORRECTO

        return "redirect:/lista";
    }

}