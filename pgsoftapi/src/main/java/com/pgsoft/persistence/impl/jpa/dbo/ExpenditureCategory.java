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
@Table(name = "EXPNDTR_CTG")
public class ExpenditureCategory extends TypeDBO {
    @OneToMany(mappedBy = "expenditureCategory", fetch = FetchType.LAZY)
    private Collection<Expenditure> expenditures;

    @OneToMany(mappedBy = "expenditureCategory", fetch = FetchType.LAZY)
    private Collection<ExpenditureMaster> expenditureMasters;
}
