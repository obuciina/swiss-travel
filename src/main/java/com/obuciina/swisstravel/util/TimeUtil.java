package com.obuciina.swisstravel.util;

import com.obuciina.swisstravel.model.dto.ConnectionDTO;
import com.obuciina.swisstravel.model.dto.RelationDTO;
import com.obuciina.swisstravel.model.dto.StationDTO;
import com.obuciina.swisstravel.model.dto.TravelDTO;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class TimeUtil {

    private int days;
    private int hours;
    private int minutes;
    private int seconds;

    public ArrayList<TravelDTO> mapToTravel(ConnectionDTO connectionDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh/mm/ss");

        ArrayList<TravelDTO> solution = new ArrayList<>();
        for (RelationDTO relation : connectionDTO.getConnections()) {
            parseDuration(relation.getDuration());
            TravelDTO travelDTO = TravelDTO.builder()
                    .start(StationDTO.builder()
                            .id(relation.getFrom().getStation().getId())
                            .name(relation.getFrom().getStation().getName())
                            .build())
                    .destination(StationDTO.builder()
                            .id(relation.getTo().getStation().getId())
                            .name(relation.getTo().getStation().getName())
                            .build())
                    .duration(LocalTime.of(hours, minutes, seconds))
                    .build();

            solution.add(travelDTO);
        }
        return solution;
    }

    private void parseDuration(String duration) {
        String[] splitedByLetterD = duration.split("d");
        String[] splittedByColon = splitedByLetterD[1].split(":");
        this.days = ((splitedByLetterD.length >= 1) ? Integer.parseInt(splitedByLetterD[0]) : 0);
        this.hours = ((splittedByColon.length >= 1) ? Integer.parseInt(splittedByColon[0]) : 0);
        this.minutes = ((splittedByColon.length >= 2) ? Integer.parseInt(splittedByColon[1]) : 0);
        this.seconds = ((splittedByColon.length >= 3) ? Integer.parseInt(splittedByColon[2]) : 0);
    }
}
