package com.github.holyloop.jencode.web.controller.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationWrapper<T> {

    private int total;

    private List<T> list;

}
