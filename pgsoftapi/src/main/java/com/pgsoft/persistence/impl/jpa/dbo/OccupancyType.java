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
@Table(name = "OCCUPANCY_TYPE")
public class OccupancyType extends TypeDBO {
    @OneToMany(mappedBy = "occupancyType", fetch = FetchType.LAZY)
    private Collection<BranchInfo> branchInfos;
}
