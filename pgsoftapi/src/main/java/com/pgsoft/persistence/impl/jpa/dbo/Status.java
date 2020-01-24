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
@Table(name = "STS")
@Entity
public class Status extends TypeDBO {
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private Collection<MaintenanceRequest> maintenanceRequests;
}
