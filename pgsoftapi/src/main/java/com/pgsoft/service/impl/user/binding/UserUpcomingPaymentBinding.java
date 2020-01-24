package com.pgsoft.service.impl.user.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QUserUpcomingPayment;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class UserUpcomingPaymentBinding implements QuerydslBinderCustomizer<QUserUpcomingPayment> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QUserUpcomingPayment qUserUpcomingPayment) {
        BindingHelper.bindMultiId(querydslBindings, qUserUpcomingPayment.user.id, "userId");
        BindingHelper.bindMultiId(querydslBindings, qUserUpcomingPayment.paymentType.id, "paymentTypeId");
        BindingHelper.bindMultiString(querydslBindings, qUserUpcomingPayment.paymentType.code, "paymentType");
        BindingHelper.bindDateRange(querydslBindings, qUserUpcomingPayment.date, "date");
    }
}
