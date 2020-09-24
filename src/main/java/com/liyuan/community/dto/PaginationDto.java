package com.liyuan.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDto {
    private List<QuestionDto> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showEndPage;
    private boolean showNext;
    private int page;
    private int totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(int totalCount, int page, int size) {
        int totalPage = (totalCount % size == 0) ? (totalCount / size) : (totalCount / size + 1);
        this.totalPage = totalPage;
        page = (page <= 0) ? 1 : (totalPage > 0) ? Math.min(page, totalPage) : 1;
        this.page = page;
        this.pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                this.pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                this.pages.add(page + i);
            }
        }
        showPrevious = page > 1;
        showNext = page < totalPage && page > 0;
        showFirstPage = !this.pages.contains(1);
        showEndPage = !this.pages.contains(totalPage) && totalPage > 0;
    }
}
