package server.specifications;

import java.util.stream.Stream;

public enum SearchOperation {

    EQUAL("eq"),
    NOT_EQUAL("neg"),
    GREATER_THAN("gt"),
    GREATER_THAN_OR_EQUAL_TO("gte"),
    LESS_THAN("lt"),
    LESS_THAN_OR_EQUAL_TO("lte"),
    IN("in"),
    NOT_IN("nin"),
    BETWEEN("btn"),
    CONTAINS("like");

    private final String value;

    SearchOperation(String value) {
        this.value = value;
    }

    public static SearchOperation fromValue(String value) {
        return Stream.of(values())
                .filter(op -> String.valueOf(op.value).equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
