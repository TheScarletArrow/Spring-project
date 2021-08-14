package ru.scarletarrow.bootmap.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.scarletarrow.bootmap.entity.*;
import ru.scarletarrow.bootmap.service.EmailSenderService;
import ru.scarletarrow.bootmap.service.TestService;
import ru.scarletarrow.bootmap.service.UserServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class APIController {


    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TestService testService;

    //    @PostMapping("/mail")
//    private Object sendMail(@RequestBody Mail mail) {
//        if (mail.getTo()==(null) || mail.getBody()==(null) || mail.getSubject()==(null))
//            return new Message("One of the fields is missing");
//        else {
//            emailSenderService.sendSimpleMail(mail.getTo(), mail.getBody(), mail.getSubject());
//            return new Message("message sent");
//        }
//    }
    @SneakyThrows
    @GetMapping("/mail")
    private Object aboutMail() {
        return new Message(MESSAGE_TYPE.OK, "Required params are: " + System.lineSeparator() + Arrays.toString(Mail.class.getConstructor(String.class, String.class, String.class, List.class).getParameterTypes()));

    }

    @PostMapping("/mail")
    private Object sendMail(@RequestBody Mail mail) {
        if (mail.getTo() == (null) || mail.getBody() == (null) || mail.getSubject() == (null))
            return new Message(MESSAGE_TYPE.ERROR, "error with fields");
        else {
            if (mail.getAttachment() == null)
                emailSenderService.sendSimpleMail(mail.getTo(), mail.getBody(), mail.getSubject());
            else
                emailSenderService.sendMailWithAttachment(mail.getTo(), mail.getBody(), mail.getSubject(), mail.getAttachment());

            return new Message(MESSAGE_TYPE.OK, "message sent");
        }
    }

    @GetMapping("/users")
    private Object getUsers() {
        return userServiceImpl.getAllUsers();
    }

    @PostMapping("/users")
    private Object addUser(@RequestBody User user) {
        if (user.getName() == null || user.getMail() == null || user.getPassword() == null)
            return new Message(MESSAGE_TYPE.ERROR, "One of the fields is missing");
        else {
            try {
                return userServiceImpl.saveUser(userServiceImpl.setUser(user));
            } catch (Exception e) {
                return new Message(MESSAGE_TYPE.ERROR, "such location already reserved");
            }
        }
    }

    @PutMapping("/users/{id}")
    private Object changeUser(@RequestBody User user, @PathVariable String id) {
        var userFromDB = userServiceImpl.getUserById(id);
        var userLocationListFromInput = user.getTests();

        if (user.getTests() != null) {
            userFromDB.getTests().addAll(userLocationListFromInput);
        }
        user.setTests(userFromDB.getTests());
        user.setUuid(user.getUuid());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(userFromDB.getCreatedAt());
//       try {;}
//       catch (Exception e){
////           return new Message(MESSAGE_TYPE.ERROR, "Exception "+ e.getMessage());
//       }
        try {
            return userServiceImpl.saveUser(user);
        } catch (DataIntegrityViolationException exception) {
            return new Message(MESSAGE_TYPE.ERROR, exception.getMessage());
        }
        catch (EntityNotFoundException exception){
            return new Message(MESSAGE_TYPE.ERROR, exception.getMessage());
        }
        catch (JpaObjectRetrievalFailureException exception){
            return new Message(MESSAGE_TYPE.ERROR, exception.getMessage());
        }
    }

    @PostMapping("/users/{id}/verify")
    private Object verifyUser(@PathVariable String id) {
        var user = userServiceImpl.getUserById(id);
        if (user.getIsVerified() == 1) return new Message(MESSAGE_TYPE.ERROR, "User already verified");
        //  if (user==null) return new Message(MESSAGE_TYPE.ERROR, "User null");
        userServiceImpl.verifyUser(user);
        userServiceImpl.saveUser(user);
        return new Message(MESSAGE_TYPE.OK, "User verified");
    }

    @GetMapping("/users/{id}")
    private Object getUserById(@PathVariable String id) {
        return userServiceImpl.getUserById(id);

    }
//    @GetMapping("/locations/type-id/{id}")
//    private List<Location> showLocationsWithLocTypeId(@PathVariable int id) {
//        return locationRepository.findByTypeidEqualsAndTypeidNotNull(id);
//    }
    @GetMapping("/tests/{id}")
    private Object getTestById(@PathVariable String id){
        return testService.getTestById(id);
    }

    @GetMapping("/tests")
    private Object getAllTests(){
        return testService.getAllTests();
    }

    @PostMapping("/tests")
    private Object addTests(@RequestBody Test test){
        if (test.getLatency()==0)
            return new Message(MESSAGE_TYPE.ERROR, "One of the fields is missing or incorrect");
        else {
            try {
                return testService.saveTest(testService.setTest(test));
            } catch (Exception e) {
//                return new Message(MESSAGE_TYPE.ERROR, "such test already reserved");
                e.printStackTrace();
                return 1;
            }
        }
    }
}
