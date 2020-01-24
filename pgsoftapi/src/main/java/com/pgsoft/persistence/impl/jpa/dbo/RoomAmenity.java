package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ROOM_AMENITY")
public class RoomAmenity implements IPgSoftDBO, IParentInjectableDBO<Room> {
    @Id
    private Long id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ROOM_ID", referencedColumnName = "ID", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AMENITY_ID", referencedColumnName = "ID", nullable = false)
    private Amenity amenity;

    @Override
    public void injectParent(Room linkedParent) {
        this.room = linkedParent;
    }

    @Override
    public Room extractParent() {
        return this.room;
    }
}
