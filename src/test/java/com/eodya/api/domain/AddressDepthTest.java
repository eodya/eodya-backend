package com.eodya.api.domain;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;
import com.eodya.api.fixture.domain.AddressDepthFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AddressDepthTest {

    @Test
    @DisplayName("정상적으로 '시'를 생성할 수 있다.")
    void createAddressDepth1_Success() {
        //given
        String city = "서울";
        AddressDepth1 addressDepth1 = AddressDepthFixture.addressDepth1Build(city);

        //when & then
        assertEquals(addressDepth1.getName(), city);
        assertEquals(addressDepth1.getDepth2().size(), 0);
    }

    @Test
    @DisplayName("정상적으로 '구'를 생성할 수 있다.")
    void createAddressDepth2_Success() {
        //given
        String city = "서울";
        AddressDepth1 addressDepth1 = AddressDepthFixture.addressDepth1Build(city);

        String gu = "강남구";
        AddressDepth2 addressDepth2 = AddressDepthFixture.addressDepth2Build(gu, addressDepth1);

        // when & then
        assertNotNull(addressDepth2.getDepth1());
        assertEquals(addressDepth2.getName(), gu);

        // 연관관계 확인
        assertEquals(addressDepth1.getName(), city);
        assertEquals(addressDepth1.getDepth2().size(), 1);
        assertEquals(addressDepth1.getDepth2().get(0).getName(), gu);
    }
}
