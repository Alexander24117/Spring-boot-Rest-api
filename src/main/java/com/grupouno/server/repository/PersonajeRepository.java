package com.grupouno.server.repository;

import com.grupouno.server.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface PersonajeRepository extends JpaRepository<Personaje,String> {
}
