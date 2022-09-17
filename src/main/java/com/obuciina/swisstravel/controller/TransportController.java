package com.obuciina.swisstravel.controller;


import com.obuciina.swisstravel.exception.NotFoundException;
import com.obuciina.swisstravel.model.dto.RelationDTO;
import com.obuciina.swisstravel.service.TransportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class TransportController {

    private static final Logger logger = LoggerFactory.getLogger(TransportController.class);
    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @GetMapping("/connections")
    public ResponseEntity<String> getDuration(@RequestBody @Valid RelationDTO relationDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(transportService.findConnections(relationDTO));
        } catch (NotFoundException ex) {
            logger.error("Unable to find direct relation between locations.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
