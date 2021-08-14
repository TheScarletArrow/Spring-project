package ru.scarletarrow.bootmap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scarletarrow.bootmap.entity.User;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, String> {
}
