package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.Band;
import com.energyaustralia.codingtest.model.BandInFestival;
import com.energyaustralia.codingtest.model.MusicFestival;
import com.energyaustralia.codingtest.model.RecordLabel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInRelativeOrder.containsInRelativeOrder;

@ExtendWith(SpringExtension.class)
class RecordLabelsServiceTest {

    @MockBean
    private FestivalsService festivalsService;

    private RecordLabelsService recordLabelsService;

    @BeforeEach
    public void setUp(){
        recordLabelsService = new RecordLabelsServiceImpl(festivalsService);
    }

    @Test
    void Given_GoodData_When_GetRecordLabels_Expect_OK() throws ValidationException {


        List<MusicFestival> festivals = Arrays.asList(
                new MusicFestival("F1", Arrays.asList(
                        new Band("B1","R1"),
                        new Band("B2","R2")
                )),
                new MusicFestival("F2", Arrays.asList(
                        new Band("B1","R3"),
                        new Band("B3","R4")
                )),
                new MusicFestival("F3", Arrays.asList(
                        new Band("B1","R1"),
                        new Band("B3","R2")
                ))
        );

        Mockito.when(festivalsService.getFestivalsData()).thenReturn(festivals);
        List<RecordLabel> result = recordLabelsService.getRecordLabels();

        List<RecordLabel> expected = Arrays.asList(
                new RecordLabel("R1", Arrays.asList(
                        new BandInFestival("B1","F1"),
                        new BandInFestival("B1","F3")
                        )),
                new RecordLabel("R2", Arrays.asList(
                        new BandInFestival("B2","F1"),
                        new BandInFestival("B3","F3")
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

    @Test
    void Given_NullFestivalName_When_GetRecordLabels_Expect_EmptyFestivalName() throws ValidationException {


        List<MusicFestival> festivals = Arrays.asList(
                new MusicFestival("F1", Arrays.asList(
                        new Band("B1","R1"),
                        new Band("B2","R2")
                )),
                new MusicFestival("F2", Arrays.asList(
                        new Band("B1","R3"),
                        new Band("B3","R4")
                )),
                new MusicFestival("F3", Arrays.asList(
                        new Band("B1","R1"),
                        new Band("B3","R2")
                )),
                new MusicFestival(null, Arrays.asList(
                        new Band("B1","R4"),
                        new Band("B4","R2")
                ))
        );

        Mockito.when(festivalsService.getFestivalsData()).thenReturn(festivals);
        List<RecordLabel> result = recordLabelsService.getRecordLabels();

        List<RecordLabel> expected = Arrays.asList(
                new RecordLabel("R1", Arrays.asList(
                        new BandInFestival("B1","F1"),
                        new BandInFestival("B1","F3")
                )),
                new RecordLabel("R2", Arrays.asList(
                        new BandInFestival("B2","F1"),
                        new BandInFestival("B3","F3"),
                        new BandInFestival("B4","")
                )),
                new RecordLabel("R3", Collections.singletonList(
                        new BandInFestival("B1", "F2")
                )),
                new RecordLabel("R4", Arrays.asList(
                        new BandInFestival("B1", "F2"),
                        new BandInFestival("B3", "")
                ))
        );

        assertThat(result, containsInRelativeOrder(expected.toArray()));
    }

    @Test
    void Given_EmptyBandName_When_GetRecordLabels_Expect_Skip_EmptyBandName() throws ValidationException {


        List<MusicFestival> festivals = Arrays.asList(
                new MusicFestival("F1", Arrays.asList(
                        new Band("B1","R1"),
                        new Band("B2","R2")
                )),
                new MusicFestival("F2", Arrays.asList(
                        new Band("B1","R3"),
                        new Band("B3","R4")
                )),
                new MusicFestival("F3", Arrays.asList(
                        new Band("B1","R1"),
                        new Band("","R2")
                )),
                new MusicFestival("F4", Arrays.asList(
                        new Band(null,"R4"),
                        new Band("B4","R2")
                ))
        );

        Mockito.when(festivalsService.getFestivalsData()).thenReturn(festivals);
        List<RecordLabel> result = recordLabelsService.getRecordLabels();

        List<RecordLabel> expected = Arrays.asList(
                new RecordLabel("R1", Arrays.asList(
                        new BandInFestival("B1","F1"),
                        new BandInFestival("B1","F3")
                )),
                new RecordLabel("R2", Arrays.asList(
                        new BandInFestival("B2","F1"),
                        new BandInFestival("B4","F4")
                )),
                new RecordLabel("R3", Collections.singletonList(
                        new BandInFestival("B1", "F2")
                )),
                new RecordLabel("R4", Collections.singletonList(
                        new BandInFestival("B1", "F2")
                ))
        );

        assertThat(result, containsInRelativeOrder(expected.toArray()));
    }

    @Test
    void Given_EmptyRecordLabelName_When_GetRecordLabels_Expect_Skip_EmptyRecordLabelName() throws ValidationException {


        List<MusicFestival> festivals = Arrays.asList(
                new MusicFestival("F1", Arrays.asList(
                        new Band("B1","R1"),
                        new Band("B2","R2")
                )),
                new MusicFestival("F2", Arrays.asList(
                        new Band("B1","R3"),
                        new Band("B3","")
                )),
                new MusicFestival("F3", Arrays.asList(
                        new Band("B1","R1"),
                        new Band("B5",null)
                )),
                new MusicFestival("F4", Arrays.asList(
                        new Band("B2","R4"),
                        new Band("B4","R2")
                ))
        );

        Mockito.when(festivalsService.getFestivalsData()).thenReturn(festivals);
        List<RecordLabel> result = recordLabelsService.getRecordLabels();

        List<RecordLabel> expected = Arrays.asList(
                new RecordLabel("R1", Arrays.asList(
                        new BandInFestival("B1","F1"),
                        new BandInFestival("B1","F3")
                )),
                new RecordLabel("R2", Arrays.asList(
                        new BandInFestival("B2","F1"),
                        new BandInFestival("B4","F4")
                )),
                new RecordLabel("R3", Collections.singletonList(
                        new BandInFestival("B1", "F2")
                )),
                new RecordLabel("R4", Arrays.asList(
                        new BandInFestival("B2", "F4")
                ))
        );

        assertThat(result, containsInRelativeOrder(expected.toArray()));
    }

}
