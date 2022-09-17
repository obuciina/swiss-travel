package com.obuciina.swisstravel.model.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class RelationDTO {

    @Valid
    @NotNull(message = "You need to provide start station")
    private String start;
    @Valid
    @NotNull(message = "You need to provide destination")
    private String destination;

}
