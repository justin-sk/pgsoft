package com.pgsoft.service.impl.category.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QCategorySubCategoryReference;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class CategorySubCategoryReferenceBinding implements QuerydslBinderCustomizer<QCategorySubCategoryReference> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QCategorySubCategoryReference qCategorySubCategoryReference) {
        BindingHelper.bindMultiString(querydslBindings, qCategorySubCategoryReference.category.code, "category");
    }
}

