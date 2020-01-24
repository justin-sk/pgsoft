package com.pgsoft.service.impl.maintenance.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QMaintenanceRequestFee;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class MaintenanceRequestFeeBinding implements QuerydslBinderCustomizer<QMaintenanceRequestFee> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QMaintenanceRequestFee qMaintenanceRequestFee) {
        BindingHelper.bindMultiId(querydslBindings, qMaintenanceRequestFee.maintenanceRequest.id);
    }
}
