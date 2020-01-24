package com.pgsoft.service.impl.payment.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QPaymentResponseDtls;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class PaymentResponseDtlsBinding implements QuerydslBinderCustomizer<QPaymentResponseDtls> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QPaymentResponseDtls qPaymentResponseDtls) {
    }
}
