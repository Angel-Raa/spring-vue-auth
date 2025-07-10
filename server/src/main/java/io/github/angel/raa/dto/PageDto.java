package io.github.angel.raa.dto;

import java.util.List;

public record PageDto<T>(List<T> content,

        long totalElements,
        int number,
        int size) {

}
