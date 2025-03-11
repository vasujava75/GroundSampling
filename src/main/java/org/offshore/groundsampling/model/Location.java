package org.offshore.groundsampling.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "locations")
public record Location (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,
    String name,
    @OneToMany(mappedBy = "location")
    List<Sample> samples
){}
