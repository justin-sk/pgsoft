package com.pgsoft.service.impl.amenity.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QAmenity;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class AmenityBinding implements QuerydslBinderCustomizer<QAmenity> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QAmenity qAmenity) {
        BindingHelper.bindMultiString(querydslBindings, qAmenity.code,"code");
        BindingHelper.bindMultiString(querydslBindings, qAmenity.description, "desc");
    }
}
