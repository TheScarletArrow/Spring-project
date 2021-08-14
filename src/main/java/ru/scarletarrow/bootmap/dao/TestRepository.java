package ru.scarletarrow.bootmap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scarletarrow.bootmap.entity.Test;

public interface TestRepository extends JpaRepository<Test, String> {
}
