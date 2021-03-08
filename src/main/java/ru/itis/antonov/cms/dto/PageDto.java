package ru.itis.antonov.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.antonov.cms.models.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDto {
    private Long id;
    private String path;
    private String content;

    public static PageDto from(Page page){
        return PageDto.builder().content(page.getContent()).path(page.getPath()).id(page.getId()).build();
    }

    public static Page toModel(PageDto pageDto){
        return Page.builder().content(pageDto.getContent()).path(pageDto.getPath()).id(pageDto.getId()).build();
    }
}