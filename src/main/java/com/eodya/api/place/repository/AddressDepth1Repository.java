package com.eodya.api.place.repository;

import com.eodya.api.address.domain.AddressDepth1;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressDepth1Repository extends JpaRepository<AddressDepth1, Long> {

    //@Query("select address1 from AddressDepth1 address1 join fetch address1.addressDepth2")
    Optional<AddressDepth1> findByName(String name);
}
