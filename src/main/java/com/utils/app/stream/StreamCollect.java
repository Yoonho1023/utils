package com.utils.app.stream;

import com.utils.app.common.pair.Pair;
import com.utils.app.trucking.domain.driving.Driving;
import com.utils.app.trucking.domain.driving.DrivingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Stream에서 Collect는 최종 연산으로 Stream형식의 객체를 전달받아서 최종적으로 어떤 format으로 return 할것인지에 대해서 결정하는 api라고 생각하면 쉽다.
 * List, Set, Map.. 등등 여러 가지로 return 할 수 있으며 최종적으로 어떤 shape을 가져갈 것인지 정할 수 있다.
 */
@Service
@RequiredArgsConstructor
public class StreamCollect {

    private final DrivingRepository drivingRepository;

    private void collect() {
        List<Driving> drivingList = drivingRepository.findAll();

        /**
         * drivingList에서 DrivingId를 key, truckingNo와 settlementOrgCd를 value로 하는 Map을 만들고 싶을때
         * Collectors.toMap을 활용하여
         * Driving::getDrivingId로 Key = drivingId,
         * d -> new Pair(d.getTruckingNo(), d.getSettlementOrgCd()로 value에 새로운 Pair객체를 만들어서 truckingNo와 settlementOrgCd를 담음
         */
        Map<String, Pair> pairMap = drivingList.stream().collect(Collectors.toMap(
                Driving::getDrivingId,
                d -> new Pair(d.getTruckingNo(), d.getSettlementOrgCd())
        ));

        /**
         * 아래는 DrivingId를 key로하고 그에 상응하는 Driving 객체를 value로 하는 Map으로 return하는 코드
         * Function.identity()는 무조건 input을 그대로 return하는 함수로 Driving 객체를 그대로 반환함
         */
        Map<String, Driving> drivingMap = drivingList.stream().collect(Collectors.toMap(
                Driving::getDrivingId,
                Function.identity()
        ));

        /**
         * 위에서 만날 수 있는 문제는 toMap에서 map을 만들다가 key가 중복되는 경우가 있을 수 있습니다.
         * 예시에서는 drivingId를 key로 하다보니 겹칠 일이 없겠지만(Entity @Id)
         * 아래와 같이 겹치는 경우에는 중복이 되었을때 어떻게 할것인지에 대해서 mergeFunction을 사용하여 해결할 수 있음
         *
         * (oldVal, newVal) -> newVal은 기존에 존재하던 value 대신 이번의 새로운 value를 덮어씌우겠다는 의미이며,
         *
         * 반대로 (oldVal, newVal) -> oldVal은 기존에 존재하는 value를 계속 keep 하겠다는 의미입니다.
         *
         * 물론 아래와 같이 아예 새로운 value를 넣어줄 수도 있습니다.
         * (oldVal, newVal) -> Driving.builder().build() (깡통 Driving 객체..)
         */
        Map<String, Driving> drivingMapWithMergeFunc = drivingList.stream().collect(Collectors.toMap(
                Driving::getDrivingId,
                Function.identity(),
                (oldVal, newVal) -> Driving.builder().build()
        ));

    }

}
