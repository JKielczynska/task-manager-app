package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class TaskDto {
    private Long id;
    private String title;
    private String content;

    public void setId(final Long id) {
        this.id = id;
    }
}
