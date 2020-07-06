package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecordLabelsImpl implements RecordLablesService{

    @Autowired
    FestivalCache cache;

    /**
     * Construct record labels from music festival data
     *
     * @return List of RecordLabel
     */
    @Override
    public List<RecordLabel> getRecordLabels() {

        return reverse(cache.getFestivals());

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
