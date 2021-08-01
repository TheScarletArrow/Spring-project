package ru.scarletarrow.bootmap.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.scarletarrow.bootmap.entity.Location;
import ru.scarletarrow.bootmap.entity.Mail;
import ru.scarletarrow.bootmap.service.EmailSenderService;
import ru.scarletarrow.bootmap.service.LocationService;

import java.util.List;
import java.util.function.Predicate;

@Controller
public class MainController {

    Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    @Autowired
    LocationService locationService;

    @Autowired
    private EmailSenderService emailSenderService;


    private static boolean isMailVaild(String mail){
        return mail.contains("@");
    }
    @GetMapping("/mail")
    public String writeMail(Model model){
        Mail mail = new Mail();
        model.addAttribute("mail", mail);
        return "send_mail";
    }
    @GetMapping("/mail/send")
    public String sendMail(Model model, Mail mail){
        String to = mail.getTo();
        if (!to.contains("@")) return "redirect:/mail";
        emailSenderService.sendSimpleMail(to,mail.getBody(),mail.getSubject());
        return "redirect:/mail";
    }
    @GetMapping("/locations")
    public String getAllLocations(Model model){
//        List<Location> allLocations = locationService.getAllLocations();
//        model.addAttribute("allLocations", allLocations);
//            return "locations";
        return findPaginated(1, model);
    }

    @RequestMapping("locations/addNewLocation")
    public String addNewLocation(Model model){
        Location location = new Location();
        model.addAttribute("location", location);
        return "location-info";
    }

    @PostMapping("locations/saveLocation")
    public String saveLocation(@ModelAttribute("location" ) Location location){
        locationService.saveLocation(location);
        LOGGER.info("Location with id "+ location.getId()+" saved via interface");
        return "redirect:/locations";
    }

    @RequestMapping("locations/UpdateLocation/{id}")
    public String updateLocation(@PathVariable (value = "id") int id, Model model){
        Location location = locationService.getLocationById(id);
        model.addAttribute("location", location);
        LOGGER.info(String.format("Location with id %d was shown", id));
        return "update-location";

    }

    @GetMapping("locations/DeleteLocation/{id}")
    public String deleteLocation(@PathVariable int id){
        locationService.deleteLocationById(id);
        LOGGER.info(String.format("Location with %d was deleted", id));
        return "redirect:/locations";
    }


    @GetMapping("locations/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int PageNumber, Model model){
        int pageSize = 5;

        Page<Location> locationPage = locationService.findPaginated(PageNumber, pageSize);
        List<Location> allLocations = locationPage.getContent();

        model.addAttribute("currentPage", PageNumber);
        model.addAttribute("totalPages", locationPage.getSize());
        model.addAttribute("totalLocations", locationPage.getTotalElements());
        model.addAttribute("allLocations", allLocations);
        LOGGER.info(String.format("Showing page #%d", PageNumber));
        return "locations";
    }
}
