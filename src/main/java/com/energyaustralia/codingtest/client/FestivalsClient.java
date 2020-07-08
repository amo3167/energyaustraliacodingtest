package com.energyaustralia.codingtest.client;

import com.energyaustralia.codingtest.model.MusicFestival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author amo31
 */
@Service
public class FestivalsClient {

    private String url;

    private RestTemplate restTemplate;

    @Autowired
    public FestivalsClient(@Value("${festivals.client.url}") String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieve music festival data from festival management API
     * @return List of MusicFestival
     */
    @Cacheable("festivalDataCache")
    public List<MusicFestival> getFestivalsData() throws ValidationException {
        List<MusicFestival> result = new ArrayList<>();

        try {
            MusicFestival[] festivals = restTemplate.getForObject(url, MusicFestival[].class);

            if (festivals != null) {
                result = Arrays.asList(festivals);
            }
            return result;
        }
        catch (RestClientException e){
            throw new ValidationException("Fail to getFestivalsData.", e);
        }
     }

}
