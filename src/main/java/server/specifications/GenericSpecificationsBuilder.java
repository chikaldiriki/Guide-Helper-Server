package server.specifications;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenericSpecificationsBuilder<T> {

    private final List<SearchCriteria> params = new ArrayList<>();

    public GenericSpecificationsBuilder<T> with(String key, String operation, List<Object> values) {
        params.add(new SearchCriteria(key, operation, values));
        return this;
    }

    public GenericSpecificationsBuilder<T> with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<T> build() {
        if (params.isEmpty()) {
            return null;
        }

        List<Specification<T>> specs = params.stream()
                .map(GenericSpecification<T>::new)
                .collect(Collectors.toList());

        Specification<T> result = specs.get(0);

        //TODO: orPredicate
        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }

        return result;
    }


}
