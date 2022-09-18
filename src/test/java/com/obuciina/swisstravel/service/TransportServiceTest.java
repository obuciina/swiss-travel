package com.obuciina.swisstravel.service;

import com.obuciina.swisstravel.util.DurationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransportServiceTest {

    private TransportService transportService;

    @Autowired
    DurationUtil durationUtil;

    void setUp(){
        transportService = new TransportService(durationUtil);
    }


}
