package com.pgsoft.service.impl.maintenance.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QMaintenanceRequestAssignTo;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class MaintenanceRequestAssignToBinding implements QuerydslBinderCustomizer<QMaintenanceRequestAssignTo> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QMaintenanceRequestAssignTo qMaintenanceRequestAssignTo) {
    }
}
