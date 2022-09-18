package com.obuciina.swisstravel.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public record RelationDTO(
        @Valid
        @NotNull(message = "You need to provide start station")
        @NotEmpty(message = "You need to provide start station")
        String start,
        @Valid
        @NotNull(message = "You need to provide destination")
        @NotEmpty(message = "You need to provide destination")
        String destination) {
}
