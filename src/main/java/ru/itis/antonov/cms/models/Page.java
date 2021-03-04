package ru.itis.antonov.cms.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Page {
    @Id
    private Long id;
    @Column(unique = true)
    private String path;
    private String content;
}
