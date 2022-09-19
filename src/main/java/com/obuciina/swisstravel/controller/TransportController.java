package com.obuciina.swisstravel.controller;


import com.obuciina.swisstravel.model.dto.RelationDTO;
import com.obuciina.swisstravel.model.dto.SwissResponseDTO;
import com.obuciina.swisstravel.service.TransportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Swiss Transport API v1")
public class TransportController {

    private static final Logger logger = LoggerFactory.getLogger(TransportController.class);
    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @ApiOperation(value = "Find average time duration between to station",
            notes = "Provide name of start and final destination to get time duration",
            response = SwissResponseDTO.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Ok - Time duration is calculated"),
                    @ApiResponse(code = 404, message = "Not Found - Relation for locations not found"),
                    @ApiResponse(code = 500, message = "Internal Server Error - An unexpected error occured")
            }
    )
    @GetMapping("/connections")
    public ResponseEntity<?> getDuration(@RequestBody @Valid RelationDTO relationDTO) {
        logger.info("Start finding time duration between {} and {}", relationDTO.start(), relationDTO.destination());
        return ResponseEntity.status(HttpStatus.OK).body(transportService.findConnections(relationDTO));
    }

}
