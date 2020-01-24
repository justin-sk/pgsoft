package com.pgsoft.service.impl.user.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QUserBed;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class UserBedBinding implements QuerydslBinderCustomizer<QUserBed> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QUserBed qUserBed) {
    }
}

