package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "MAINT_REQ_ASSIGN_TO")
public class MaintenanceRequestAssignTo implements IPgSoftDBO, IParentInjectableDBO<MaintenanceRequest> {
    @Id
    private Long id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAINT_REQ_ID", referencedColumnName = "ID", nullable = false)
    private MaintenanceRequest maintenanceRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID", referencedColumnName = "ID")
    private User user;

    @Override
    public void injectParent(MaintenanceRequest linkedParent) {
        this.maintenanceRequest = linkedParent;
    }

    @Override
    public MaintenanceRequest extractParent() {
        return this.maintenanceRequest;
    }
}
