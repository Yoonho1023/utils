package com.utils.app.trucking.domain.driving;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Driving {

    @Id
    @Column(name = "driving_id")
    private String drivingId;

    @Column(name = "route_id")
    private String routeId;

    @Column(name = "trucking_no")
    private String truckingNo;

    @Column(name = "route_group_id")
    private String routeGroupId;

    @Column(name = "driving_dt")
    private String drivingDt;

    @Column(name = "driving_stus_cd")
    @Enumerated(EnumType.STRING)
    private DrivingStsType drivingStatus;

    @Column(name = "settle_org_cd")
    private String settlementOrgCd;

    @Column(name = "distance")
    private int distance;

    @Column(name = "vhcl_in_tm")
    private String vehicleInTm;

    @Column(name = "vhcl_ask_in_tm")
    private String vehicleReqInTm;

}
