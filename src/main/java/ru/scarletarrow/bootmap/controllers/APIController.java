package ru.scarletarrow.bootmap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.scarletarrow.bootmap.dao.LocationRepository;
import ru.scarletarrow.bootmap.dao.OperatorRepository;
import ru.scarletarrow.bootmap.dao.UserRepository;
import ru.scarletarrow.bootmap.entity.*;
import ru.scarletarrow.bootmap.service.EmailSenderService;
import ru.scarletarrow.bootmap.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class APIController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/locations")
    private List<Location> showAllLocations() {
        return locationRepository.findAll();
    }

    @PostMapping("/locations")
    @ResponseBody
    private Location saveLocation(@RequestBody Location location) {
        locationRepository.save(location);
        return location;
    }


    @GetMapping("/operators")
    private List<Operator> showAllOperators() {
        return operatorRepository.findAll();
    }

//    @PostMapping("/mail")
//    private Object sendMail(@RequestBody Mail mail) {
//        if (mail.getTo()==(null) || mail.getBody()==(null) || mail.getSubject()==(null))
//            return new Message("One of the fields is missing");
//        else {
//            emailSenderService.sendSimpleMail(mail.getTo(), mail.getBody(), mail.getSubject());
//            return new Message("message sent");
//        }
//    }
@PostMapping("/mail")
private ResponseEntity<?> sendMail(@RequestBody Mail mail) {
    if (mail.getTo()==(null) || mail.getBody()==(null) || mail.getSubject()==(null))
        return ResponseEntity.badRequest().body( "One of the fields is missing");
    else {
        if (mail.getAttachment()==null)
            emailSenderService.sendSimpleMail(mail.getTo(), mail.getBody(), mail.getSubject());
        else
            emailSenderService.sendMailWithAttachment(mail.getTo(), mail.getBody(), mail.getSubject(), mail.getAttachment());

        return ResponseEntity.ok("message sent");
    }
}

@GetMapping("/user")
private Object getUsers(){
        return userService.getAllUsers();
}

    @PostMapping("/user")
    private Object addUser(@RequestBody User user){
        if(user.getName()==null||user.getMail()==null||user.getPassword()==null)
            return new Message(MESSAGE_TYPE.ERROR, "One of the fields is missing");
        else{

              return userService.saveUser(userService.setUser(user));
        }
}
//    @GetMapping("/locations/type-id/{id}")
//    private List<Location> showLocationsWithLocTypeId(@PathVariable int id) {
//        return locationRepository.findByTypeidEqualsAndTypeidNotNull(id);
//    }

}
