package server.specifications;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {

    private final SearchCriteria searchCriteria;


    public GenericSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public GenericSpecification(String key, String operation, List<Object> args) {
        searchCriteria = new SearchCriteria(key, operation, args);
    }

    public GenericSpecification(String key, String operation, Object argument) {
        searchCriteria = new SearchCriteria(key, operation, argument);
    }


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Object> arguments = searchCriteria.getArguments();

        if (arguments.isEmpty()) {
            throw new IllegalStateException("Arguments list is empty");
        }

        Object arg = arguments.get(0);

        switch (searchCriteria.getOperation()) {
            case EQUAL:
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), arg);

            case NOT_EQUAL:
                return criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), arg);

            case GREATER_THAN:
                return criteriaBuilder.greaterThan(root.get(searchCriteria.getKey()), arg.toString()); //(Comparable)

            case GREATER_THAN_OR_EQUAL_TO:
                return criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()), arg.toString());

            case LESS_THAN:
                return criteriaBuilder.lessThan(root.get(searchCriteria.getKey()), arg.toString());

            case LESS_THAN_OR_EQUAL_TO:
                return criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()), arg.toString());

            case IN:
                return root.get(searchCriteria.getKey()).in(arguments);

            case NOT_IN:
                return root.get(searchCriteria.getKey()).in(arguments).not();

            case BETWEEN:
                return criteriaBuilder.
                        between(root.get(searchCriteria.getKey()), arg.toString(), arguments.get(1).toString());

            case CONTAINS:
                return criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + arg + "%");

            default:
                return null;
        }

    }
 }
