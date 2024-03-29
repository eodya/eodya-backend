package com.eodya.api.repository;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;
import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.bookmark.domain.BookmarkStatus;
import com.eodya.api.bookmark.repository.BookmarkRepository;
import com.eodya.api.fixture.domain.AddressDepthFixture;
import com.eodya.api.fixture.domain.PlaceFixture;
import com.eodya.api.fixture.domain.UserFixture;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.repository.PlaceRepository;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class PlaceRepositoryTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private EntityManager em;

    private AddressDepth1 addressDepth1;
    private AddressDepth2 addressDepth2;

    @BeforeEach
    void setUp() {
        addressDepth1 = AddressDepthFixture.addressDepth1Build();
        em.persist(addressDepth1);

        addressDepth2 = AddressDepthFixture.addressDepth2Build(em, addressDepth1);
        em.persist(addressDepth2);

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("정상적으로 북마크를 기준 오름차순으로 장소를 찾을 수 있다.")
    void findPlace_ByBookmark_Success() {
        //given
        User testUser = UserFixture.userBuild();
        Place testPlace1 = PlaceFixture.placeBuild(testUser);
        Place testPlace2 = PlaceFixture.placeBuild(testUser);
        Place testPlace3 = PlaceFixture.placeBuild(testUser);

        userRepository.save(testUser);
        placeRepository.save(testPlace1);
        placeRepository.save(testPlace2);
        placeRepository.save(testPlace3);

        createBookmarksForPlace(testPlace1, 5,testUser);
        createBookmarksForPlace(testPlace2, 3,testUser);
        createBookmarksForPlace(testPlace3, 10,testUser);

        //when
        List<Place> places = placeRepository.findAllByOrderByBookmarkCountDesc();

        //then
        assertNotNull(places);
        assertEquals(3, places.size());
        assertEquals(testPlace3.getId(), places.get(0).getId());
        assertEquals(testPlace1.getId(), places.get(1).getId());
        assertEquals(testPlace2.getId(), places.get(2).getId());
    }

    private void createBookmarksForPlace(Place place, int count,User testUser){

        IntStream.range(0, count).forEach(i -> {
            Bookmark bookmark = Bookmark.builder()
                    .user(testUser)
                    .place(place)
                    .status(BookmarkStatus.TRUE)
                    .build();
            bookmarkRepository.save(bookmark);
        });
    }
}
