package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "USR_BED")
public class UserBed implements IPgSoftDBO, IParentInjectableDBO<User> {
    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID", referencedColumnName = "ID")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ROOM_BED_ID", referencedColumnName = "ID")
    private Bed bed;

    @Override
    public void injectParent(User linkedParent) {
        this.user = linkedParent;
    }

    @Override
    public User extractParent() {
        return this.user;
    }
}
