package com.grupouno.server.controller;

import com.grupouno.server.entity.Especie;
import com.grupouno.server.entity.Jugador;
import com.grupouno.server.entity.Personaje;
import com.grupouno.server.repository.EspecieRepository;
import com.grupouno.server.repository.JugadorRepository;
import com.grupouno.server.repository.PersonajeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class AppRestController {
    private final JugadorRepository jugadorRepository;
    private final EspecieRepository especieRepository;
    private final PersonajeRepository personajeRepository;

    public AppRestController(JugadorRepository jugadorRepository, EspecieRepository especieRepository, PersonajeRepository personajeRepository) {
        this.jugadorRepository = jugadorRepository;
        this.especieRepository = especieRepository;
        this.personajeRepository = personajeRepository;
    }
    @GetMapping(value = "/info", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getApi(){
        return "Lists: " +
                "\n" +
                "\n" +
                "jugadores: https://bi-app-postgress.herokuapp.com/app/api/jugadores" +
                "\n" +
                "especies: https://bi-app-postgress.herokuapp.com/app/api/especies" +
                "\n" +
                "personajes: https://bi-app-postgress.herokuapp.com/app/api/personajes" +
                "\n" +
                "\n" +
                "Gets: "+
                "\n" +
                "\n" +
                "jugadores: https://bi-app-postgress.herokuapp.com/app/api/jugador/" +
                "\n" +
                "especies: https://bi-app-postgress.herokuapp.com/app/api/especie/" +
                "\n" +
                "personajes: https://bi-app-postgress.herokuapp.com/app/api/personaje/" +
                "\n" +
                "\n" +
                "Updates:   " +
                "\n" +
                "\n" +
                "jugadores: https://bi-app-postgress.herokuapp.com/app/api/personaje/update/" +
                "\n" +
                "especies: https://bi-app-postgress.herokuapp.com/app/api/especie/update/" +
                "\n" +
                "personajes: https://bi-app-postgress.herokuapp.com/app/api/personaje/update/" +
                "\n" +
                "\n" +
                "Deletes:   " +
                "\n" +
                "\n" +
                "jugadores: https://bi-app-postgress.herokuapp.com/app/api/jugador/delete/" +
                "\n" +
                "especies: https://bi-app-postgress.herokuapp.com/app/api/especie/delete/" +
                "\n" +
                "personajes: https://bi-app-postgress.herokuapp.com/app/api/personaje/delete/" +
                "\n" +
                "\n"+
                "insert:   " +
                "\n" +
                "\n" +
                "jugadores: https://bi-app-postgress.herokuapp.com/app/api/jugador/" +
                "\n" +
                "especies: https://bi-app-postgress.herokuapp.com/app/api/especie/" +
                "\n" +
                "personajes: https://bi-app-postgress.herokuapp.com/app/api/personaje/" +
                "\n" +
                "\n";
    }
    @GetMapping("/jugadores")
    public List<Jugador> allJugador() {
        return jugadorRepository.findAll();
    }

    @GetMapping("/especies")
    public List<Especie> allEspecie() {
        return especieRepository.findAll();
    }

    @GetMapping("/personajes")
    public List<Personaje> allPersonajes() {
        return personajeRepository.findAll();
    }

    @GetMapping(value = "/jugador/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jugador> getJugador(@PathVariable String id) {
        Boolean sa = jugadorRepository.existsById(id);
        if (jugadorRepository.existsById(id)) {

            return new ResponseEntity<>(jugadorRepository.findById(id).get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador not found "+ id);
    }

    @GetMapping(value = "/personaje/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personaje> getPersonaje(@PathVariable String id) {
        if (personajeRepository.existsById(id)) {
            return new ResponseEntity<>(personajeRepository.findById(id).get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personaje not found");
    }

    @GetMapping(value = "/especie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Especie> getEspecie(@PathVariable String id) {
        if (especieRepository.existsById(id)) {
            return new ResponseEntity<>(especieRepository.findById(id).get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Especie not found");
    }


    @PostMapping(value = "/jugador", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jugador> newJugador(@RequestBody Jugador jugador) {
        return new ResponseEntity<>(jugadorRepository.save(jugador), HttpStatus.CREATED);
    }

    @PostMapping(value ="/personaje", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personaje> newEspecie(@RequestBody Personaje personaje) {
        return new ResponseEntity<>(personajeRepository.save(personaje), HttpStatus.CREATED);
    }

    @PostMapping(value = "/especie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Especie> newEspecie(@RequestBody Especie especie) {
        return new ResponseEntity<>(especieRepository.save(especie), HttpStatus.CREATED);
    }

    @PutMapping(value = "/jugador/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jugador> updateJugador(@RequestBody Jugador newJugador, @PathVariable String id) {
        if (jugadorRepository.existsById(id)) {
            return new ResponseEntity<>(jugadorRepository.findById(id)
                    .map(jugador -> {
                        jugador.setApodo(newJugador.getApodo());
                        jugador.setCuenta(newJugador.getCuenta());
                        jugador.setContrasena(newJugador.getContrasena());
                        jugador.setEstadoRegistro(BigDecimal.valueOf(1));
                        jugador.setFechaModificacion(newJugador.getFechaModificacion());
                        return jugadorRepository.save(jugador);
                    }).get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador not found");
    }

    @PutMapping(value = "/especie/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Especie> updateEspecie(@RequestBody Especie newEspecie, @PathVariable String id) {
        if (especieRepository.existsById(id)) {
            return new ResponseEntity<>(especieRepository.findById(id)
                    .map(especie -> {
                        especie.setNombre(newEspecie.getNombre());
                        especie.setEstadoRegistro(BigDecimal.valueOf(1));
                        especie.setFechaModificacion(newEspecie.getFechaModificacion());
                        return especieRepository.save(especie);
                    }).get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Especie not found");
    }

    @PutMapping(value = "/personaje/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personaje> updatePersonaje(@RequestBody Personaje newPersonaje, @PathVariable String id) {
        if (personajeRepository.existsById(id)) {
            return new ResponseEntity<>(personajeRepository.findById(id)
                    .map(personaje -> {
                        personaje.setNombre(newPersonaje.getNombre());
                        personaje.setFuerza(newPersonaje.getFuerza());
                        personaje.setMana(newPersonaje.getMana());
                        personaje.setEnergia(newPersonaje.getEnergia());
                        personaje.setFechaModificacion(newPersonaje.getFechaModificacion());
                        return personajeRepository.save(personaje);
                    }).get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personaje not found");
    }

    @DeleteMapping("/jugador/delete/{id}")
    public ResponseEntity<Jugador> deleteJugador(@PathVariable String id) {
        boolean existsJugadorById = jugadorRepository.existsById(id);
        if (existsJugadorById) {
            jugadorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador not found");
    }

    @DeleteMapping("/personaje/delete/{id}")
    public ResponseEntity<Personaje> deletePersonaje(@PathVariable String id) {
        boolean existsJugadorById = personajeRepository.existsById(id);
        if (existsJugadorById) {
            personajeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personaje not found");
    }
    @DeleteMapping("/especie/delete/{id}")
    public ResponseEntity<Especie> deleteEspecie(@PathVariable String id) {
        boolean existsJugadorById = especieRepository.existsById(id);
        if (existsJugadorById) {
            especieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personaje not found");
    }
}
