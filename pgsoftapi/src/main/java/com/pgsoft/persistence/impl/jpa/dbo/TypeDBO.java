package com.pgsoft.persistence.impl.jpa.dbo;

import org.slf4j.Logger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@MappedSuperclass
public abstract class TypeDBO implements IPgSoftDBO {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TypeDBO.class);

    public TypeDBO(@NotNull String code) {
        this.code = code;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CD", nullable = false)
    private String code;

    @java.beans.ConstructorProperties({"id", "code"})
    public TypeDBO(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public TypeDBO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TypeDBO)) return false;
        final TypeDBO other = (TypeDBO) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if ((this$id == null && other$id != null) || !Objects.equals(this$id, other$id)) return false;
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        return (this$code == null && other$code == null) || Objects.equals(this$code, other$code);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TypeDBO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $code = this.getCode();
        result = result * PRIME + ($code == null ? 43 : $code.hashCode());
        return result;
    }

    public String toString() {
        return "TypeDBO(id=" + this.getId() + ", code=" + this.getCode() + ")";
    }
}
