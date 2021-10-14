package org.kumar.primestarter.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.internal.QueryImpl;
import org.kumar.primestarter.entity.Product;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findProductsWithPaginationFilteringAndSorting(int first, int pageSize,
            Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        List<Predicate> predicates = getPredicates(filterBy, criteriaBuilder, productRoot);

        if (!predicates.isEmpty()) {
            Predicate andPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(andPredicate);
        }

        List<Order> orders = new ArrayList<>();

        if (sortBy.isEmpty()) {
            orders.add(criteriaBuilder.asc(productRoot.get("id")));
        } else {
            for (SortMeta entry : sortBy.values()) {
                Order order = null;
                if (entry.getOrder().isDescending()) {
                    order = criteriaBuilder.desc(productRoot.get(entry.getField()));
                }
                if (entry.getOrder().isAscending()) {
                    order = criteriaBuilder.asc(productRoot.get(entry.getField()));
                }
                orders.add(order);
            }
        }

        criteriaQuery.orderBy(orders);

        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);

        String textQuery = query.unwrap(QueryImpl.class).getQueryString();

        List<Product> resultList = query.getResultList();

        return resultList;
    }

    private List<Predicate> getPredicates(Map<String, FilterMeta> filters, CriteriaBuilder criteriaBuilder,
            Root<Product> productRoot) {
        List<Predicate> predicates = new ArrayList<>();

        for (FilterMeta entry : filters.values()) {

            String filterField = entry.getField();

            if ("id".equals(filterField)) {
                Integer filterBy = Integer.parseInt((String) entry.getFilterValue());
                Predicate idPredicate = criteriaBuilder.equal(productRoot.get("id"), filterBy);
                predicates.add(idPredicate);
            }

            if ("productName".equals(filterField)) {
                String filterBy = (String) entry.getFilterValue();
                Predicate firstNamePredicate =
                        criteriaBuilder.like(criteriaBuilder.lower(productRoot.get("productName")), filterBy + "%");
                predicates.add(firstNamePredicate);
            }

            if ("listPrice".equals(filterField)) {
                BigDecimal filterBy = new BigDecimal((String) entry.getFilterValue());
                Predicate firstNamePredicate = criteriaBuilder.equal(productRoot.get("listPrice"), filterBy);
                predicates.add(firstNamePredicate);
            }

        }

        return predicates;
    }

}
