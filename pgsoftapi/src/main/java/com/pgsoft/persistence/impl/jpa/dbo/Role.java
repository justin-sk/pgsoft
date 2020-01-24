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
@Table(name = "ROLE")
@Entity
public class Role extends TypeDBO {
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private Collection<RoleMenuXref> roleMenuXrefsById;
}
