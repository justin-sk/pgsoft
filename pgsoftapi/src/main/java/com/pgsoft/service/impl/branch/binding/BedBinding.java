package com.pgsoft.service.impl.branch.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QBed;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class BedBinding implements QuerydslBinderCustomizer<QBed> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QBed qBed) {
        BindingHelper.bindMultiId(querydslBindings, qBed.bedType.id, "bedTypeId");
        BindingHelper.bindMultiString(querydslBindings, qBed.bedType.code, "bedType");
        BindingHelper.bindMultiId(querydslBindings, qBed.room.id, "roomId");
        BindingHelper.bindMultiId(querydslBindings, qBed.room.branch.id, "branchId");
    }
}
