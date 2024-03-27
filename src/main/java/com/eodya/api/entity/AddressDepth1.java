package com.eodya.api.entity;

import com.eodya.api.common.entity.BaseEntity;
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
@Table(name = "address_depth1")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressDepth1 extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_depth1_id")
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "depth1")
    private List<AddressDepth2> depth2 = new ArrayList<>();

    @Builder
    private AddressDepth1(String name) {
        this.name = name;
    }
}
