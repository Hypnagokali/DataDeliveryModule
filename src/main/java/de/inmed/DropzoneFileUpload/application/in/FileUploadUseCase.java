package de.inmed.DropzoneFileUpload.application.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.inmed.DropzoneFileUpload.common.SelfValidating;
import de.inmed.DropzoneFileUpload.domain.DataDelivery;
import de.inmed.DropzoneFileUpload.domain.FileUpload;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public interface FileUploadUseCase {

    void uploadFile(UploadFileCommand command);
    DataDelivery createDataDelivery();

    @Getter
    final class UploadFileCommand extends SelfValidating<UploadFileCommand> {

        @NotNull
        private final FileUpload file;

        @JsonCreator
        public UploadFileCommand(FileUpload file) {
            this.file = file;
            validateSelf();
        }
    }


}
