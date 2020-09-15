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
        this.page = (page < 0) ? 1 : (page > totalPage) ? totalPage : page;
        pages.add(this.page);
        for (int i = 1; i <= 3; i++) {
            if (this.page - i > 0) {
                pages.add(0, page - i);
            }
            if (this.page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        showPrevious = (page == 1) ? false : true;
        showNext = (page == totalPage) ? false : true;
        showFirstPage = (!pages.contains(1)) ? true : false;
        showEndPage = (!pages.contains(totalPage)) ? true : false;
    }
}
