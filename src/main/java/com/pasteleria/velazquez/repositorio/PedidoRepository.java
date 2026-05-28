/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pasteleria.velazquez.repositorio;


import com.pasteleria.velazquez.modelo.Contacto;
import com.pasteleria.velazquez.modelo.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DAW1-M
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findByContactoId(Integer contactoId);
}
