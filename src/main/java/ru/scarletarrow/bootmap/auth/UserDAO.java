package ru.scarletarrow.bootmap.auth;

import org.springframework.context.annotation.Bean;
import ru.scarletarrow.bootmap.auth.AppUser;

import java.util.Optional;
public interface UserDAO {

    public Optional<AppUser> selectAppUserByUserName(String username);

}
