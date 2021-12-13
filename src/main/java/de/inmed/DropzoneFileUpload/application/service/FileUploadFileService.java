package de.inmed.DropzoneFileUpload.application.service;

import de.inmed.DropzoneFileUpload.application.in.FileUploadUseCase;
import de.inmed.DropzoneFileUpload.domain.DataDelivery;
import org.springframework.stereotype.Service;

@Service
public class FileUploadFileService implements FileUploadUseCase {

    @Override
    public void uploadFile(UploadFileCommand command) {

    }

    @Override
    public DataDelivery createDataDelivery() {
        return new DataDelivery();
    }
}
