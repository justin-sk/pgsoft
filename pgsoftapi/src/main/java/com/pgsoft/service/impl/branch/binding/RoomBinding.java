package com.pgsoft.service.impl.branch.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QRoom;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class RoomBinding implements QuerydslBinderCustomizer<QRoom> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QRoom qRoom) {
        BindingHelper.bindMultiId(querydslBindings, qRoom.branch.id, "branchId");
        BindingHelper.bindMultiId(querydslBindings, qRoom.roomType.id, "roomTypeId");
        BindingHelper.bindMultiString(querydslBindings, qRoom.roomType.code, "roomTypeId");
        BindingHelper.bindMultiBoolean(querydslBindings, qRoom.sts, "sts");
    }
}
