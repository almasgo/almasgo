package com.luthfihariz.almasgocore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
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

    @JsonRawValue
    @Column(columnDefinition = "text")
    private String tags;

    @JsonRawValue
    @Column(columnDefinition = "text")
    private String attributes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Content(String externalUniqueId, String title, String description, Integer popularityInPercentage,
                   Integer visibility, String tags, String attributes) {
        this.externalUniqueId = externalUniqueId;
        this.title = title;
        this.description = description;
        this.popularityInPercentage = popularityInPercentage;
        this.visibility = visibility;
        this.tags = tags;
        this.attributes = attributes;
    }
}
