package com.pgsoft.service.impl.user.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QUser;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class UserBinding implements QuerydslBinderCustomizer<QUser> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QUser qUser) {
        BindingHelper.bindMultiString(querydslBindings, qUser.nm, "name");
        BindingHelper.bindMultiString(querydslBindings, qUser.email, "email");
        BindingHelper.bindMultiString(querydslBindings, qUser.phone, "phone");
        BindingHelper.bindMultiId(querydslBindings, qUser.userBranch.id, "branchId");
        BindingHelper.bindMultiId(querydslBindings, qUser.userRole.id, "roleId");
        BindingHelper.bindMultiId(querydslBindings, qUser.userBed.id, "bedId");
        BindingHelper.bindMultiBoolean(querydslBindings, qUser.userTc.tcAcpt, "tcAcpt");
        BindingHelper.bindMultiId(querydslBindings, qUser.employee.id, "employeeId");
        BindingHelper.bindMultiString(querydslBindings, qUser.employee.emplNo, "employeeNo");
        querydslBindings.bind(qUser.userExitDtls.exitDt).as("exit").first((path, value) -> path.isNotNull());
    }
}
