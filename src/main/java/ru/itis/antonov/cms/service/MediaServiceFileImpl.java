package ru.itis.antonov.cms.service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MediaServiceFileImpl implements MediaService{

    private Path repositoryPath;

    @Autowired
    public MediaServiceFileImpl(Path path){
        repositoryPath = path;
    }

    public String saveFileItem(MultipartFile file){
        String fileName = RandomStringUtils.random(10, true, true);
        String ext;
        try {
            ext = MimeTypes.getDefaultMimeTypes().forName(file.getContentType()).getExtension();
            fileName += ext;
            file.transferTo(repositoryPath.resolve(fileName).toFile());
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return fileName;
    }

    @Override
    public boolean fileExist(String path) {
        System.out.println(repositoryPath.resolve(path));
        return repositoryPath.resolve(path).toFile().exists();
    }

    @Override
    public InputStream getFile(String path) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(repositoryPath.resolve(path).toFile());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return inputStream;
    }

    @Override
    public String getMimeType(String path) {
        try {
            return Files.probeContentType(repositoryPath.resolve(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
