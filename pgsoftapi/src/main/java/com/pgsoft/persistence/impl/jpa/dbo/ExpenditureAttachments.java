package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "EXPNDTR_ATTACHMENTS")
public class ExpenditureAttachments implements IPgSoftDBO, IParentInjectableDBO<Expenditure> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ATTACHMENT_URL")
    private String attachmentUrl;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXPNDTR_ID", referencedColumnName = "ID", nullable = false)
    private Expenditure expenditure;

    public ExpenditureAttachments(String url, Expenditure obj) {
        this.attachmentUrl = url;
        this.expenditure = obj;
    }

    @Override
    public void injectParent(Expenditure linkedParent) {
        this.expenditure = linkedParent;
    }

    @Override
    public Expenditure extractParent() {
        return this.expenditure;
    }
}
