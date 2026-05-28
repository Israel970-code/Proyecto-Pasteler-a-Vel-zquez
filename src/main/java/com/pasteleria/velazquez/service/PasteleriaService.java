/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pasteleria.velazquez.service;

import com.pasteleria.velazquez.modelo.Contacto;
import com.pasteleria.velazquez.modelo.Pedido;
import com.pasteleria.velazquez.repositorio.ContactoRepository;
import com.pasteleria.velazquez.repositorio.PedidoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author DAW1-M
 */
@Service
public class PasteleriaService {

     private final PedidoRepository pedidoRepository;
    private final ContactoRepository contactoRepository;

    public PasteleriaService(PedidoRepository pedidoRepository, ContactoRepository contactoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.contactoRepository = contactoRepository;
    }

    public List<Pedido> listar() {

        return pedidoRepository.findAll();

    }

    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido buscar(Long id){
    return pedidoRepository.findById(id).orElseThrow();
    }
    
    public Pedido guardar(Pedido p){
    
          if (p.getFecha1() == null) {
            p.setFecha1(LocalDate.now());
            p.setFecha2(LocalDate.now().plusDays(10));
        }
        
        return pedidoRepository.save(p);
    }
    
   public List<Pedido> listarPorContacto(Contacto contacto) {
    return pedidoRepository.findByContactoId(contacto.getId());
}
    
}
