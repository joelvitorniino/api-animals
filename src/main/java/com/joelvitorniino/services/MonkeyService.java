package com.joelvitorniino.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MonkeyService {
    public String randomPhotoMonkey() {
        String[] array = {"teste", "2", "3", "4"};
        Random r = new Random();

        String word = array[r.nextInt(array.length)];

        return word;
    }
}
