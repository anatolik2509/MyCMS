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
    private String path;
    private String content;

    public static PageDto from(Page page){
        return PageDto.builder().content(page.getContent()).path(page.getPath()).build();
    }

    public static Page toModel(PageDto pageDto){
        return Page.builder().content(pageDto.getContent()).path(pageDto.getPath()).build();
    }
}