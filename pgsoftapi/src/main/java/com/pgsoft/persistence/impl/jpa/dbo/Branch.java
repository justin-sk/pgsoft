package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "BRANCH")
@Embeddable
public class Branch extends TypeDBO {
    @Nullable
    @OneToOne(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BranchInfo branchInfo;
}
