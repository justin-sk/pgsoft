package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "MAINT_REQ_ATTACHMENT")
public class MaintenanceRequestAttachment implements IPgSoftDBO, IParentInjectableDBO<MaintenanceRequest> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ATTACHMENT_URL")
    private String attachmentUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAINT_REQ_ID", referencedColumnName = "ID", nullable = false)
    private MaintenanceRequest maintenanceRequest;

    public MaintenanceRequestAttachment(String url, MaintenanceRequest obj) {
        this.attachmentUrl = url;
        this.maintenanceRequest = obj;
    }


    @Override
    public void injectParent(MaintenanceRequest linkedParent) {
        this.maintenanceRequest = linkedParent;
    }

    @Override
    public MaintenanceRequest extractParent() {
        return this.maintenanceRequest;
    }
}
