package com.workintech.zoo.validations;

import ch.qos.logback.core.util.InvocationGate;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZooKoalaValidation {
    public static void isValid(Integer id) {
        if (id == null || id < 0) {
            throw new ZooException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkKoalaExistence(Map<Integer, Koala> koalas, Integer id, boolean existence) {
        if (existence) {
            if (!koalas.containsKey(id)) {
                throw new ZooException("Id is not exist: " + id, HttpStatus.NOT_FOUND);
            }
        } else {
            if (koalas.containsKey(id)) {
                throw new ZooException("Id is already exist: " + id, HttpStatus.BAD_REQUEST);
            }
        }
    }
}




