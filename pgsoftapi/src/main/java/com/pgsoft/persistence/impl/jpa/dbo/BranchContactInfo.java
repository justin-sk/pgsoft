package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "BRANCH_CONTACT_INFO")
public class BranchContactInfo implements IPgSoftDBO, IParentInjectableDBO<Branch> {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NM")
    private String nm;

    @Column(name = "PHONE")
    private String phone;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID")
    private Branch branch;

    @Override
    public void injectParent(Branch linkedBranch) {
        this.branch = linkedBranch;
    }

    @Override
    public Branch extractParent() {
        return this.branch;
    }
}
