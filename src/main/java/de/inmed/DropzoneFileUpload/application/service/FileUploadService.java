package de.inmed.DropzoneFileUpload.application.service;

import de.inmed.DropzoneFileUpload.application.in.FileUploadUseCase;
import de.inmed.DropzoneFileUpload.domain.DataDelivery.DataDelivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileUploadService implements FileUploadUseCase {

    private final CreateOrLoadDataDeliveryService createAndLoadDataDeliveryService;

    @Override
    public void uploadFile(FileUploadCommand command) {
        DataDelivery dataDelivery = createAndLoadDataDeliveryService.findDataDelivery(command.getDataDeliveryId());
        dataDelivery.addFile(command.getFile());

    }

    @Override
    public DataDelivery createDataDelivery() {
        return createAndLoadDataDeliveryService.createDataDelivery();
    }

    @Override
    public void removeFileFromDataDelivery(FileUploadCommand command) {
        DataDelivery dataDelivery = createAndLoadDataDeliveryService.findDataDelivery(command.getDataDeliveryId());

        dataDelivery.removeFileById(command.getFile().getId());
    }


}
