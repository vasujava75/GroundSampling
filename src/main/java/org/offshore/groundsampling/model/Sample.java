package org.offshore.groundsampling.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "samples")
public record Sample(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,
    @ManyToOne
    Location location,
    double depth,
    LocalDate dateCollected,
    double unitWeight,
    double waterContent,
    double shearStrength
) {}
