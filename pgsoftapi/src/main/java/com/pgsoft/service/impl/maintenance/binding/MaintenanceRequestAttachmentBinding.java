package com.pgsoft.service.impl.maintenance.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QMaintenanceRequestAttachment;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class MaintenanceRequestAttachmentBinding implements QuerydslBinderCustomizer<QMaintenanceRequestAttachment> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QMaintenanceRequestAttachment qMaintenanceRequestAttachment) {
    }
}
