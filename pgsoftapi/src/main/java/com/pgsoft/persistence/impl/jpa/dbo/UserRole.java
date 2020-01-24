package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "USR_ROLE")
public class UserRole implements IPgSoftDBO, IParentInjectableDBO<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    private Role role;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID", referencedColumnName = "ID")
    private User user;

    @Override
    public void injectParent(User linkedParent) {
        this.user = linkedParent;
    }

    @Override
    public User extractParent() {
        return this.user;
    }
}
