package de.inmed.DropzoneFileUpload.application.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.inmed.DropzoneFileUpload.common.SelfValidating;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDelivery;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDeliveryId;
import de.inmed.DropzoneFileUpload.domain.fileUpload.FileUpload;
import lombok.Getter;

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
