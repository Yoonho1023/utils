package com.utils.app.trucking.domain.driving;

import lombok.Getter;

@Getter
public enum DrivingStsType {
    standby("운행 대기"), done("운행 완료");

    private String description;

    DrivingStsType(String desc) {
        this.description = desc;
    }

}

