package com.pgsoft.service.impl.payment.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QPaymentDtls;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class PaymentDtlsBinding implements QuerydslBinderCustomizer<QPaymentDtls> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QPaymentDtls qPaymentDtls) {
        BindingHelper.bindMultiId(querydslBindings, qPaymentDtls.user.id, "userId");
        BindingHelper.bindMultiString(querydslBindings, qPaymentDtls.sts, "status");
        BindingHelper.bindMultiId(querydslBindings, qPaymentDtls.paymentType.id, "paymentTypeId");
        BindingHelper.bindMultiString(querydslBindings, qPaymentDtls.paymentType.code, "paymentType");
        BindingHelper.bindMultiString(querydslBindings, qPaymentDtls.paymentResponseDtls.rspCd, "paymentResponse");
        BindingHelper.bindMultiId(querydslBindings, qPaymentDtls.user.userBranch.branch.id, "branchId");
        BindingHelper.bindDateRange(querydslBindings, qPaymentDtls.paymentDt, "date");
        /*querydslBindings.bind(qPaymentDtls.paymentDt)
                .as("date")
                .all((path, value) -> {
                    List<? extends Date> dates = new ArrayList<>(value);
                    if (dates.size() == 1) {
                        return Optional.of(path.eq(dates.get(0)));
                    } else {
                        Date from = dates.get(0);
                        Date to = dates.get(1);
                        return Optional.of(path.between(from, to));
                    }
                });*/
    }
}
