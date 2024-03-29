package com.eodya.api.place.repository;

import com.eodya.api.address.domain.AddressDepth2;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDepth2Repository extends JpaRepository<AddressDepth2, Long> {

    Optional<AddressDepth2> findByName(String name);
}
