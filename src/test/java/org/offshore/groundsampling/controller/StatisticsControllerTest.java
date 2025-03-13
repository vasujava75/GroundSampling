package org.offshore.groundsampling.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.offshore.groundsampling.model.Location;
import org.offshore.groundsampling.model.Sample;
import org.offshore.groundsampling.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatisticsController.class)
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SampleRepository sampleRepository;

    @InjectMocks
    private StatisticsController statisticsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAverageWaterContent() throws Exception {
        List<Sample> samples = Arrays.asList(
            new Sample(1L, new Location(), 10.0, LocalDate.now(), 20.0, 50.0, 30.0),
            new Sample(2L, new Location(), 15.0, LocalDate.now(), 25.0, 150.0, 35.0)
        );

        when(sampleRepository.findAll()).thenReturn(samples);

        mockMvc.perform(get("/api/statistics/average-water-content"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(100.0));
    }

    @Test
    public void testGetThresholdExceedingSamples() throws Exception {
        List<Sample> samples = Arrays.asList(
            new Sample(1L, new Location(), 10.0, LocalDate.now(), 20.0, 50.0, 30.0),
            new Sample(2L, new Location(), 15.0, LocalDate.now(), 25.0, 150.0, 35.0)
        );

        when(sampleRepository.findAll()).thenReturn(samples);

        mockMvc.perform(get("/api/statistics/threshold-exceeding-samples"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].waterContent").value(150.0));
    }
}
