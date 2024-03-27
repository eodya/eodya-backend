package com.eodya.api.fixture.domain;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;

public class AddressDepthFixture {

    public static AddressDepth1 addressDepth1Build(String name) {
        return AddressDepth1.builder()
                .name(name)
                .build();
    }

    public static AddressDepth2 addressDepth2Build(String name, AddressDepth1 addressDepth1
    ) {
        return AddressDepth2.builder()
                .name(name)
                .addressDepth1(addressDepth1)
                .build();
    }
}
