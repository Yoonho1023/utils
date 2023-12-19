package com.utils.app.common.pair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Pair {
    private String code;
    private String codeNm;

}
