package com.pgsoft.service.impl.expenditure.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QExpenditure;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class ExpenditureBinding implements QuerydslBinderCustomizer<QExpenditure> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QExpenditure qExpenditure) {
        BindingHelper.bindMultiId(querydslBindings, qExpenditure.branch.id, "branchId");
        BindingHelper.bindMultiId(querydslBindings, qExpenditure.user.id, "userId");
        BindingHelper.bindMultiId(querydslBindings, qExpenditure.expenditureCategory.id, "categoryId");
        BindingHelper.bindDateRange(querydslBindings, qExpenditure.billDt, "date");
    }
}
