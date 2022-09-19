package com.obuciina.swisstravel.service;

import com.obuciina.swisstravel.model.dto.SwissResponseDTO;

public interface TransportService {

    /**
     * Call external API with requested locations and calculating average time duration
     *
     * @param start       a string that represent start station
     * @param destination a sting that represent final destination
     * @return locations and average time duration
     * @see SwissResponseDTO
     */
    SwissResponseDTO findConnections(String start, String destination);
}
