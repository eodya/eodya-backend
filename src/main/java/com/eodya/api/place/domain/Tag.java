package com.eodya.api.place.domain;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "tag")
    private List<PlaceTag> placeTags = new ArrayList<>();

    @Builder
    private Tag(String name) {
        this.name = name;
    }
}