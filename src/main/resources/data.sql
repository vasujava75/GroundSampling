INSERT INTO locations (id, name) VALUES (1, 'Netherlands');
INSERT INTO locations (id, name) VALUES (2, 'Germany');

INSERT INTO samples (id, location_id, depth, date_collected, unit_weight, water_content, shear_strength) VALUES
                                                                                                             (1, 1, 10.0, '2025-03-11', 20.0, 50.0, 100.0),
                                                                                                             (2, 1, 20.0, '2025-03-11', 22.0, 55.0, 110.0),
                                                                                                             (3, 2, 15.0, '2025-03-11', 18.0, 45.0, 90.0);
