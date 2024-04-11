package com.eodya.api.users.domain;

import com.eodya.api.place.domain.Place;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class UserPlace {

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Place> places = new ArrayList<>();

    public void addUserPlace(Place place) {
        places.add(place);
    }
}
