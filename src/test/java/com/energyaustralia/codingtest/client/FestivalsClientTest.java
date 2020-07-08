package com.energyaustralia.codingtest.client;

import com.energyaustralia.codingtest.model.Band;
import com.energyaustralia.codingtest.model.MusicFestival;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class FestivalsClientTest {

    private final String URL = "http://eacodingtest.digital.energyaustralia.com.au/api/v1/festivals";

    @MockBean
    private RestTemplate restTemplate;

    private FestivalsClient service;

    @BeforeEach
    public void setUp(){
        service = new FestivalsClient(URL,restTemplate);
    }

    @Test
    void Given_GoodData_When_GetFestivalsData_Expect_No_Exception() throws ValidationException {


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
        List<MusicFestival> result = service.getFestivalsData();

        assertThat(result, containsInAnyOrder(festivals));
    }

    @Test
    void Given_Api_Error_When_GetFestivalsData_Expect_RestClientException() {

        Mockito.when(restTemplate.getForObject(URL, MusicFestival[].class)).thenThrow(new RestClientException("Error"));

        Exception exception = assertThrows(ValidationException.class, () ->
                service.getFestivalsData());

        assertEquals("Fail to getFestivalsData.", exception.getMessage());
    }

}
