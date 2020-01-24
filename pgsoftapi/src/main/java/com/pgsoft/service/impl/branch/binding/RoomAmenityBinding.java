package com.pgsoft.service.impl.branch.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QRoomAmenity;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class RoomAmenityBinding implements QuerydslBinderCustomizer<QRoomAmenity> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QRoomAmenity qRoomAmenity) {
        BindingHelper.bindMultiId(querydslBindings, qRoomAmenity.room.id, "roomId");
        BindingHelper.bindMultiId(querydslBindings, qRoomAmenity.amenity.id, "amenityId");
    }
}