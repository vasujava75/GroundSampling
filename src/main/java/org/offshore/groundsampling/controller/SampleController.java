package org.offshore.groundsampling.controller;

import org.offshore.groundsampling.model.Sample;
import org.offshore.groundsampling.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/samples")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping
    public ResponseEntity<List<Sample>> getAllSamples() {
        List<Sample> samples = sampleService.getAllSamples().collectList().block();
        return ResponseEntity.ok(samples);
    }

    @PostMapping
    public ResponseEntity<Sample> addSample(@RequestBody Sample sample) {
        Sample savedSample = sampleService.addSample(sample).block();
        return ResponseEntity.ok(savedSample);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sample> updateSample(@PathVariable Long id, @RequestBody Sample sample) {
        Sample updatedSample = sampleService.updateSample(id, sample).block();
        return ResponseEntity.ok(updatedSample);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSample(@PathVariable Long id) {
        sampleService.deleteSample(id).block();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<Sample>> getSamplesByLocation(@PathVariable Long locationId) {
        List<Sample> samples = sampleService.getSamplesByLocation(locationId).collectList().block();
        return ResponseEntity.ok(samples);
    }
}
