package com.leandrofita.generic_specification_builder_example.specification.builder;


import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Component
public class DefaultSpecificationBuilder implements DefaultSpecificationBuilderInterface {


    @Override
    public <T> Specification<T> equals(String field, Object value) {
        return (root, query, cb) ->
                value == null ? null : cb.equal(root.get(field), value);
    }

    @Override
    public <T> Specification<T> like(String field, String value) {
        return (root, query, cb) ->
                !StringUtils.hasText(value) ? null :
                        cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }

    public <T> Specification<T> betweenOffsetDateTime(String field, OffsetDateTime start, OffsetDateTime end) {
        return between(field, start, end);
    }

    public <T> Specification<T> betweenLocalDates(String field, LocalDate start, LocalDate end) {
        return between(field, start, end);
    }

    private <T, V extends Comparable<? super V>> Specification<T> between(String field, V start, V end) {
        return (root, query, cb) -> {
            if (start == null && end == null) return null;
            if (start != null && end != null) return cb.between(root.get(field), start, end);
            if (start != null) return cb.greaterThanOrEqualTo(root.get(field), start);
            return cb.lessThanOrEqualTo(root.get(field), end);
        };
    }

    @Override
    public  <T> Specification<T> nestedJoinEquals(String pathString, Object value) {
        return (root, query, cb) -> {
            if (value == null || pathString == null || pathString.isEmpty()) {
                return null;
            }

            Path<?> finalPath = resolvePath(root, pathString, JoinType.INNER);

            return cb.equal(finalPath, value);
        };
    }

    @Override
    public  <T> Specification<T> nestedJoinLike(String pathString, String value) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(value) || pathString == null || pathString.isEmpty()) {
                return null;
            }

            Path<?> path = resolvePath(root, pathString, JoinType.INNER);

            lancarExcecaoCasoOCampoNaoSejaString(pathString, path);

            Path<String> finalPath = (Path<String>) path;

            return cb.like(cb.lower(finalPath), "%" + value.toLowerCase() + "%");
        };
    }

    public <T> Specification<T> nestedCollectionJoinLike(String pathString, String value) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(value)) return null;
            query.distinct(true);
            Path<?> path = resolvePath(root, pathString, JoinType.LEFT);
            return cb.like(cb.lower((Path<String>) path), "%" + value.toLowerCase() + "%");
        };
    }

    public <T> Specification<T> nestedCollectionJoinEquals(String pathString, String value) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(value)) return null;
            query.distinct(true);
            Path<?> path = resolvePath(root, pathString, JoinType.LEFT);
            return cb.equal(cb.lower((Path<String>) path), "%" + value.toLowerCase() + "%");
        };
    }

    public <T> Specification<T> betweenLocalDatesCollection(String field, LocalDate start, LocalDate end) {
        return betweenNestedCollection(field, start, end);
    }

    private <T, V extends Comparable<? super V>> Specification<T> betweenNestedCollection(
            String pathString, V start, V end) {

        return (root, query, cb) -> {
            if ((start == null && end == null) || pathString == null || pathString.isEmpty()) {
                return null;
            }

            query.distinct(true); // evita duplicatas por joins em coleções

            // resolve o caminho com LEFT JOIN
            Path<?> path = resolvePath(root, pathString, JoinType.LEFT);

            Path<V> finalPath = (Path<V>) path;

            if (start != null && end != null) {
                return cb.between(finalPath, start, end);
            } else if (start != null) {
                return cb.greaterThanOrEqualTo(finalPath, start);
            } else {
                return cb.lessThanOrEqualTo(finalPath, end);
            }
        };
    }


    private static void lancarExcecaoCasoOCampoNaoSejaString(String pathString, Path<?> path) {
        if (!String.class.isAssignableFrom(path.getJavaType())) {
            throw new IllegalArgumentException("O campo [" + pathString + "] não é String. LIKE só pode ser usado em Strings.");
        }
    }

    private Path<?> resolvePath(Root<?> root, String pathString, JoinType joinType) {

        String[] parts = pathString.split("\\.");

        From<?, ?> join = root;

        for (int i = 0; i < parts.length - 1; i++) {
            join = join.join(parts[i], joinType);
        }
        return join.get(parts[parts.length - 1]);
    }
}

