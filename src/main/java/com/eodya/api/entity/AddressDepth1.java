package com.eodya.api.entity;

import com.eodya.api.entity.superclass.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AddressDepth1 extends TimeStamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_depth1_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "depth1")
    private List<AddressDepth2> depth2 = new ArrayList<>();

    @Builder
    private AddressDepth1(String name) {
        this.name = name;
    }
}
