package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "BED_TYPE")
public class BedType extends TypeDBO {
}
