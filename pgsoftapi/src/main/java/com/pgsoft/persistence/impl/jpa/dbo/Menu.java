package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "MENU")
@Entity
public class Menu extends TypeDBO {
    @Column(name = "URL")
    private String url;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private Collection<MenuSubMenuReference> menuSubMenuReferences;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private Collection<RoleMenuXref> roleMenuXrefs;
}
