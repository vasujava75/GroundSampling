CREATE TABLE locations (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);

CREATE TABLE samples (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         location_id BIGINT NOT NULL,
                         depth DOUBLE NOT NULL,
                         date_collected DATE NOT NULL,
                         unit_weight DOUBLE NOT NULL,
                         water_content DOUBLE NOT NULL,
                         shear_strength DOUBLE NOT NULL,
                         FOREIGN KEY (location_id) REFERENCES locations(id)
);
