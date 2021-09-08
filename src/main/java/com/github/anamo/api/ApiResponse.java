package com.github.anamo.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
final public class ApiResponse<T> {
    public List<Question> items;

    @JsonProperty("has_more")
    public boolean hasMore;

    @JsonProperty("quota_max")
    public int quotaMax;

    @JsonProperty("quota_remaining")
    public int quotarRmaining;

    @Override
    public String toString() {
        return "ApiResponse{" +
                "items=" + items +
                ", hasMore=" + hasMore +
                ", quotaMax=" + quotaMax +
                ", quotarRmaining=" + quotarRmaining +
                '}';
    }
}
