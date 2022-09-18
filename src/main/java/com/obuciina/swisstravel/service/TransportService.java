package com.obuciina.swisstravel.service;

import com.obuciina.swisstravel.model.Duration;
import com.obuciina.swisstravel.model.dto.RelationDTO;
import com.obuciina.swisstravel.model.dto.SwissResponseDTO;

public interface TransportService {
    /**
     * Returns average time duration from provided duration list.
     *
     * @param  durations  an absolute URL giving the base location of the image
     * @return      the average time duration
     * @see         Duration
     */
    SwissResponseDTO findConnections(RelationDTO findConnections);
}
