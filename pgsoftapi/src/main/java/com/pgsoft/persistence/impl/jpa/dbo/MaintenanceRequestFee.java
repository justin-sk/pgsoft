package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "MAINT_REQ_FEE")
public class MaintenanceRequestFee implements IPgSoftDBO, IParentInjectableDBO<MaintenanceRequest> {
    @Id
    private Long id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAINT_REQ_ID", referencedColumnName = "ID", nullable = false)
    private MaintenanceRequest maintenanceRequest;

    @Override
    public void injectParent(MaintenanceRequest linkedParent) {
        this.maintenanceRequest = linkedParent;
    }

    @Override
    public MaintenanceRequest extractParent() {
        return this.maintenanceRequest;
    }
}
