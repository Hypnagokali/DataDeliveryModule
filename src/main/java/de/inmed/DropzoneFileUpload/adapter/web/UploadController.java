package de.inmed.DropzoneFileUpload.adapter.web;

import de.inmed.DropzoneFileUpload.domain.FileUpload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @PostMapping("/upload")
    public FileUpload uploadFile(@RequestParam MultipartFile file) {
        FileUpload upload = new FileUpload(file.getSize(), file.getOriginalFilename());

        return upload;
    }

}
