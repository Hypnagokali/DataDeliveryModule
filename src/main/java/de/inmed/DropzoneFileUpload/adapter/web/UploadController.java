package de.inmed.DropzoneFileUpload.adapter.web;

import de.inmed.DropzoneFileUpload.adapter.fileSystem.FileSystemService;
import de.inmed.DropzoneFileUpload.adapter.web.dto.UploadDto;
import de.inmed.DropzoneFileUpload.adapter.web.service.FileUploadToUploadDtoConverter;
import de.inmed.DropzoneFileUpload.domain.fileUpload.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UploadController {

    private final FileSystemService fileSystemService;
    private final FileUploadToUploadDtoConverter fileUploadConverter;

    @PostMapping("/upload")
    public UploadDto uploadFile(@RequestParam MultipartFile file, @RequestParam(name = "data-delivery-id") String dataDeliveryId) {
        FileSystemService.AddFileCommand command = new FileSystemService.AddFileCommand(file, dataDeliveryId);

        FileUpload fileUpload = fileSystemService.addToDataDeliveryAndWriteToFileSystem(command);

        return fileUploadConverter.fileUploadToDto(fileUpload);
    }

}
