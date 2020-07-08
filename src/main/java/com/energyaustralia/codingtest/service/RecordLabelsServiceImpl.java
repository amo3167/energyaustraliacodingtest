package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.client.FestivalsClient;
import com.energyaustralia.codingtest.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.bind.ValidationException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author amo31
 */
@Service("recordLabelsService")
public class RecordLabelsServiceImpl implements RecordLabelsService {

    Logger logger = LoggerFactory.getLogger(RecordLabelsServiceImpl.class);

    private final FestivalsClient festivalsService;

    @Autowired
    public RecordLabelsServiceImpl(FestivalsClient festivalsService) {
        this.festivalsService = festivalsService;
    }

    /**
     * Construct record labels from music festival data
     *
     * @return List of RecordLabel
     */
    @Override
    public List<RecordLabel> getRecordLabels() throws ValidationException {

        return reverse(festivalsService.getFestivalsData());

    }

    /**
     * Reverse the relationships of music festival data
     * @param festivals List of MusicFestival
     * @return List<RecordLabel>
     */
    private List<RecordLabel> reverse(List<MusicFestival> festivals) {

        Map<String,RecordLabel> recordLabels = new HashMap<>(100);

        for (MusicFestival festival:festivals) {
            if(StringUtils.isEmpty(festival.getName())) {
                logger.info("Empty festival {} ",festival.getName());
                festival.setName("");
            }

            for(Band band: festival.getBands()){

                if(!isValidBand(band)) {
                    logger.warn("Skip invalid band {} ",band);
                    continue;
                }
                if(!recordLabels.containsKey(band.getRecordLabel())){
                    RecordLabel recordLabel = new RecordLabel(band.getRecordLabel());
                    TreeSet<BandInFestival> bandInFestivals = new TreeSet<>();
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

    /**
     * Check if band contain empty band name or recordLabel name
     * @param band Band
     * @return True： valid band, False: Invalid band
     */
    private boolean isValidBand(Band band){
        return !StringUtils.isEmpty(band.getName()) && !StringUtils.isEmpty(band.getRecordLabel()) ;
    }
}
