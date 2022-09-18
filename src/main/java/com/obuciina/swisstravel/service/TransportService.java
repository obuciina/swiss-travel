package com.obuciina.swisstravel.service;

import com.obuciina.swisstravel.model.dto.RelationDTO;
import com.obuciina.swisstravel.model.dto.SwissResponseDTO;

public interface TransportService {

    SwissResponseDTO findConnections(RelationDTO findConnections);
}
