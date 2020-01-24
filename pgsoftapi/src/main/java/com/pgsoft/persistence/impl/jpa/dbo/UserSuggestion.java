package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "USR_SUGGESTION")
public class UserSuggestion implements IPgSoftDBO, IParentInjectableDBO<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "[DESC]")
    private String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTG_ID", referencedColumnName = "ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_CTG_ID", referencedColumnName = "ID")
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
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
