package com.grupouno.server.controller;

import com.grupouno.server.entity.Especie;
import com.grupouno.server.entity.Personaje;
import com.grupouno.server.entity.Jugador;
import com.grupouno.server.services.EspecieService;
import com.grupouno.server.services.JugadorService;
import com.grupouno.server.services.PersonajeService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@AllArgsConstructor
public class AppRestController {
    private final JugadorService jugadorService;
    private final EspecieService especieService;
    private final PersonajeService personajeService;


    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getApi() {
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
                "Gets: " +
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
                "\n" +
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
        return jugadorService.getJugadores();
    }

    @GetMapping("/especies")
    public List<Especie> allEspecie() {
        return especieService.getEspecies();
    }

    @GetMapping("/personajes")
    public List<Personaje> allPersonajes() {
        return personajeService.getPersonajes();
    }

    @GetMapping(value = "/jugador/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jugador> getJugador(@PathVariable String id) {
        return jugadorService.getJugador(id);
    }

    @GetMapping(value = "/personaje/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personaje> getPersonaje(@PathVariable String id) {
        return personajeService.getPersonaje(id);
    }

    @GetMapping(value = "/especie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Especie> getEspecie(@PathVariable String id) {
        return especieService.getEspecie(id);
    }


    @PostMapping(value = "/jugador", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jugador> newJugador(@RequestBody Jugador jugador) {
        return jugadorService.createJugador(jugador);
    }

    @PostMapping(value = "/personaje", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personaje> newPersonaje(@RequestBody Personaje personaje) {
        return personajeService.createPersonaje(personaje);
    }

    @PostMapping(value = "/especie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Especie> newEspecie(@RequestBody Especie especie) {
        return especieService.createEspecie(especie);
    }

    @PutMapping(value = "/jugador/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jugador> updateJugador(@RequestBody Jugador newJugador, @PathVariable String id) {
        return jugadorService.updateJugador(id, newJugador);
    }

    @PutMapping(value = "/especie/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Especie> updateEspecie(@RequestBody Especie newEspecie, @PathVariable String id) {
        return especieService.updateEspecie(id, newEspecie);
    }

    @PutMapping(value = "/personaje/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personaje> updatePersonaje(@RequestBody Personaje newPersonaje, @PathVariable String id) {
        return personajeService.updatePersonaje(id, newPersonaje);
    }

    @DeleteMapping("/jugador/delete/{id}")
    public ResponseEntity<Jugador> deleteJugador(@PathVariable String id) {
        return jugadorService.deleteJugador(id);
    }

    @DeleteMapping("/personaje/delete/{id}")
    public ResponseEntity<Personaje> deletePersonaje(@PathVariable String id) {
        return personajeService.deletePersonaje(id);
    }

    @DeleteMapping("/especie/delete/{id}")
    public ResponseEntity<Especie> deleteEspecie(@PathVariable String id) {
        return especieService.deleteEspecie(id);
    }
}
