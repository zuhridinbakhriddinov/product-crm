package com.example.task.model.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomPage<T> {

    private List<T> data;
    @JsonProperty(value = "pagination_data")
    private PaginationData paginationData;

    public static <T> CustomPage<T> genericPaginationResponse(Page<T> t){
        List<T> tList = t.getContent();
        return CustomPage.<T>builder()
                .paginationData(
                        PaginationData.builder()
                                .numberOfElements(t.getNumberOfElements())
                                .totalElements(t.getTotalElements())
                                .totalPages(t.getTotalPages())
                                .pageSize(t.getSize())
                                .currentPageNumber(t.getNumber())
                                .build()
                )
                .data(tList)
                .build();
    }
}
