package com.obuciina.swisstravel.service;

import com.obuciina.swisstravel.model.Point;
import com.obuciina.swisstravel.model.Relation;
import com.obuciina.swisstravel.model.Station;
import com.obuciina.swisstravel.model.dto.ConnectionDTO;
import com.obuciina.swisstravel.model.dto.RelationDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

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
        transportService = new TransportServiceImpl(durationUtil);
    }

    @Test
    void testFindConnections() {
        //given
        RelationDTO relationDTO = new RelationDTO(any(), any());
        ConnectionDTO swissConnection = mockConnections();
        // when(restTemplate.getForObject(any(),any(), (Map<String, ?>) any())).thenReturn(swissConnection);

        SwissResponseDTO response = transportService.findConnections(relationDTO);

        assertEquals(response.duration().minutes(), 40);
    }

    private ConnectionDTO mockConnections() {
        Station startStation = new Station("1", "Lausanne");
        Station destinationStation = new Station("2", "Basel, Novartis Campus");
        Point startPoint = new Point(startStation);
        Point destinationPoint = new Point(destinationStation);
        String durationOne = "00d:00:30:00";
        String durationTwo = "00d:00:50:00";

        ArrayList<Relation> connections = new ArrayList<>();
        connections.add(new Relation(startPoint, destinationPoint, durationOne));
        connections.add(new Relation(startPoint, destinationPoint, durationTwo));

        return new ConnectionDTO(connections);
    }
}
