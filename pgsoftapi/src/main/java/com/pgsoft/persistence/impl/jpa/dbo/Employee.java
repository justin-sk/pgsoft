package com.pgsoft.persistence.impl.jpa.dbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Employee implements IPgSoftDBO, IParentInjectableDBO<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMPL_NO")
    private String emplNo;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PERM_ADDRESS")
    private String permAddress;

    @Column(name = "SALARY")
    private BigDecimal salary;

    @Column(name = "DATE_OF_JOIN")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfJoin;

    @Column(name = "DATE_OF_EXIT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfExit;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USR_ID", referencedColumnName = "ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESIG_ID", referencedColumnName = "ID")
    private Designation designation;

    @Override
    public void injectParent(User linkedParent) {
        this.user = linkedParent;
    }

    @Override
    public User extractParent() {
        return this.user;
    }
}
