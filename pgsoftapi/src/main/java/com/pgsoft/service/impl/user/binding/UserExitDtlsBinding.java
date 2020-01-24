package com.pgsoft.service.impl.user.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QUserExitDtls;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class UserExitDtlsBinding implements QuerydslBinderCustomizer<QUserExitDtls> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QUserExitDtls qUserExitDtls) {
        BindingHelper.bindMultiId(querydslBindings, qUserExitDtls.user.id, "userId");
    }
}
