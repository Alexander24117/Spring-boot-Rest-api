package com.grupouno.server.services;

import com.grupouno.server.entity.Personaje;
import com.grupouno.server.repository.PersonajeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonajeServiceImpl implements PersonajeService{

    private final PersonajeRepository personajeRepository;

    @Override
    public ResponseEntity<Personaje> createPersonaje(Personaje personaje) {
        LocalDate modf = LocalDate.now();
        // personaje.setFechaModificacion(modf);
        personaje.setEstadoRegistro(new BigDecimal(1));
        personaje.getEspecie().setEstadoRegistro(new BigDecimal(1));
        personaje.getJugador().setEstadoRegistro(new BigDecimal(1));
        return new ResponseEntity<>(personajeRepository.save(personaje), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Personaje> updatePersonaje(String id, Personaje newPersonaje) {
        if (personajeRepository.existsById(id)) {
            return new ResponseEntity<>(personajeRepository.findById(id)
                    .map(personaje -> {
                        personaje.setNombre(newPersonaje.getNombre());
                        personaje.setFuerza(newPersonaje.getFuerza());
                        personaje.setMana(newPersonaje.getMana());
                        personaje.setEnergia(newPersonaje.getEnergia());
                        personaje.setFechaModificacion(LocalDate.now());
                        return personajeRepository.save(personaje);
                    }).get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personaje not found");
    }

    @Override
    public ResponseEntity<Personaje> deletePersonaje(String id) {
        boolean existsJugadorById = personajeRepository.existsById(id);
        if (existsJugadorById) {
            personajeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personaje not found");
    }

    @Override
    public List<Personaje> getPersonajes() {
        return personajeRepository.findAll();
    }

    @Override
    public ResponseEntity<Personaje> getPersonaje(String id) {
        if (personajeRepository.existsById(id)) {
            return new ResponseEntity<>(personajeRepository.findById(id).get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personaje not found");
    }
}
