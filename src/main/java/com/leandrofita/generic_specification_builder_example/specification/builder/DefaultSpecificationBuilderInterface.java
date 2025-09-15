package com.leandrofita.generic_specification_builder_example.specification.builder;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public interface DefaultSpecificationBuilderInterface {
    <T> Specification<T> equals(String field, Object value);

    <T> Specification<T> like(String field, String value);

    <T> Specification<T> betweenOffsetDateTime(String field, OffsetDateTime start, OffsetDateTime end);

    <T> Specification<T> betweenLocalDates(String field, LocalDate startDate, LocalDate endDate);

    <T> Specification<T> nestedJoinLike(String pathString, String value);

    <T> Specification<T> nestedJoinEquals(String pathString, Object value);

    <T> Specification<T> nestedCollectionJoinEquals(String pathString, String value);

    <T> Specification<T> nestedCollectionJoinLike(String pathString, String value);

    <T> Specification<T> betweenLocalDatesCollection(String field, LocalDate start, LocalDate end);
}
