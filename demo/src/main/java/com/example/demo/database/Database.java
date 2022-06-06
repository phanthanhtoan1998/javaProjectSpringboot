package com.example.demo.database;

import com.example.demo.models.Product;
import com.example.demo.models.ResponseObject;
import com.example.demo.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database  {

    private  static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){
        return new CommandLineRunner() {
            //logger

            @Override
            public void run(String... args) throws Exception {


//                Product productA = new Product("2",2022,399,"") ;
//                Product productB = new Product("2",2022,399,"") ;
//                logger.info();

            }
        };
    }
}
