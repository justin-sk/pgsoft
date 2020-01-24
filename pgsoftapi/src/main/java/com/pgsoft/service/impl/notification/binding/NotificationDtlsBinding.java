package com.pgsoft.service.impl.notification.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QNotificationDtls;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class NotificationDtlsBinding implements QuerydslBinderCustomizer<QNotificationDtls> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QNotificationDtls qNotificationDtls) {
        BindingHelper.bindMultiId(querydslBindings, qNotificationDtls.notification.id, "notificationId");
        BindingHelper.bindMultiBoolean(querydslBindings, qNotificationDtls.sentSts, "sent");
        BindingHelper.bindDateRange(querydslBindings, qNotificationDtls.dt, "date");
    }
}
