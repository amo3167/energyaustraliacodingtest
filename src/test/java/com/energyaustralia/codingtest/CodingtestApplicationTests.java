package com.energyaustralia.codingtest;

import com.energyaustralia.codingtest.controller.FestivalsController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CodingtestApplicationTests {

    @Autowired
    FestivalsController festivalsController;

    @Test
    void contextLoads()  {
        assertNotEquals(null,festivalsController);
    }

}
