package de.inmed.DropzoneFileUpload.adapter.fileSystem;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.inmed.DropzoneFileUpload.application.in.CreateOrLoadDataDeliveryUseCase;
import de.inmed.DropzoneFileUpload.common.SelfValidating;
import de.inmed.DropzoneFileUpload.domain.DataDelivery.DataDelivery;
import de.inmed.DropzoneFileUpload.domain.DataDelivery.DataDeliveryId;
import de.inmed.DropzoneFileUpload.domain.FileUpload.FileUpload;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@PropertySource("")
@Service
@RequiredArgsConstructor
@Slf4j
public class FileSystemService {
    
    private final CreateOrLoadDataDeliveryUseCase createOrLoadDataDeliveryUseCase;

    @Value("${inmed.temppath}")
    private String tempPath = "";

    public FileUpload addToDataDeliveryAndWriteToFileSystem(AddFileCommand addFileCommand) {
        DataDelivery dataDelivery = createOrLoadDataDeliveryUseCase.findDataDelivery(addFileCommand.getDataDeliveryId());
        FileUpload fileUpload = dataDelivery.addTransientFile(addFileCommand.getFile(), tempPath);

        try {
            addFileCommand.getFile().transferTo(fileUpload.getPathToFile());
        } catch (IOException e) {
            log.error("Konnte Datei nicht ins Dateisystem schreiben");
            dataDelivery.removeFileById(fileUpload.getId());
        }

        return fileUpload;
    }

    public static class AddFileCommand extends SelfValidating<AddFileCommand> {

        @NotNull
        private MultipartFile file;
        @NotNull
        @NotEmpty
        //@IsDataDeliveryId
        private String dataDeliveryId;

        @JsonCreator
        public AddFileCommand(MultipartFile file, String dataDeliveryId) {
            this.file = file;
            this.dataDeliveryId = dataDeliveryId;
            validateSelf();
        }

        public DataDeliveryId getDataDeliveryId() {
            return DataDeliveryId.from(dataDeliveryId);
        }

        public MultipartFile getFile() {
            return file;
        }
    }

}