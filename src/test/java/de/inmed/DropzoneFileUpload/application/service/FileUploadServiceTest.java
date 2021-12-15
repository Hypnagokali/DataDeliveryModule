package de.inmed.DropzoneFileUpload.application.service;

import de.inmed.DropzoneFileUpload.application.in.FileUploadUseCase;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDelivery;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDeliveryId;
import de.inmed.DropzoneFileUpload.domain.fileUpload.FileId;
import de.inmed.DropzoneFileUpload.domain.fileUpload.FileUpload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FileUploadServiceTest {

    @Mock
    CreateOrLoadDataDeliveryService createAndLoadDataDeliveryService;

    @InjectMocks
    FileUploadService fileUploadService;

    @Test
    void whenAddingAFileAndRemovingAFileFromDataDelivery_ExpectSizeOfUploadsAre0() throws Exception {
        givenADataDeliveryThatCanBeCreatedAndFound();

        DataDelivery dataDelivery = fileUploadService.createDataDelivery();

        FileUpload upload = new FileUpload(new FileId(1, FileId.FileType.TRANSIENT), 1000, "testdatei.txt");
        andUploadingAFile(dataDelivery, upload);

        andDeletingAFile(dataDelivery, upload);

        assertThat(dataDelivery.getFileUploadSet()).hasSize(0);

    }

    private void andDeletingAFile(DataDelivery dataDelivery, FileUpload upload) {
        FileUploadUseCase.FileUploadCommand command = new FileUploadUseCase.FileUploadCommand(upload, dataDelivery.getId());
        fileUploadService.removeFileFromDataDelivery(command);
    }

    @Test
    void whenAddingAFileToANewDataDelivery_ExpectSizeOfUploadsIs1() throws Exception {
        // Given:
        // Eine Datenlieferung kann erstellt und gefunden werden
        givenADataDeliveryThatCanBeCreatedAndFound();

        // When:
        // Eine Datenlieferung wird vom Benutzer erstellt
        DataDelivery dataDelivery = fileUploadService.createDataDelivery();
        // And:
        // Eine Datei testdatei.txt wird hochgeladen
        andUploadingAFile(dataDelivery, new FileUpload(new FileId(1, FileId.FileType.TRANSIENT), 1000, "testdatei.txt"));
        // Then:
        // die gefundene Datenlieferung hat einen FileUpload
        assertThat(dataDelivery.getFileUploadSet()).hasSize(1);
    }

    private void andUploadingAFile(DataDelivery dataDelivery, FileUpload upload) {
        FileUploadUseCase.FileUploadCommand fileUploadCommand = new FileUploadUseCase.FileUploadCommand(upload, dataDelivery.getId());
        fileUploadService.uploadFile(fileUploadCommand);
    }

    private void givenADataDeliveryThatCanBeCreatedAndFound() {
        DataDelivery dataDelivery = new DataDelivery(DataDeliveryId.from("123"));
        BDDMockito.given(createAndLoadDataDeliveryService.createDataDelivery()).willReturn(dataDelivery);
        // And:
        // und gefunden werden.
        BDDMockito.given(createAndLoadDataDeliveryService.findDataDelivery(DataDeliveryId.from("123"))).willReturn(dataDelivery);
    }

    @Test
    void whenCreatingADataDelivery_ExpectEditModeIsTrue_AndFileUploadSetIsEmpty() throws Exception {
        BDDMockito.given(createAndLoadDataDeliveryService.createDataDelivery()).willReturn(new DataDelivery(new DataDeliveryId("123")));


        DataDelivery dataDelivery = fileUploadService.createDataDelivery();

        assertThat(dataDelivery).isNotNull();
        assertThat(dataDelivery.isInEditMode()).isTrue();
        assertThat(dataDelivery.getFileUploadSet()).isEmpty();
        assertThat(dataDelivery.getId().value()).isNotEmpty();
    }
}
