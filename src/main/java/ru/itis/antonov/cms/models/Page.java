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
    private Long id;
    @Column(unique = true)
    private String path;
    private String content;
    @ManyToOne
    private Page parent;

    @OneToMany(mappedBy = "parent")
    private List<Page> children;
}
