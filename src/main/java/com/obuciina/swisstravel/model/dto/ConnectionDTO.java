package com.obuciina.swisstravel.model.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ConnectionDTO {

    private ArrayList<RelationDTO> connections;
}
