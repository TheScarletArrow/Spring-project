package ru.scarletarrow.bootmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.scarletarrow.bootmap.auth.FakeAppDaoService;
import ru.scarletarrow.bootmap.service.EmailSenderService;

import java.util.Arrays;

@SpringBootApplication
public class BootMapApplication {



    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
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
