package ru.itis.antonov.cms.service;

import ru.itis.antonov.cms.dto.PageDto;
import ru.itis.antonov.cms.models.Page;

public interface SitePagesService {
    void savePage(PageDto page);

    PageDto getPage(String path);
}
