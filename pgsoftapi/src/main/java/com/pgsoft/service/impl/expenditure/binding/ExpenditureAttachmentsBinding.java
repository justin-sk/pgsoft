package com.pgsoft.service.impl.expenditure.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QExpenditureAttachments;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class ExpenditureAttachmentsBinding implements QuerydslBinderCustomizer<QExpenditureAttachments> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QExpenditureAttachments qExpenditureAttachments) {
        BindingHelper.bindMultiId(querydslBindings, qExpenditureAttachments.expenditure.id, "expenditureId");
    }
}
