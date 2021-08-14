package ru.scarletarrow.bootmap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.scarletarrow.bootmap.dao.UserRepository;
import ru.scarletarrow.bootmap.entity.MESSAGE_TYPE;
import ru.scarletarrow.bootmap.entity.Message;
import ru.scarletarrow.bootmap.entity.User;

import java.util.*;
import java.util.function.Predicate;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
            private LocationService locationService;
    Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Object getAllUsers() {
        var list = userRepository.findAll();
        LOGGER.info("Getting all Users...");
        if (list.size() == 0) {
            LOGGER.info("No users got");
            return new Message(MESSAGE_TYPE.ERROR, "No users are in DB");}
        else {
            LOGGER.info(String.format("Got %d users", list.size()));
            return list;}
    }

    private static final Predicate<Character> isLetter = Character::isLetter;
    private static final Predicate<Character> isWhiteSpace = character -> character == ' ';
    private static final Predicate<Character> isNotDigit = character -> !Character.isDigit(character);

    public Predicate<User> usernameValid = user ->
    {
        char[] chars = user.getName().toCharArray();
        List<Boolean> booleans = new LinkedList<>();

        for (char aChar : chars) {
            booleans.add(isNotDigit.and(isLetter.or(isWhiteSpace)).test(aChar));
        }

        return !booleans.contains(false);
    };


    //    public boolean userValidsUsername(User user){
//        for (Character ch:user.getName().toCharArray()  ) {
//                if (  isLetter.or(isWhiteSpace).test(ch)    )
//                return  true;
//        }
//        return false;
//    }

    @Override
    public void verifyUser(User user){
        user.setIsVerified(1);
    }

    @Override
    public User getUserById(String id){
        var optionalUser =userRepository.findById(id);
        User user;
        if (optionalUser.isPresent())
             user= optionalUser.get();
        else throw new IllegalArgumentException("EXCEPTION");

        return user;
    }
    @Override
    public User setUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(new Date(System.currentTimeMillis()).toString());
        user.setUuid(UUID.randomUUID().toString());

        return user;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
