package com.pgsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {PgSoftApplication.class})
public class PgSoftApplication {
    public static void main(String[] args){
        SpringApplication.run(PgSoftApplication.class,args);
    }
}
