package com.pgsoft.service.impl.user.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QUserBranch;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class UserBranchBinding implements QuerydslBinderCustomizer<QUserBranch> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QUserBranch qUserBranch) {
    }
}
