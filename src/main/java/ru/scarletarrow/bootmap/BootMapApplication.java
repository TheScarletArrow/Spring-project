package ru.scarletarrow.bootmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.scarletarrow.bootmap.auth.FakeAppDaoService;

@SpringBootApplication
public class BootMapApplication {

    public static void main(String[] args) {

        SpringApplication.run(BootMapApplication.class, args);


    }

}
