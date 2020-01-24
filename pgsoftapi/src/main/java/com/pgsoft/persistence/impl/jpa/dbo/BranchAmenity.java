package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "BRANCH_AMENITY")
public class BranchAmenity implements IParentInjectableDBO<Branch> {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AMENITY_ID", referencedColumnName = "ID", nullable = false)
    private Amenity amenity;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID", nullable = false)
    private Branch branch;

    @Override
    public void injectParent(Branch linkedParent) {
        this.branch = linkedParent;
    }

    @Override
    public Branch extractParent() {
        return this.branch;
    }
}
