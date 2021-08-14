package ru.scarletarrow.bootmap.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestServieLogger {
    public void method(){
        var name = "Anton";
        log.info("hello {}!", name);
    }
}
