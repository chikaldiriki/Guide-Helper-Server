package server.specifications;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class SearchCriteria {

    private String key;
    private SearchOperation operation;
    private List<Object> arguments;

    public SearchCriteria(String key, String operation, Object argument) {
        this.key = key;
        this.operation = SearchOperation.fromValue(operation);
        this.arguments = Collections.singletonList(argument);
    }
}
