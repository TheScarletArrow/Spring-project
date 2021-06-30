package ru.scarletarrow.bootmap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class BootMapApplicationTests {

    @Test
    void contextLoads() {
        Assert.isTrue(Boolean.TRUE, String.valueOf(true));
    }

}
