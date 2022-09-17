package com.obuciina.swisstravel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointDTO {

    private StationDTO station;
    private String departure;
    private String arrival;
}
