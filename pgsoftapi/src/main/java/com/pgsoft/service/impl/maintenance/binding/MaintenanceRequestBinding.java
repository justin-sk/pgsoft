package com.pgsoft.service.impl.maintenance.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QMaintenanceRequest;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class MaintenanceRequestBinding implements QuerydslBinderCustomizer<QMaintenanceRequest> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QMaintenanceRequest qMaintenanceRequest) {
        BindingHelper.bindMultiId(querydslBindings, qMaintenanceRequest.user.id, "userId");
        BindingHelper.bindMultiId(querydslBindings, qMaintenanceRequest.category.id, "categoryId");
        BindingHelper.bindMultiString(querydslBindings, qMaintenanceRequest.category.code, "category");
        BindingHelper.bindMultiId(querydslBindings, qMaintenanceRequest.subCategory.id, "subCategoryId");
        BindingHelper.bindMultiString(querydslBindings, qMaintenanceRequest.subCategory.code, "subCategory");
        BindingHelper.bindMultiId(querydslBindings, qMaintenanceRequest.status.id, "statusId");
        BindingHelper.bindMultiBoolean(querydslBindings, qMaintenanceRequest.permToEnter, "permToEnter");
        BindingHelper.bindMultiId(querydslBindings, qMaintenanceRequest.user.userBranch.branch.id, "branchId");
        BindingHelper.bindDateRange(querydslBindings, qMaintenanceRequest.requestDate, "date");
    }
}
