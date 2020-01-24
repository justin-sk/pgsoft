package com.pgsoft.service.impl.user.binding;

import com.pgsoft.persistence.impl.jpa.dbo.QUserSuggestion;
import com.pgsoft.service.helper.BindingHelper;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public class UserSuggestionBinding implements QuerydslBinderCustomizer<QUserSuggestion> {
    @Override
    public void customize(@NotNull QuerydslBindings querydslBindings, @NotNull QUserSuggestion qUserSuggestion) {
        BindingHelper.bindMultiId(querydslBindings, qUserSuggestion.user.id, "userId");
        BindingHelper.bindMultiId(querydslBindings, qUserSuggestion.category.id, "categoryId");
        BindingHelper.bindMultiString(querydslBindings, qUserSuggestion.category.code, "category");
        BindingHelper.bindMultiId(querydslBindings, qUserSuggestion.subCategory.id, "subCategoryId");
        BindingHelper.bindMultiString(querydslBindings, qUserSuggestion.subCategory.code, "subCategory");
    }
}
