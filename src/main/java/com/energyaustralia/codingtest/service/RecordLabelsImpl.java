package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author amo31
 */
public class RecordLabelsImpl implements RecordLablesService{

    FestivalsService festivalsService;

    @Autowired
    public RecordLabelsImpl(FestivalsService festivalsService) {
        this.festivalsService = festivalsService;
    }

    /**
     * Construct record labels from music festival data
     *
     * @return List of RecordLabel
     */
    @Override
    public List<RecordLabel> getRecordLabels() {

        return reverse(festivalsService.getFestivalsData());

    }

    /**
     * Clear cache
     */
    @Override
    public void clearCache() {
        festivalsService.clearCache();
    }

    /**
     * Reverse the relationships of music festival data
     * @param festivals
     * @return List<RecordLabel>
     */
    private List<RecordLabel> reverse(List<MusicFestival> festivals) {

        Map<String,RecordLabel> recordLabels = new HashMap<>();

        for (MusicFestival festival:festivals) {
            for(Band band: festival.getBands()){

                if(!recordLabels.containsKey(band.getRecordLabel())){
                    RecordLabel recordLabel = new RecordLabel(band.getRecordLabel());
                    List<BandInFestival> bandInFestivals = new ArrayList<>();
                    bandInFestivals.add(new BandInFestival(band.getName(),festival.getName()));
                    recordLabel.setBands(bandInFestivals);
                    recordLabels.put(band.getRecordLabel(), recordLabel);
                }
                else {
                    RecordLabel recordLabel = recordLabels.get(band.getRecordLabel());
                    BandInFestival bandInFestival = new BandInFestival(band.getName(),festival.getName());
                    recordLabel.getBands().add(bandInFestival);
                }
            }
        }

        return recordLabels.values().stream().sorted(RecordLabel::compareTo).collect(Collectors.toList());

    }
}
