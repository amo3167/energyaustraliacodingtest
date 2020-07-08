package com.energyaustralia.codingtest.controller;

import com.energyaustralia.codingtest.model.BandInFestival;
import com.energyaustralia.codingtest.model.RecordLabel;
import com.energyaustralia.codingtest.service.RecordLabelsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FestivalsController.class)
class FestivalsControllerTest {

    @MockBean
    RecordLabelsService recordLabelsService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testRecordLabelsGet() throws Exception {

        List<RecordLabel> recordLabelList = Collections.singletonList(
                new RecordLabel("R1", Arrays.asList(
                        new BandInFestival("B1", "F1"),
                        new BandInFestival("B1", "F3")
                ))
        );

        Mockito.when(recordLabelsService.getRecordLabels()).thenReturn(recordLabelList);

        ResultActions resultActions = mockMvc.perform(get("/recordlabels"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].recordLabel", Matchers.is("R1")));
    }

    @Test
    void Given_Service_Error_When_TestRead_Expect_InternalServerError() throws Exception {


        Mockito.when(recordLabelsService.getRecordLabels()).thenThrow(new ValidationException("Error"));

        ResultActions resultActions = mockMvc.perform(get("/recordlabels"));
        resultActions
                .andExpect(status().isInternalServerError());


    }
}
