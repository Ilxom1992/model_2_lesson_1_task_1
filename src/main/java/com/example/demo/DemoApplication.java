package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
//        Company malumotlarini service, controller yordamida ResposeEntity qaytaradigan to'liq REST full API yozing.
//        Bunda Address(street, homeNumber) Company(corpName, directorName, Address) Department(name, Company)
//        Worker(name, phoneNumber, Address, Department)
//        malumotlari bo'lsin. Proyektni git ga yuklab, javob sifatida linkni yuboring.
