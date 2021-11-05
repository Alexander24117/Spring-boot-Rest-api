package com.grupouno.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }


    @PostConstruct
    public void setUp(){
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
