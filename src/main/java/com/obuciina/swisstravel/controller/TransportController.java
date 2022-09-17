package com.obuciina.swisstravel.controller;

import com.obuciina.swisstravel.model.dto.TravelDTO;
import com.obuciina.swisstravel.service.TransportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class TransportController {

    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @GetMapping("/connections")
    public ResponseEntity<ArrayList<TravelDTO>> getTime(@RequestParam("from") String startPoint, @RequestParam("to") String destination) {

        ArrayList<TravelDTO> info = transportService.calculateTime(startPoint, destination);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(info);
    }
}
