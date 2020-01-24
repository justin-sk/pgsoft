package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "AMENITY")
@Entity
public class Amenity extends TypeDBO {
    @Column(name = "[DESC]")
    private String description;

    @Column(name = "IMAGE_URL")
    private String imageUrl;
}
