package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "MENU_SUB_MENU_XREF")
public class MenuSubMenuReference implements IPgSoftDBO, IParentInjectableDBO<Menu> {
    @Id
    private Long id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID", referencedColumnName = "ID")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_MENU_ID", referencedColumnName = "ID")
    private SubMenu subMenu;

    @Override
    public void injectParent(Menu linkedParent) {
        this.menu = linkedParent;
    }

    @Override
    public Menu extractParent() {
        return this.menu;
    }
}
