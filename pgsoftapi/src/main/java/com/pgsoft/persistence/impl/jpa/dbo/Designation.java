package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "DESIG")
@Entity
public class Designation extends TypeDBO {

}
