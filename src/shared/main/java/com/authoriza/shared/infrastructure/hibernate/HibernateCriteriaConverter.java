package com.authoriza.shared.infrastructure.hibernate;

import com.authoriza.shared.domain.criteria.Criteria;
import com.authoriza.shared.domain.criteria.Filter;
import com.authoriza.shared.domain.criteria.FilterOperator;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class HibernateCriteriaConverter<T> {
    private final CriteriaBuilder builder;
    private final HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>> predicateTransformers = new HashMap<>() {{
        put(FilterOperator.EQUAL, HibernateCriteriaConverter.this::equalsPredicateTransformer);
        put(FilterOperator.NOT_EQUAL, HibernateCriteriaConverter.this::notEqualsPredicateTransformer);
        put(FilterOperator.GT, HibernateCriteriaConverter.this::greaterThanPredicateTransformer);
        put(FilterOperator.LT, HibernateCriteriaConverter.this::lowerThanPredicateTransformer);
        put(FilterOperator.CONTAINS, HibernateCriteriaConverter.this::containsPredicateTransformer);
        put(FilterOperator.NOT_CONTAINS, HibernateCriteriaConverter.this::notContainsPredicateTransformer);
    }};

    public HibernateCriteriaConverter(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public CriteriaQuery<T> convert(Criteria criteria, Class<T> aggregateClass) {
        CriteriaQuery<T> hibernateCriteria = builder.createQuery(aggregateClass);
        Root<T>          root              = hibernateCriteria.from(aggregateClass);

        hibernateCriteria.where(formatPredicates(criteria.filters().filters(), root));

        if (criteria.order().hasOrder()) {
            Path<Object> orderBy = root.get(criteria.order().orderBy().getValue());
            Order        order   = criteria.order().orderType().isAsc() ? builder.asc(orderBy) : builder.desc(orderBy);

            hibernateCriteria.orderBy(order);
        }

        return hibernateCriteria;
    }

    private Predicate[] formatPredicates(List<Filter> filters, Root<T> root) {
        List<Predicate> predicates = filters.stream().map(filter -> formatPredicate(
                filter,
                root
        )).collect(Collectors.toList());

        Predicate[] predicatesArray = new Predicate[predicates.size()];
        predicatesArray = predicates.toArray(predicatesArray);

        return predicatesArray;
    }

    private Predicate formatPredicate(Filter filter, Root<T> root) {
        BiFunction<Filter, Root<T>, Predicate> transformer = predicateTransformers.get(filter.operator());

        return transformer.apply(filter, root);
    }

    private Predicate equalsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.equal(root.get(filter.field().getValue()), filter.value().getValue());
    }

    private Predicate notEqualsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.notEqual(root.get(filter.field().getValue()), filter.value().getValue());
    }

    private Predicate greaterThanPredicateTransformer(Filter filter, Root<T> root) {
        return builder.greaterThan(root.get(filter.field().getValue()), filter.value().getValue());
    }

    private Predicate lowerThanPredicateTransformer(Filter filter, Root<T> root) {
        return builder.lessThan(root.get(filter.field().getValue()), filter.value().getValue());
    }

    private Predicate containsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.like(root.get(filter.field().getValue()), String.format("%%%s%%", filter.value().getValue()));
    }

    private Predicate notContainsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.notLike(root.get(filter.field().getValue()), String.format("%%%s%%", filter.value().getValue()));
    }
}
