package com.eodya.api.entity;

import com.eodya.api.entity.superclass.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AddressDepth2 extends TimeStamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_depth2_id")
    private Long id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_depth1_id")
    private AddressDepth1 depth1;

    public void setDepth1(AddressDepth1 depth1) {
        this.depth1 = depth1;
        depth1.getDepth2().add(this);
    }

    @Builder
    private AddressDepth2(String name, AddressDepth1 addressDepth1) {
        this.name = name;
        setDepth1(addressDepth1);
    }
}
