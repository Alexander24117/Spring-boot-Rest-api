package com.grupouno.server.services;

import com.grupouno.server.entity.Especie;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public interface EspecieService {

    public abstract ResponseEntity<Especie> createEspecie(Especie especie);
    public abstract ResponseEntity<Especie> updateEspecie(String id,Especie especie);
    public abstract ResponseEntity<Especie> deleteEspecie(String id);
    public abstract List<Especie> getEspecies();
    public abstract ResponseEntity<Especie> getEspecie(String id);

}
