package com.energyaustralia.codingtest;

import com.energyaustralia.codingtest.controller.FestivalsController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CodingtestApplicationTests {

    @Autowired
    FestivalsController festivalsController;

    @Test
    void contextLoads()  {
        Assert.assertNotNull(festivalsController);
    }

}
