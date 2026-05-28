package com.pasteleria.velazquez.repositorio;

import com.pasteleria.velazquez.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ContactoRepository extends JpaRepository<Contacto, Integer> {

Contacto findByNombre(String nombre);}