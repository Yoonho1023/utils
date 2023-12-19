package com.utils.app.common.pair;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PairService {

    private final CommonCodeRepository commonCodeRepository;

    private Map<String, Pair> codeMap = new HashMap<>();

    /**
     * 왜 Pair라는 객체를 만들었는가?
     * 예를들어 하나의 코드이지만 나라마다 다른형식으로 부르는 경우 Entity 객체를 전체 담기에는 불필요한 정보들이 포함되어있고,
     * List<String>으로 담으면 자신은 알지만, 나중에 첫번째가 한국이고, 두번째가 미국이라는 규칙을 한번에 알기 어려운 문제등이 발생할 수 있음.
     *
     * 이에, 아래 예시에서는 Pair라는 객체를 통해서 하나의 코드에 한국, 미국의 코드 한꺼번에 담을 수 있음
     *
     * ex) cd: dprt
     *     cd_kor: 출발
     *     cd_usa: departure
     *
     * ex2) cd: CD9124
     *      cd_kor: 배송완료
     *      cd_usa: delivery done
     *
     * 만약 변경이 잦지 않지만 자주 불러쓰는 공통코드의 경우 CodeMap을 만들어 서버가 뜨기전에
     * @PostConstruct를 통해, 메모리에 올려두고 사용할 수 있음
     */
    @PostConstruct
    private void loadCodeMap() {
        if (!this.codeMap.isEmpty()) {
            return;
        }
        List<CommonCode> commonCodeList = commonCodeRepository.findAll();
        this.codeMap = commonCodeList.stream().collect(Collectors.toMap(
                CommonCode::getCd,
                v -> new Pair(v.getCdKor(), v.getCdUsa()))
        );
    }
}
