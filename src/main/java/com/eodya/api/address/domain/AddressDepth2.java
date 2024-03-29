package com.eodya.api.address.domain;

import com.eodya.api.common.entity.BaseEntity;
import com.eodya.api.place.domain.Place;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "address_depth2")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressDepth2 extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_depth2_id")
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_depth1_id")
    private AddressDepth1 depth1;


    @OneToMany(mappedBy = "depth2")
    private List<Place> place = new ArrayList<>();

    public void setDepth1(AddressDepth1 depth1) {
        this.depth1 = depth1;
        this.depth1.getDepth2().add(this);
    }

    @Builder
    private AddressDepth2(String name, AddressDepth1 addressDepth1) {
        this.name = name;
        this.setDepth1(addressDepth1);
    }
}
