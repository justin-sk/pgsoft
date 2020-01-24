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
@Table(name = "USR_RENT")
public class UserRent implements IPgSoftDBO, IParentInjectableDBO<User> {
    @Id
    private Long Id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID", referencedColumnName = "ID", nullable = false)
    private User user;

    @Override
    public void injectParent(User linkedParent) {
        this.user = linkedParent;
    }

    @Override
    public User extractParent() {
        return this.user;
    }
}
