package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.PaymentType;
import com.pgsoft.web.rest.v1.pgsoft.type.PaymentTypeResource;
import org.springframework.stereotype.Component;

@Component
public class PaymentTypeMapper extends TypeMapperAdapter<PaymentType> {
    public PaymentTypeMapper() {
        super(PaymentTypeResource.class);
    }
}