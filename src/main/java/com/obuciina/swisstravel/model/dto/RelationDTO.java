package com.obuciina.swisstravel.model.dto;

import lombok.Data;

@Data
public class RelationDTO {

    private PointDTO from;
    private PointDTO to;
    private String duration;

}
