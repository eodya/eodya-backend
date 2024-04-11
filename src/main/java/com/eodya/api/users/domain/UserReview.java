package com.eodya.api.users.domain;

import com.eodya.api.review.domain.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class UserReview {

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Review> userReviews = new ArrayList<>();

    public void addUserReview(Review review) {
        userReviews.add(review);
    }
}
