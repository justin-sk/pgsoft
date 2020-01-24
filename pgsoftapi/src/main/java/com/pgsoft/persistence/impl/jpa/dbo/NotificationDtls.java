package com.pgsoft.persistence.impl.jpa.dbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "NOTIFICATION_DTLS")
public class NotificationDtls implements IPgSoftDBO, IParentInjectableDBO<Notification> {
    @Id
    private Long Id;

    @Column(name = "DT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dt;

    @Column(name = "SENT_STS")
    private Boolean sentSts;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTIFICATION_ID", referencedColumnName = "ID", nullable = false)
    private Notification notification;

    @Override
    public void injectParent(Notification linkedParent) {
        this.notification = linkedParent;
    }

    @Override
    public Notification extractParent() {
        return this.notification;
    }
}
