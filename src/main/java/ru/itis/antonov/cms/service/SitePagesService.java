package ru.itis.antonov.cms.service;

import ru.itis.antonov.cms.dto.PageDto;
import ru.itis.antonov.cms.models.Page;

import java.util.List;

public interface SitePagesService {
    void savePage(Page page);

    PageDto getPage(String path);

    List<PageDto> pages();

    Page buildPage(String content, String title, String path, String locale, String parentId);
}
