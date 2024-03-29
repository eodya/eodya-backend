package com.eodya.api.fixture.domain;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;

public class AddressDepthFixture {

    public static AddressDepth1 addressDepth1Build() {
        String addressDepth1Name = "서울시";

        return AddressDepth1.builder()
                .name(addressDepth1Name)
                .build();
    }

    public static AddressDepth2 addressDepth2Build() {
        AddressDepth1 addressDepth1 = addressDepth1Build();
        String addressDepth2Name = "강남구";

        return AddressDepth2.builder()
                .name(addressDepth2Name)
                .addressDepth1(addressDepth1)
                .build();
    }
}
