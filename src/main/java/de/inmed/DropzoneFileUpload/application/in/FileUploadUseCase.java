package de.inmed.DropzoneFileUpload.application.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.inmed.DropzoneFileUpload.common.SelfValidating;
import de.inmed.DropzoneFileUpload.domain.DataDelivery.DataDelivery;
import de.inmed.DropzoneFileUpload.domain.DataDelivery.DataDeliveryId;
import de.inmed.DropzoneFileUpload.domain.FileUpload.FileUpload;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public interface FileUploadUseCase {

    void uploadFile(FileUploadCommand command);
    DataDelivery createDataDelivery();

    void removeFileFromDataDelivery(FileUploadCommand command);

    @Getter
    final class FileUploadCommand extends SelfValidating<FileUploadCommand> {

        @NotNull
        private FileUpload file;

        @NotNull
        private DataDeliveryId dataDeliveryId;

        @JsonCreator
        public FileUploadCommand(FileUpload file, DataDeliveryId dataDeliveryId) {
            this.file = file;
            this.dataDeliveryId = dataDeliveryId;
            validateSelf();
        }

    }


}
