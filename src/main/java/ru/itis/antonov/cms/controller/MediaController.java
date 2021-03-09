package ru.itis.antonov.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.antonov.cms.service.MediaService;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MediaController {

    private MediaService service;

    @Autowired
    public MediaController(MediaService service) {
        this.service = service;
    }

    @GetMapping("/media/{filename:.+}")
    public void getImage(@PathVariable String filename, HttpServletResponse response, OutputStream out){
        System.out.println(filename);
        if(!service.fileExist(filename)){
            response.setStatus(404);
        }
        else {
            InputStream in = service.getFile(filename);
            byte[] buffer = new byte[1024];
            try {
                while (in.read(buffer) != -1){
                    out.write(buffer);
                }
                out.close();
            }
            catch (IOException e){
                throw new IllegalArgumentException(e);
            }
        }
    }

    @PostMapping("/media")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("upload") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        String fileName = service.saveFileItem(file);
        map.put("fileName", fileName);
        map.put("uploaded", 1);
        map.put("url", request.getRequestURI() + "/" + fileName);
        return map;
    }
}
