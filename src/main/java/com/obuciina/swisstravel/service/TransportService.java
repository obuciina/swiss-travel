package com.obuciina.swisstravel.service;

import com.obuciina.swisstravel.model.dto.ConnectionDTO;
import com.obuciina.swisstravel.model.dto.TravelDTO;
import com.obuciina.swisstravel.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransportService {

    private final TimeUtil timeUtil;

    public TransportService(TimeUtil timeUtil) {
        this.timeUtil = timeUtil;
    }

    public ArrayList<TravelDTO> calculateTime(String from, String to) {
        String url = "http://transport.opendata.ch/v1/connections?from={from}&to={to}";


        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> uri = new HashMap<>();
        uri.put("from", from);
        uri.put("to", to);

        ConnectionDTO response = restTemplate.getForObject(url, ConnectionDTO.class, uri);
        ArrayList<TravelDTO> travelInfo = timeUtil.mapToTravel(response);
        return travelInfo;
    }

}
