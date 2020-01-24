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
@Table(name = "REC_TYP")
public class RecurrenceType extends TypeDBO {
    @OneToMany(mappedBy = "recurrenceType", fetch = FetchType.LAZY)
    private Collection<ExpenditureMaster> expenditureMasters;

    @OneToMany(mappedBy = "recurrenceType", fetch = FetchType.LAZY)
    private Collection<Notification> notifications;
}
