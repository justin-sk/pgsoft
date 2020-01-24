package com.pgsoft.service.helper;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BindingHelper {
    public static void bindMultiBoolean(@NotNull QuerydslBindings querydslBindings, @NotNull BooleanPath booleanPath) {
        BindingHelper.bindMultiBoolean(querydslBindings, booleanPath, null);
    }

    public static void bindMultiBoolean(@NotNull QuerydslBindings querydslBindings, @NotNull BooleanPath booleanPath, @Nullable String alias) {
        QuerydslBindings.AliasingPathBinder<@NotNull BooleanPath, Boolean> binding = querydslBindings.bind(booleanPath);
        if (!StringUtils.isEmpty(alias)) {
            binding = binding.as(alias);
        }
        binding.all((path, value) -> {
            final BooleanBuilder predicate = new BooleanBuilder();
            value.forEach(val -> predicate.or(path.eq(val)));
            return Optional.of(predicate);
        });
    }

    public static void bindMultiString(@NotNull QuerydslBindings querydslBindings, @NotNull StringPath stringPath) {
        BindingHelper.bindMultiString(querydslBindings, stringPath, null);
    }

    public static void bindMultiString(@NotNull QuerydslBindings querydslBindings, @NotNull StringPath stringPath, @Nullable String alias) {
        QuerydslBindings.AliasingPathBinder<@NotNull StringPath, String> binding = querydslBindings.bind(stringPath);
        if (!StringUtils.isEmpty(alias)) {
            binding = binding.as(alias);
        }
        binding.all((path, value) -> {
            final BooleanBuilder predicate = new BooleanBuilder();
            value.forEach(val -> predicate.or(BindingHelper.expandMultiString(path, val)));
            return Optional.of(predicate);
        });
    }

    private static Predicate expandMultiString(@NotNull StringPath stringPath, @NotNull String values) {
        final BooleanBuilder predicate = new BooleanBuilder();
        Arrays.stream(values.split(",")).forEach(val -> predicate.and(stringPath.containsIgnoreCase(val)));
        return predicate;
    }

    public static void bindRange(@NotNull QuerydslBindings querydslBindings, @NotNull NumberPath<?> numberPath, @Nullable String minAlias, @Nullable String maxAlias) {
        if (StringUtils.isEmpty(minAlias) && StringUtils.isEmpty(maxAlias)) {
            return;
        }
        final BooleanBuilder predicate = new BooleanBuilder();
        if (!StringUtils.isEmpty(minAlias)) {
            querydslBindings.bind(numberPath).as(minAlias).first((path, value) -> predicate.and(path.goe(value)));
        }
        if (!StringUtils.isEmpty(maxAlias)) {
            querydslBindings.bind(numberPath).as(maxAlias).first((path, value) -> predicate.and(path.loe(value)));
        }
    }

    public static void bindRange(@NotNull QuerydslBindings querydslBindings, @NotNull DatePath<?> datePath, @Nullable String minAlias, @Nullable String maxAlias) {
        if (StringUtils.isEmpty(minAlias) && StringUtils.isEmpty(maxAlias)) {
            return;
        }
        final BooleanBuilder predicate = new BooleanBuilder();
        if (!StringUtils.isEmpty(minAlias)) {
            querydslBindings.bind(datePath).as(minAlias).first((path, value) -> predicate.and(path.goe(value)));
        }
        if (!StringUtils.isEmpty(maxAlias)) {
            querydslBindings.bind(datePath).as(maxAlias).first((path, value) -> predicate.and(path.loe(value)));
        }
    }

    public static void bindMultiId(@NotNull QuerydslBindings querydslBindings, @NotNull NumberPath<Long> longNumberPath) {
        BindingHelper.bindMultiId(querydslBindings, longNumberPath, null);
    }

    public static void bindMultiId(@NotNull QuerydslBindings querydslBindings, @NotNull NumberPath<Long> longNumberPath, @Nullable String alias) {
        QuerydslBindings.AliasingPathBinder<@NotNull NumberPath<Long>, Long> binding = querydslBindings.bind(longNumberPath);
        if (!StringUtils.isEmpty(alias)) {
            binding = binding.as(alias);
        }
        binding.all((path, values) -> {
            final BooleanBuilder predicate = new BooleanBuilder();
            values.forEach(val -> {
                if (val == null || val.equals(0L)) {
                    predicate.or(path.isNull());
                } else {
                    predicate.or(path.eq(val));
                }
            });
            return Optional.of(predicate);
        });
    }

    public static void bindDateRange(@NotNull QuerydslBindings querydslBindings, @NotNull DatePath<Date> datePath, @NotNull String alias) {
        querydslBindings.bind(datePath)
                .as(alias)
                .all((path, value) -> {
                    List<? extends Date> dates = new ArrayList<>(value);
                    if (dates.size() == 1) {
                        return Optional.of(path.eq(dates.get(0)));
                    } else {
                        Date from = dates.get(0);
                        Date to = dates.get(1);
                        return Optional.of(path.between(from, to));
                    }
                });
    }
}
