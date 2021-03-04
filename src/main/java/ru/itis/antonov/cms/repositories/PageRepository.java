package ru.itis.antonov.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.antonov.cms.models.Page;

import java.util.Optional;


@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    Optional<Page> findPageByPath(String path);
}
