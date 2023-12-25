package com.utils.app.stream;

import com.utils.app.trucking.domain.driving.Driving;
import com.utils.app.trucking.domain.driving.DrivingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream에서 Map 또한 중간연산으로써, 최종 연산과 함께 활용이 가능한 stream api 중 하나이다.
 * 쉽게 이야기하면 mapping해서 원하는 데이터만 뽑는 등의 방법으로 사용 가능하다.
 */
@Service
@RequiredArgsConstructor
public class StreamMap {

    private final DrivingRepository drivingRepository;

    private void map() {
        List<Driving> drivingList = drivingRepository.findAll();

        /**
         * List<Driving> driving list에서 Driving id만 뽑아오고 싶은 경우에는 중간연산 map을 활용하여 가져올 수 있음
         */
        List<String> drivingIdLists = drivingList.stream().map(Driving::getDrivingId).collect(Collectors.toList());



    }
}
