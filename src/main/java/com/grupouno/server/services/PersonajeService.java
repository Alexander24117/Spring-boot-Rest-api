package com.grupouno.server.services;

import com.grupouno.server.entity.Personaje;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonajeService {
    public abstract ResponseEntity<Personaje> createPersonaje(Personaje personaje);
    public abstract ResponseEntity<Personaje> updatePersonaje(String id, Personaje newPersonaje);
    public abstract ResponseEntity<Personaje> deletePersonaje(String id);
    public abstract List<Personaje> getPersonajes();
    public abstract ResponseEntity<Personaje> getPersonaje(String id);
}
