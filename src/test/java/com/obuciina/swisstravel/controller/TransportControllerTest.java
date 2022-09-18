package com.obuciina.swisstravel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.obuciina.swisstravel.model.dto.RelationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransportControllerTest {

    private final String baseUrl = "http://localhost:8080/api/v1/connections";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Test
    void testGetDurationOk() throws Exception {
        //given
        RelationDTO relationOne = new RelationDTO("Lausanne", "Genève");
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String request = objectWriter.writeValueAsString(relationOne);

        //then
        MvcResult result = mockMvc.perform(get(baseUrl).contentType(APPLICATION_JSON_UTF8).content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("minutes"));
    }

    @Test
    void testGetDurationBadRequest() throws Exception {
        //given
        RelationDTO relationOne = new RelationDTO("Lausanne", "");
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String request = objectWriter.writeValueAsString(relationOne);

        //then
        mockMvc.perform(get(baseUrl).contentType(APPLICATION_JSON_UTF8).content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
}
