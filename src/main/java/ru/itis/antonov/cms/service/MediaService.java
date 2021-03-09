package ru.itis.antonov.cms.service;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface MediaService {
    InputStream getFile(String path);

    String getMimeType(String path);

    String saveFileItem(MultipartFile file);

    boolean fileExist(String path);
}
