package com.pgsoft.service.impl.branch.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QBranchAmenity;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class BranchAmenityBinding implements QuerydslBinderCustomizer<QBranchAmenity> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QBranchAmenity qBranchAmenity) {
        BindingHelper.bindMultiId(querydslBindings, qBranchAmenity.branch.id, "branchId");
    }
}