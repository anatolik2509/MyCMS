package ru.itis.antonov.cms.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.antonov.cms.dto.PageDto;
import ru.itis.antonov.cms.models.Page;
import ru.itis.antonov.cms.repositories.PageRepository;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SitePagesServiceImpl implements SitePagesService {

    private FreeMarkerConfigurer configurer;

    private PageRepository pageRepository;

    @Autowired
    public SitePagesServiceImpl(FreeMarkerConfigurer configurer, PageRepository pageRepository) {
        this.configurer = configurer;
        this.pageRepository = pageRepository;
    }

    @Override
    public void savePage(Page page) {
        Optional<Page> samePage = pageRepository.findPageByPath(page.getPath());
        samePage.ifPresent(optionalPage -> {
            page.setId(optionalPage.getId());
        });
        pageRepository.save(page);
    }

    @Override
    public PageDto getPage(String path) {
        Optional<Page> page = pageRepository.findPageByPath(path);
        return page.map(PageDto::from).orElse(null);
    }

    @Override
    public List<PageDto> pages() {
        return pageRepository.findAll(Sort.by("path")).stream().map(PageDto::from).collect(Collectors.toList());
    }

    @Override
    public Page buildPage(String content, String title, String path, String locale, String parentId) {
        Long parent = Long.parseLong(parentId);
        if(parent == -1){
            return Page.builder()
                    .content(content)
                    .path("/site/" + locale + "/" + path)
                    .parent(null)
                    .title(title)
                    .build();
        }
        return Page.builder()
                .content(content)
                .path("/" + locale + "/" + path)
                .parent(Page.builder().id(parent).build())
                .title(title)
                .build();
    }
}
