package com.obuciina.swisstravel.service;

import com.obuciina.swisstravel.model.dto.ConnectionDTO;
import com.obuciina.swisstravel.model.dto.DurationDTO;
import com.obuciina.swisstravel.model.dto.RelationDTO;
import com.obuciina.swisstravel.model.dto.SwissResponseDTO;
import com.obuciina.swisstravel.util.DurationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransportServiceTest {
    @Value("${swiss.base-url}")
    private String swissBaseUrl;
    private TransportService transportService;

    @Mock
    RestTemplate restTemplate;

    @Autowired
    DurationUtil durationUtil;

    @BeforeEach
    void setUp() {
        transportService = new TransportService(durationUtil);
    }

    @Test
    void testFindConnections() {
        //given
        RelationDTO relationDTO = new RelationDTO(any(), any());
        ConnectionDTO swissConnection = mockConnections();
        when(restTemplate.getForObject(any(),any(), (Map<String, ?>) any())).thenReturn(swissConnection);

        SwissResponseDTO response = transportService.findConnections(relationDTO);

        assertEquals(response.minutes(), 40);
    }

    private ConnectionDTO mockConnections() {

        DurationDTO durationOne = new DurationDTO("00d:00:30:00");
        DurationDTO durationTwo = new DurationDTO("00d:00:50:00");

        ArrayList<DurationDTO> connections = new ArrayList<>();
        connections.add(durationOne);
        connections.add(durationTwo);

        return new ConnectionDTO(connections);
    }
}
