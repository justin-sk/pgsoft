package com.pgsoft.service.impl.menu.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QMenuSubMenuReference;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class MenuSubMenuBinding implements QuerydslBinderCustomizer<QMenuSubMenuReference> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QMenuSubMenuReference qMenuSubMenuReference) {
    }
}
