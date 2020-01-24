package com.pgsoft.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Relation(collectionRelation = "summary")
public class SummaryDTO implements IPgSoftDTO {
    private String metricName;
    private BigInteger metricValue;

    public SummaryDTO(String name, BigInteger val) {
        this.metricName = name;
        this.metricValue = val;
    }
}
