package br.com.projetointegrador.store.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {
    private long pageNum;
    private long pageItemsNum;
    private long totalItems;
    private long totalPages;
    private List<T> data;
}
