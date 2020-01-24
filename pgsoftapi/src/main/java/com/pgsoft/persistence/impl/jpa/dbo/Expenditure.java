package com.pgsoft.persistence.impl.jpa.dbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "EXPNDTR")
@Entity
public class Expenditure implements IPgSoftDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "BILL_DT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date billDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXPNDTR_CTG_ID", referencedColumnName = "ID", nullable = false)
    private ExpenditureCategory expenditureCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID", nullable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID", referencedColumnName = "ID")
    private User user;

    @OneToMany(mappedBy = "expenditure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ExpenditureAttachments> expenditureAttachments;
}
