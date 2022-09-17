package com.obuciina.swisstravel.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class TravelDTO {

    private StationDTO start;
    private StationDTO destination;
    private LocalTime duration;
}
