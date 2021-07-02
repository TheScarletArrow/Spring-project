package ru.scarletarrow.bootmap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.scarletarrow.bootmap.dao.LocationRepository;

@SpringBootTest
class BootMapApplicationTests {

    @Autowired
    LocationRepository locationRepository;

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
}
