package com.grupouno.server.services;

import com.grupouno.server.entity.Especie;
import com.grupouno.server.repository.EspecieRepository;
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
public class EspecieServiceImpl implements EspecieService{

    private final  EspecieRepository especieRepository;

    @Override
    public ResponseEntity<Especie> createEspecie(Especie especie) {
        LocalDate modf = LocalDate.now();
        //especie.setFechaModificacion(modf);
        especie.setEstadoRegistro(new BigDecimal(1));
        return new ResponseEntity<>(especieRepository.save(especie), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Especie> updateEspecie(String id, Especie newEspecie) {
        if (especieRepository.existsById(id)) {
            return new ResponseEntity<>(especieRepository.findById(id)
                    .map(especie -> {
                        especie.setNombre(newEspecie.getNombre());
                        especie.setEstadoRegistro(BigDecimal.valueOf(1));
                        especie.setFechaModificacion(LocalDate.now());
                        return especieRepository.save(especie);
                    }).get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Especie not found");
    }

    @Override
    public ResponseEntity<Especie> deleteEspecie(String id) {
        boolean existsJugadorById = especieRepository.existsById(id);
        if (existsJugadorById) {
            especieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personaje not found");
    }

    @Override
    public List<Especie> getEspecies() {
        return especieRepository.findAll();
    }

    @Override
    public ResponseEntity<Especie> getEspecie(String id) {
        if (especieRepository.existsById(id)) {
            return new ResponseEntity<>(especieRepository.findById(id).orElse(null), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Especie not found");
    }
}
