package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "BRANCH_INFO")
public class BranchInfo implements IPgSoftDBO, IParentInjectableDBO<Branch> {
    @Id
    private Long id;

    @Column(name = "NM")
    private String nm;

    @Column(name = "NICK_NM")
    private String nickNm;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STS")
    private Boolean sts;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OCCUPANCY_TYPE_ID", referencedColumnName = "ID", nullable = false)
    private OccupancyType occupancyType;

    @Override
    public void injectParent(Branch linkedParent) {
        this.branch = linkedParent;
    }

    @Override
    public Branch extractParent() {
        return this.branch;
    }
}
