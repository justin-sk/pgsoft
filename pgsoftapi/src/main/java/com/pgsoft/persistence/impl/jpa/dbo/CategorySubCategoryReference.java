package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "CTG_SUB_CTG_XREF")
public class CategorySubCategoryReference implements IPgSoftDBO, IParentInjectableDBO<Category> {
    @Id
    private Long id;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "CTG_ID", referencedColumnName = "ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "SUB_CTG_ID", referencedColumnName = "ID")
    private SubCategory subCategory;

    @Override
    public void injectParent(Category linkedParent) {
        this.category = linkedParent;
    }

    @Override
    public Category extractParent() {
        return this.category;
    }
}
