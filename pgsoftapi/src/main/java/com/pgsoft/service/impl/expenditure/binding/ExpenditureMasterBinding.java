package com.pgsoft.service.impl.expenditure.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QExpenditureMaster;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class ExpenditureMasterBinding implements QuerydslBinderCustomizer<QExpenditureMaster> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QExpenditureMaster qExpenditureMaster) {
        BindingHelper.bindMultiId(querydslBindings, qExpenditureMaster.branch.id, "branchId");
        BindingHelper.bindMultiId(querydslBindings, qExpenditureMaster.recurrenceType.id, "recTypeId");
        BindingHelper.bindMultiId(querydslBindings, qExpenditureMaster.expenditureCategory.id, "categoryId");
        BindingHelper.bindMultiId(querydslBindings, qExpenditureMaster.user.id, "userId");
        BindingHelper.bindDateRange(querydslBindings, qExpenditureMaster.billDt, "date");
    }
}
