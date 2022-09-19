package com.obuciina.swisstravel.service;

import com.obuciina.swisstravel.exception.NotFoundException;
import com.obuciina.swisstravel.model.Point;
import com.obuciina.swisstravel.model.Relation;
import com.obuciina.swisstravel.model.Station;
import com.obuciina.swisstravel.model.dto.ConnectionDTO;
import com.obuciina.swisstravel.model.dto.SwissResponseDTO;
import com.obuciina.swisstravel.util.DurationUtilImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransportServiceTest {
    @Value("${swiss.base-url}")
    private String swissBaseUrl;
    private TransportService transportService;

    @Mock
    RestTemplate restTemplate;

    @Autowired
    DurationUtilImpl durationUtil;

    @BeforeEach
    void setUp() {
        transportService = new TransportServiceImpl(durationUtil, restTemplate);
    }

    @Test
    void testFindConnections() {
        //given
        ConnectionDTO swissResponse = mockConnections();
        //when
        when(restTemplate.getForObject(any(), any(), (Map<String, ?>) any())).thenReturn(swissResponse);

        //then
        SwissResponseDTO response = transportService.findConnections("Basel", "Lausanne");

        assertEquals(response.duration().hours(), 0);
        assertEquals(response.duration().minutes(), 40);
    }

    @Test
    void testFindConnectionsNull() {
        String expectedMessage = "Unable to found connection.";
        //when
        when(restTemplate.getForObject(any(), any(), (Map<String, ?>) any())).thenReturn(null);

        //then
        Exception exception = assertThrows(NotFoundException.class, () -> transportService.findConnections("Basel", "Lausanne"));

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private ConnectionDTO mockConnections() {
        Station startStation = new Station("1", "Lausanne");
        Station destinationStation = new Station("2", "Basel, Novartis Campus");
        Point startPoint = new Point(startStation);
        Point destinationPoint = new Point(destinationStation);
        String durationOne = "00d00:30:00";
        String durationTwo = "00d00:50:00";

        ArrayList<Relation> connections = new ArrayList<>();
        connections.add(new Relation(startPoint, destinationPoint, durationOne));
        connections.add(new Relation(startPoint, destinationPoint, durationTwo));

        return new ConnectionDTO(connections);
    }

}
