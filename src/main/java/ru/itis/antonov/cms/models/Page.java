package ru.itis.antonov.cms.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Page {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String path;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    private Page parent;
    private String title;

    @OneToMany(mappedBy = "parent")
    private List<Page> children;
}
