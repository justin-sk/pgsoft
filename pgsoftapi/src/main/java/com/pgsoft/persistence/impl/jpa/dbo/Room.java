package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "BRANCH_ROOM")
public class Room implements IPgSoftDBO, IParentInjectableDBO<Branch> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROOM_NO")
    private String roomNo;

    @Column(name = "STS")
    private Boolean sts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID", nullable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_TYPE_ID", referencedColumnName = "ID")
    private RoomType roomType;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Collection<Bed> beds;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Collection<RoomAmenity> roomAmenities;

    @Override
    public void injectParent(Branch linkedParent) {
        this.branch = linkedParent;
    }

    @Override
    public Branch extractParent() {
        return this.branch;
    }
}
