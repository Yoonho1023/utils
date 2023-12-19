package com.utils.app.common.pair;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class CommonCode {

    @Id
    @Column(name = "cd")
    private String cd;

    @Column(name = "cd_kor")
    private String cdKor;

    @Column(name = "cd_usa")
    private String cdUsa;

    @Column(name = "cd_desc")
    private String cdDesc;

    @Column(name = "cd_seq")
    private String cdSeq;

    @Column(name = "use_yn")
    private String useYN;

    // 등등..
}
