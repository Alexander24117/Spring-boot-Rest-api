package com.grupouno.server.services;
import com.grupouno.server.entity.Jugador;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JugadorService {
    public abstract ResponseEntity<Jugador> createJugador(Jugador jugador);
    public abstract ResponseEntity<Jugador> updateJugador(String id, Jugador newJugador);
    public abstract ResponseEntity<Jugador> deleteJugador(String id);
    public abstract List<Jugador> getJugadores();
    public abstract ResponseEntity<Jugador> getJugador(String id);
}
