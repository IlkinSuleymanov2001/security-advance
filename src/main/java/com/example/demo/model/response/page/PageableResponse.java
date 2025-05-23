package com.example.demo.model.response.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableResponse<R> {

    private List<R> data;
    private long totalElements;
    private int lastPageNumber;
    private boolean hasNextPage;

}
