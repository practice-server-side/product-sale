package com.example.product.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RequestListDto {
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int pageSize = 10;

    @Builder.Default
    private String sort = "registerDate";
    @Builder.Default
    private Sort.Direction direction = Sort.Direction.DESC;

    @Builder.Default
    private String searchType = "";
    @Builder.Default
    private String keyword = "";

    private String param;

    public int getPage() {
        page = page - 1;
        if (page < 0) {
            page = 0;
        }
        return page;
    }

}
