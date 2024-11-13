package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.validations.ZooKoalaValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    public Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @GetMapping()
    public List<Koala> findAllKoalas() {
        return this.koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala findKoalaById(@PathVariable("id") Integer id) {
        ZooKoalaValidation.isValid(id);
        ZooKoalaValidation.checkKoalaExistence(koalas, id, true);
        return koalas.get(id);
    }

    @PostMapping
    public Koala saveKoala(@RequestBody Koala koala) {
        ZooKoalaValidation.checkKoalaExistence(koalas, koala.getId(), false);
        koalas.put(koala.getId(), koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable int id, @RequestBody Koala koala) {
        ZooKoalaValidation.isValid(id);
        koala.setId(id);
        if(koalas.containsKey(id)) {
            koalas.put(id, koala);
            return koalas.get(id);
        } else {
            return saveKoala(koala);
        }
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable Integer id) {
        ZooKoalaValidation.isValid(id);
        ZooKoalaValidation.checkKoalaExistence(koalas, id, true);
        return koalas.remove(id);
    }
}
