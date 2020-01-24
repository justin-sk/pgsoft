package com.pgsoft.service.impl.notification.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QNotification;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class NotificationBinding implements QuerydslBinderCustomizer<QNotification> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QNotification qNotification) {
        BindingHelper.bindMultiId(querydslBindings, qNotification.user.id, "userId");
        BindingHelper.bindMultiId(querydslBindings, qNotification.branch.id, "branchId");
        BindingHelper.bindMultiId(querydslBindings, qNotification.recurrenceType.id, "recTypeId");
    }
}
