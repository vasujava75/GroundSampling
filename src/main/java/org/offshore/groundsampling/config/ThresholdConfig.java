package org.offshore.groundsampling.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThresholdConfig {

    @Value("${threshold.unitWeight}")
    private double unitWeightThreshold;

    @Value("${threshold.waterContent}")
    private double waterContentThreshold;

    @Value("${threshold.shearStrength}")
    private double shearStrengthThreshold;

    public double getUnitWeightThreshold() {
        return unitWeightThreshold;
    }

    public double getWaterContentThreshold() {
        return waterContentThreshold;
    }

    public double getShearStrengthThreshold() {
        return shearStrengthThreshold;
    }
}
