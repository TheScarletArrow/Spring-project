package ru.scarletarrow.bootmap;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import ru.scarletarrow.bootmap.dao.LocationRepository;
import ru.scarletarrow.bootmap.entity.Mail;
import ru.scarletarrow.bootmap.entity.User;
import ru.scarletarrow.bootmap.service.EmailSenderService;
import ru.scarletarrow.bootmap.service.UserService;

import java.util.List;


@SpringBootTest
class BootMapApplicationTests {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserService userService;
    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    Logger LOGGER = LoggerFactory.getLogger(BootMapApplicationTests.class);



    @Test
    void contextLoads() {
        Assert.isTrue(Boolean.TRUE, String.valueOf(true));
    }

    @Test
    void checkIfNotNull() {
        Assert.noNullElements(locationRepository.findAll(), "message");
    }
    @Test
    void checkFalse(){
        Assert.isNull(locationRepository,"message");
    }

    @Test
    void testSimpleMailSend(){
        Mail mail = new Mail();
        mail.setBody("Hello world");
        mail.setSubject("test");
        mail.setTo("speed.map.scarlet@gmail.com");
        emailSenderService.sendSimpleMail(mail.getTo(), mail.getBody(), mail.getSubject());
        System.out.println("test");
    }

    @Test
    void testMailWithAttachment(){
        Mail mail = new Mail();
        mail.setBody("Hello world");
        mail.setSubject("test");
        mail.setTo("speed.map.scarlet@gmail.com");
        String attachment = "F:\\00-java.png";
        String attachment2 = "F:\\1200px-ISO_C++_Logo.png";

        //String attachment2 = "C:\\Users\\Антон\\Pictures\\D7BxFvTXsAAtnYo.jpg";
        emailSenderService.sendMailWithAttachment(mail.getTo(),  mail.getBody(),  mail.getSubject(),
                 List.of(attachment2, attachment));
        LOGGER.info("test completed");
        System.out.println("test completed");
    }


   @Test
    void test(){
           //Assertions.assertThat(locationRepository.findAll()).map(location -> location.getTypeid()!=0).first().isNotNull();
       User user = new User();

       user.setPassword("123");
       user.setName("Anton  ");
       user.setMail("vika@vika.ri");
       user.setBirthdate("2000-00-00");

       if (userService.usernameValid.test(user)) {
           LOGGER.info(user.getName() + " is valid name");
           userService.setUser(user);
       } else {
           LOGGER.error(user.getName() + " is not a valid name");
       }
//       if (!userService.userValidsUsername(user)) {
//           LOGGER.error(user.getName() + " is not a valid name");
//       } else {
//           userService.setUser(user);
//       }

       LOGGER.info(user.toString());

   }
}
