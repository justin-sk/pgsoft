package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CITY")
public class City extends TypeDBO {
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Collection<BranchInfo> branchInfos;
}
