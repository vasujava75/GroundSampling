package org.offshore.groundsampling.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.offshore.groundsampling.model.Location;
import org.offshore.groundsampling.model.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SampleRepositoryTest {

    @Autowired
    private SampleRepository sampleRepository;


    @Test
    public void testFindByLocation() {
        // Given
        Location location = new Location(3L, "New York");

        Sample sample1 = new Sample(5L, location, 10.0, LocalDate.now(), 1.5, 20.0, 30.0);
        Sample sample2 = new Sample(4L, location, 15.0, LocalDate.now(), 1.6, 21.0, 31.0);
        sampleRepository.save(sample1);
        sampleRepository.save(sample2);

        // When
          List<Sample> samples = sampleRepository.findByLocation(location);

        // Then
         assertThat(samples).hasSize(2);
         assertThat(samples).extracting(Sample::getDepth).containsExactlyInAnyOrder(10.0, 15.0);
    }
}
