package com.grupouno.server.services;

import com.grupouno.server.entity.Jugador;
import com.grupouno.server.repository.JugadorRepository;
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
public class JugadorServiceImpl implements JugadorService{
    private final JugadorRepository jugadorRepository;
    @Override
    public ResponseEntity<Jugador> createJugador(Jugador jugador) {
        jugador.setEstadoRegistro(new BigDecimal(1));
        // jugador.setFechaModificacion(LocalDate.now());
        return new ResponseEntity<>(jugadorRepository.save(jugador), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Jugador> updateJugador(String id, Jugador newJugador) {
        if (jugadorRepository.existsById(id)) {
            return new ResponseEntity<>(jugadorRepository.findById(id)
                    .map(jugador -> {
                        jugador.setApodo(newJugador.getApodo());
                        jugador.setCuenta(newJugador.getCuenta());
                        jugador.setContrasena(newJugador.getContrasena());
                        jugador.setEstadoRegistro(BigDecimal.valueOf(1));
                        jugador.setFechaModificacion(LocalDate.now());
                        return jugadorRepository.save(jugador);
                    }).get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador not found");
    }

    @Override
    public ResponseEntity<Jugador> deleteJugador(String id) {
        boolean existsJugadorById = jugadorRepository.existsById(id);
        if (existsJugadorById) {
            jugadorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador not found");
    }

    @Override
    public List<Jugador> getJugadores() {
        return jugadorRepository.findAll();
    }

    @Override
    public ResponseEntity<Jugador> getJugador(String id) {
        Boolean sa = jugadorRepository.existsById(id);
        if (jugadorRepository.existsById(id)) {

            return new ResponseEntity<>(jugadorRepository.findById(id).get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador not found "+ id);
    }


}
