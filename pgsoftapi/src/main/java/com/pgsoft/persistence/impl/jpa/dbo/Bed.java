package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "BRANCH_ROOM_BED")
public class Bed implements IPgSoftDBO, IParentInjectableDBO<Room> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BED_NO")
    private Integer bedNo;

    @Column(name = "RENT")
    private BigDecimal rent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ROOM_ID", referencedColumnName = "ID")
    private Room room;

    @OneToOne(mappedBy = "bed", fetch = FetchType.LAZY)
    private UserBed userBed;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BED_TYPE_ID", referencedColumnName = "ID")
    private BedType bedType;

    @Override
    public void injectParent(Room linkedParent) {
        this.room = linkedParent;
    }

    @Override
    public Room extractParent() {
        return this.room;
    }
}
