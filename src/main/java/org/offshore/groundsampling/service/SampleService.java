package org.offshore.groundsampling.service;

import org.offshore.groundsampling.config.ThresholdConfig;
import org.offshore.groundsampling.model.Sample;
import org.offshore.groundsampling.repository.LocationRepository;
import org.offshore.groundsampling.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ThresholdConfig thresholdConfig;



    public Flux<Sample> getAllSamples() {
        return Flux.fromIterable(sampleRepository.findAll());
    }

    public Mono<Sample> addSample(Sample sample) {
        return Mono.just(sampleRepository.save(sample));
    }

    public Mono<Sample> updateSample(Long id, Sample sample) {
        return Mono.justOrEmpty(sampleRepository.findById(id))
            .flatMap(existingSample -> Mono.just(sampleRepository.save(new Sample(
                id,
                sample.location(),
                sample.depth(),
                sample.dateCollected(),
                sample.unitWeight(),
                sample.waterContent(),
                sample.shearStrength()
            ))));
    }

    public Mono<Void> deleteSample(Long id) {
        sampleRepository.deleteById(id);
        return Mono.empty();
    }

    public Flux<Sample> getThresholdExceedingSamples() {
        double unitWeightThreshold = thresholdConfig.getUnitWeightThreshold();
        double waterContentThreshold = thresholdConfig.getWaterContentThreshold();
        double shearStrengthThreshold = thresholdConfig.getShearStrengthThreshold();

        return Flux.fromIterable(sampleRepository.findAll())
            .filter(sample -> sample.unitWeight() > unitWeightThreshold ||
                sample.waterContent() > waterContentThreshold ||
                sample.shearStrength() > shearStrengthThreshold);
    }

    public Flux<Sample> getSamplesByLocation(Long locationId) {
        return Mono.justOrEmpty(locationRepository.findById(locationId))
            .flatMapMany(location -> Flux.fromIterable(sampleRepository.findByLocation(location)));
    }
}
