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
@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
@Table(name = "SUB_CTG")
public class SubCategory extends TypeDBO {
    @OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
    private Collection<CategorySubCategoryReference> categorySubCtgXrefsById;

    @OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
    private Collection<MaintenanceRequest> maintenanceRequests;

    @OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
    private Collection<UserSuggestion> userSuggestions;
}
