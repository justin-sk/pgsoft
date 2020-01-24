package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ROLE_MENU_XREF")
public class RoleMenuXref implements IPgSoftDBO, IParentInjectableDBO<Role> {
    @Id
    private Long id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID", referencedColumnName = "ID")
    private Menu menu;

    @Override
    public void injectParent(Role linkedParent) {
        this.role = linkedParent;
    }

    @Override
    public Role extractParent() {
        return this.role;
    }
}
