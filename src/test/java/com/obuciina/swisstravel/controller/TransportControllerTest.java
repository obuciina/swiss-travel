package com.obuciina.swisstravel.controller;

import com.obuciina.swisstravel.service.TransportService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TransportControllerTest {

    @MockBean
    TransportService transportService;
}
