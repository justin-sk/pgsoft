package com.pgsoft.service.impl.branch.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QBranchInfo;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class BranchInfoBinding implements QuerydslBinderCustomizer<QBranchInfo> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QBranchInfo qBranchInfo) {
        BindingHelper.bindMultiId(querydslBindings, qBranchInfo.branch.id, "branchId");
        BindingHelper.bindMultiString(querydslBindings, qBranchInfo.branch.code, "branch");
        BindingHelper.bindMultiId(querydslBindings, qBranchInfo.city.id, "cityId");
        BindingHelper.bindMultiString(querydslBindings, qBranchInfo.city.code, "city");
        BindingHelper.bindMultiBoolean(querydslBindings, qBranchInfo.sts, "sts");
        BindingHelper.bindMultiId(querydslBindings, qBranchInfo.occupancyType.id, "occTypeId");
        BindingHelper.bindMultiString(querydslBindings, qBranchInfo.occupancyType.code, "occType");
    }
}
