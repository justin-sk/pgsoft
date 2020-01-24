package com.pgsoft.service.impl.employee.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QEmployee;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class EmployeeBinding implements QuerydslBinderCustomizer<QEmployee> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QEmployee qEmployee) {
        BindingHelper.bindMultiString(querydslBindings, qEmployee.emplNo, "num");
        BindingHelper.bindMultiId(querydslBindings, qEmployee.designation.id, "designationId");
        BindingHelper.bindMultiId(querydslBindings, qEmployee.user.userRole.role.id, "roleId");
        BindingHelper.bindMultiId(querydslBindings, qEmployee.user.userBranch.branch.id, "branchId");
        BindingHelper.bindMultiBoolean(querydslBindings, qEmployee.user.sts, "active");
        querydslBindings.bind(qEmployee.dateOfExit).as("inactive").first((path, value) -> path.isNotNull());
    }
}
