package com.pgsoft.persistence.impl.jpa.dbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "EXPNDTR_MASTER")
public class ExpenditureMaster implements IPgSoftDBO {
    @Id
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
    @JoinColumn(name = "REC_TYP_ID", referencedColumnName = "ID", nullable = false)
    private RecurrenceType recurrenceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID", nullable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID", referencedColumnName = "ID")
    private User user;
}
