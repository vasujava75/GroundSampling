package org.offshore.groundsampling.controller;

import org.offshore.groundsampling.model.Sample;
import org.offshore.groundsampling.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    private SampleRepository sampleRepository;

    @GetMapping("/average-water-content")
    public double getAverageWaterContent() {
        List<Sample> samples = sampleRepository.findAll();
        return samples.stream().mapToDouble(Sample::getWaterContent).average().orElse(0);
    }

    @GetMapping("/threshold-exceeding-samples")
    public List<Sample> getThresholdExceedingSamples() {
        double thresholdWaterContent = 100; // Example threshold
        return sampleRepository.findAll().stream()
            .filter(sample -> sample.getWaterContent() > thresholdWaterContent)
            .collect(Collectors.toList());
    }
}
