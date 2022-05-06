package io.peaches.academy.service.edu.entity.vo;

import lombok.Data;

@Data
public class WebCourseQueryVO {
    private static final long serialVersionUID = 1L;
    private String subjectParentId;
    private String subjectId;
    private String buyCountSort;
    private String gmtCreateSort;
    private String priceSort;
    private Integer type; //价格正序：1，价格倒序：2
}
