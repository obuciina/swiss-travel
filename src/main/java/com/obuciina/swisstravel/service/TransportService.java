package com.obuciina.swisstravel.service;

import com.obuciina.swisstravel.model.Duration;
import com.obuciina.swisstravel.model.dto.RelationDTO;
import com.obuciina.swisstravel.model.dto.SwissResponseDTO;

public interface TransportService {

    /**
     * Call external API with requested locations and calculating average time duration
     *
     * @param  findConnections  object that contains start and final location
     * @return      locations and average time duration
     * @see         SwissResponseDTO
     */
    SwissResponseDTO findConnections(RelationDTO findConnections);
}
