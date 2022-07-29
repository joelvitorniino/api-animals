package com.joelvitorniino.config;

import com.joelvitorniino.models.Monkey;
import com.joelvitorniino.repository.MonkeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private MonkeyRepository repository;

    @Override
    public void run(String... args) throws Exception {
    }
}
