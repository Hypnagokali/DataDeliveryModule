package de.inmed.DropzoneFileUpload.application.service;

import de.inmed.DropzoneFileUpload.domain.DataDelivery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FileUploadServiceTest {

    @InjectMocks
    FileUploadFileService uploadFileService;

    @Test
    void whenAddingAFileToDataDelivery_ExpectSizeOfUploadsIs1() throws Exception {
        DataDelivery dataDelivery = uploadFileService.createDataDelivery();

    }

    @Test
    void whenCreatingADataDelivery_ExpectEditModeIsTrue_AndFileUploadSetIsEmpty() throws Exception {

        DataDelivery dataDelivery = uploadFileService.createDataDelivery();

        assertThat(dataDelivery).isNotNull();
        assertThat(dataDelivery.isInEditMode()).isTrue();
        assertThat(dataDelivery.getFileUploadSet()).isEmpty();
        assertThat(dataDelivery.getId()).isNotEmpty();
    }
}
