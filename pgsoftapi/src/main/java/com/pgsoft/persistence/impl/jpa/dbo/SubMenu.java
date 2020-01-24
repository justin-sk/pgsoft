package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SUB_MENU")
public class SubMenu extends TypeDBO {
    @OneToMany(mappedBy = "subMenu", fetch = FetchType.LAZY)
    private Collection<MenuSubMenuReference> menuSubMenuXrefsById;
}
