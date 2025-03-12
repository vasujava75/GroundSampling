package org.offshore.groundsampling.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "samples")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Sample{
    @Id
    Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    Location location;
    Double depth;
    LocalDate dateCollected;
    Double unitWeight;
    Double waterContent;
    Double shearStrength;
}
