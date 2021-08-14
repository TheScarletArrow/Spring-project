package ru.scarletarrow.bootmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootMapApplication {



    public static void main(String[] args) {
        SpringApplication.run(BootMapApplication.class, args);

    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerMail() {
//        // emailSenderService.sendSimpleMail("anton20001701@yandex.ru","Hello my friend", "Hello");
//        emailSenderService.sendMailWithAttachment(
//                "anton20001701@yandex.ru",
//                "Hello my friend",
//                "Hello",
//                "C:\\Users\\Антон\\Pictures\\D7BxFvTXsAAtnYo.jpg");
//    }
}
