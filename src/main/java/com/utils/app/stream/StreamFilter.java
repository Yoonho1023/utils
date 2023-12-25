package com.utils.app.stream;

import com.utils.app.trucking.domain.driving.Driving;
import com.utils.app.trucking.domain.driving.DrivingRepository;
import com.utils.app.trucking.domain.driving.DrivingStsType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Filter는 Stream의 중간 연산으로,
 * 말그대로 필터역할을 하는 것이다.
 * 다양한 Stream api의 block들과 활용하여서 사용가능함 (map으로 데이터를 선별해서 filter에서 또 한번 거른 뒤 List로 변환하여 return 등..)
 */
@Service
@RequiredArgsConstructor
public class StreamFilter {

    private final DrivingRepository drivingRepository;

    private void filter() {
        List<Driving> drivingList = drivingRepository.findAll();

        /**
         * 운행상태코드가 standby인 것들을 모아 Stream 형태로 return
         */
        Stream<Driving> stream =drivingList.stream().filter(d -> d.getDrivingStatus().equals(DrivingStsType.standby));

        /**
         * 운행상태코드가 done인것들을 List<Driving> 형태로 최종연산인 Collect를 활용하여 List형식으로 결과값을 return
         */
        List<Driving> doneList = drivingList.stream().filter(driving -> driving.getDrivingStatus().equals(DrivingStsType.done)).collect(Collectors.toList());

        /**
         * 운행거리가 100이상인 것들을 DrivingId를 key로하고 Driving 객체를 value로 하는 Map을 만들어 return
         */
        Map<String, Driving> drivingMap = drivingList.stream().filter(d -> d.getDistance() >= 100).collect(Collectors.toMap(Driving::getDrivingId, Function.identity()));
    }
}
