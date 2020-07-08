package com.energyaustralia.codingtest.integration;

import com.energyaustralia.codingtest.client.FestivalsClient;
import com.energyaustralia.codingtest.controller.FestivalsController;
import com.energyaustralia.codingtest.model.Band;
import com.energyaustralia.codingtest.model.BandInFestival;
import com.energyaustralia.codingtest.model.MusicFestival;
import com.energyaustralia.codingtest.model.RecordLabel;
import com.energyaustralia.codingtest.service.RecordLabelsService;
import com.energyaustralia.codingtest.service.RecordLabelsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInRelativeOrder.containsInRelativeOrder;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
class IntegrationTest {

    private final String URL = "http://eacodingtest.digital.energyaustralia.com.au/api/v1/festivals";

    @MockBean
    RestTemplate restTemplate;

    FestivalsClient festivalsService;
    RecordLabelsService recordLabelsService;
    FestivalsController festivalsController;

    @BeforeEach
    public void setUp(){
        festivalsService = new FestivalsClient(URL,restTemplate);
        recordLabelsService = new RecordLabelsServiceImpl(festivalsService);
        festivalsController = new FestivalsController(recordLabelsService);

    }

    @Test
    void testRecordLabelsGet() throws Exception {

        MusicFestival[] festivals = {
                new MusicFestival("F1", Arrays.asList(
                        new Band("B1","R1"),
                        new Band("B2","R2")
                )),
                new MusicFestival("F2", Arrays.asList(
                        new Band("B1","R3"),
                        new Band("B3","R4")
                ))
        };

        Mockito.when(restTemplate.getForObject(URL, MusicFestival[].class)).thenReturn(festivals);

        Iterable<RecordLabel> result = festivalsController.recordLabelsGet();

        List<RecordLabel> expected = Arrays.asList(
                new RecordLabel("R1", Collections.singletonList(
                        new BandInFestival("B1", "F1")
                )),
                new RecordLabel("R2", Collections.singletonList(
                        new BandInFestival("B2", "F1")
                )),
                new RecordLabel("R3", Collections.singletonList(
                        new BandInFestival("B1", "F2")
                )),
                new RecordLabel("R4", Collections.singletonList(
                        new BandInFestival("B3", "F2")
                ))
        );

        assertThat(result, containsInRelativeOrder(expected.toArray()));
    }
}
