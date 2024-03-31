package com.example.task.model.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PaginationData {
    @JsonProperty(value = "number_of_elements")
    private int numberOfElements;

    @JsonProperty(value = "total_elements")
    private long totalElements;

    @JsonProperty(value = "total_pages")
    private int totalPages;

    @JsonProperty(value = "current_page_number")
    private int currentPageNumber;

    @JsonProperty(value = "page_size")
    private int pageSize;
}
