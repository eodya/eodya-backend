package com.eodya.api.place.service;


//@Service
//@RequiredArgsConstructor
//public class PlaceService {
//
//    private static final int MAX_PLACE_IMAGE_COUNT = 2;
//
//    private final S3Service s3Service;
//    private final UserRepository userRepository;
//    private final PlaceRepository placeRepository;
//    private final ReviewImageRepository reviewImageRepository;
//    private final AddressDepth1Repository addressDepth1Repository;
//    private final AddressDepth2Repository addressDepth2Repository;
//    private final ReviewRepository reviewRepository;
//    private final PlaceTagRepository placeTagRepository;
//    private final TagRepository tagRepository;
//    private final BookmarkRepository bookmarkRepository;
//    private static GeometryFactory geometryFactory = new GeometryFactory();
//
//    @Transactional
//    public void createPlaceAndReview(Long userId, PlaceCreateRequest request) {
//        validatePlaceImageCount((request.getImages()));
//        User user = userRepository.getUserById(userId);
//
//        Tag tag = tagRepository.findByName(request.getTag())
//                .orElseGet(() ->
//                {
//                    Tag newPlaceTag = request.toTagEntity(request.getTag());
//                    return tagRepository.save(newPlaceTag);
//                });
//        Place place;
//        Optional<Place> findPlace = placeRepository.findByAddressDetail(request.getAddressDetail());
//
//        if (findPlace.isPresent()) {
//            Optional<PlaceTag> placeTag = placeTagRepository.findByPlaceAndTag(findPlace.get(), tag);
//            if (placeTag.isPresent()) throw new PlaceException(ALREADY_EXIST_PLACE);
//            place = findPlace.get();
//        } else {
//            //이미 등록된 장소가 아니면 등록
//            Optional<AddressDepth1> depth1 = addressDepth1Repository.findByName(request.getAddressDepth1());
//
//            AddressDepth1 addressDepth1 = depth1.orElseGet(() -> {
//                AddressDepth1 newDepth1 = request.toAddressDepth1Entity(request.getAddressDepth1());
//                addressDepth1Repository.save(newDepth1);
//                return newDepth1;
//            });
//            Optional<AddressDepth2> findDepth2 = addressDepth1.getDepth2().stream() //성능저하
//                    .filter(depth2 -> depth2.getName().equals(request.getAddressDepth2()))
//                    .findFirst();
//
//            AddressDepth2 addressDepth2 = findDepth2.orElseGet(() -> {
//                AddressDepth2 newDepth2 = request.toAddressDepth2Entity(request.getAddressDepth2(), addressDepth1);
//                addressDepth2Repository.save(newDepth2);
//                return newDepth2;
//            });
//
//            Point point = pointBuild(request.getX(), request.getY());
//            place = request.toPlaceEntity(point, user, addressDepth1, addressDepth2);
//            placeRepository.save(place);
//        }
//
//        placeTagRepository.save(PlaceTag.builder()
//                .tag(tag)
//                .place(place)
//                .build());
//
//        List<String> images = s3Service.uploadFiles(request.getImages());
//        place.setImage(images.get(0));
//
//        Review review = request.toReviewEntity(user, place);
//        reviewRepository.save(review);
////        for (String imageUrl : images) {
////            ReviewImage reviewImage = ReviewImage.builder()
////                    .imageUrl(imageUrl)
////                    .review(review)
////                    .build();
////            reviewImageRepository.save(reviewImage);
//        }
//    }
//
//    public List<PlaceAllByTagResponse> findAllPlaceByTag(String tagName) {
//        Tag tag = tagRepository.findByName(tagName)
//                .orElseThrow(() -> new PlaceException(INVALID_PLACE_IMAGE_COUNT));
//
//        List<PlaceTag> placeTags = placeTagRepository.findByTag(tag);
//        List<Long> placeIds = placeTags.stream()
//                .map(placeTag -> placeTag.getPlace())
//                .map(place -> place.getId())
//                .collect(Collectors.toList());
//
//        return placeRepository.findByPlaceIds(placeIds)
//                .stream().map(place -> PlaceAllByTagResponse.builder()
//                        .placeId(place.getId())
//                        .x(place.getPoint().getX())
//                        .y(place.getPoint().getY())
//                        .build()).toList();
//    }
//
//
//    public Point pointBuild(double x, double y) {
//        Coordinate coord = new Coordinate(x, y);
//        return geometryFactory.createPoint(coord);
//    }
//
//    public List<PlaceRakingResponse> findPlaceRankingByBookmarks() {
//        List<Place> places = placeRepository.findAll(Sort.by("bookmarkCount").descending());
//        List<PlaceRakingResponse> responses = new ArrayList<>();
//        long rank = 1;
//        long lastBookmarkCount = -1;
//        long skippedRanks = 0;
//
//        for (Place place : places) {
//            long currentBookmarkCount = place.getBookmarkCount();
//
//            if (currentBookmarkCount != lastBookmarkCount) {
//                rank += skippedRanks;
//                skippedRanks = 1;
//            } else {
//                skippedRanks++;
//            }
//
//            responses.add(PlaceRakingResponse.builder()
//                    .id(place.getId())
//                    .name(place.getName())
//                    .addressDetail(place.getAddressDetail())
//                    .placeImage(place.getImage())
//                    .bookmarkCount(currentBookmarkCount)
//                    .rank(rank)
//                    .build());
//
//            lastBookmarkCount = currentBookmarkCount;
//        }
//
//        return responses;
//    }
//
//
//    public PlaceAllByAddressResponse findAllPlaceByAddress(Long userId, PlaceAllByAddressRequest request, Pageable pageable) {
//        User user = userRepository.getUserById(userId);
//
//        Map<Long, Integer> bookMarkPlaceId = new HashMap<>();
//        user.getBookmarks().stream()
//                .filter(bookmark -> bookmark.getStatus().equals(BookmarkStatus.TRUE))
//                .forEach(bookmark -> bookMarkPlaceId.put(bookmark.getPlace().getId(), 1));
//
//        Page<Place> places = placeRepository.findByAddressContaining(request.getAddress(), pageable);
//        boolean hasNext = places.hasNext();
//
//        List<PlaceDetail> details = places.getContent().stream()
//                .map((place) -> {
//
//                    boolean isBookmarked = false;
//                    if (bookMarkPlaceId.getOrDefault(place.getId(), 0) == 1) {
//                        System.out.println(place.getId());
//                        isBookmarked = true;
//                    }
//                    PlaceStatus placeStatus = getPlaceRecentReview(place);
//
//                    return PlaceDetail.from(place, isBookmarked, placeStatus);
//                }).toList();
//        return PlaceAllByAddressResponse.from(details, hasNext);
//    }
//
//    public void validatePlaceImageCount(List<MultipartFile> files) {
//        if (files.size() > MAX_PLACE_IMAGE_COUNT || files.size() == 0) {
//            throw new PlaceException(INVALID_PLACE_IMAGE_COUNT);
//        }
//    }
//
//    public PlaceDetailResponse getPlaceDetail(Long userId, Long placeId) {
//        Place place = placeRepository.getPlaceById(placeId);
//        PlaceStatus placeStatus = getPlaceRecentReview(place);
//        Long bookmarkCount = placeRepository.countBookmarksByPlace(place);
//
//        boolean bookmarkStatus = false;
//        Optional<Bookmark> findBookmark = bookmarkRepository.findByUserIdAndPlaceId(userId, place.getId());
//
//        if (findBookmark.isPresent() && findBookmark.get().getStatus().equals(BookmarkStatus.TRUE)) {
//            bookmarkStatus = true;
//        }
//        return PlaceDetailResponse.from(place, placeStatus, bookmarkStatus, bookmarkCount);
//    }
//
//    private PlaceStatus getPlaceRecentReview(Place place) {
//        return place.getReviews().stream() //가장 최신의 리뷰를 가져옴
//                .sorted(Comparator.comparing(Review::getReviewDate).reversed())
//                .findFirst()
//                .get()
//                .getPlaceStatus();
//    }
//}
