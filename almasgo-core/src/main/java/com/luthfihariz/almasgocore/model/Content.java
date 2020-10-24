package com.luthfihariz.almasgocore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Content {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 48)
    private String externalUniqueId;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(length = 2)
    private Integer popularityInPercentage;

    @Column(length = 1)
    private Integer visibility;

    @Column(columnDefinition = "text")
    private String tags;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Content(Long id, String title, String description, Integer popularityInPercentage, Integer visibility, String tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.popularityInPercentage = popularityInPercentage;
        this.visibility = visibility;
        this.tags = tags;
    }

    public Content(String externalUniqueId, String title, String description, Integer popularityInPercentage,
                   Integer visibility, String tags) {
        this.externalUniqueId = externalUniqueId;
        this.title = title;
        this.description = description;
        this.popularityInPercentage = popularityInPercentage;
        this.visibility = visibility;
        this.tags = tags;
    }
}
