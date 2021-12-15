package de.inmed.DropzoneFileUpload.adapter.fileSystem;

import de.inmed.DropzoneFileUpload.application.in.CreateOrLoadDataDeliveryUseCase;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDelivery;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDeliveryId;
import de.inmed.DropzoneFileUpload.domain.fileUpload.FileUpload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FileSystemServiceTest {

    @Mock
    CreateOrLoadDataDeliveryUseCase createOrLoadDataDeliveryUseCase;
    @InjectMocks
    FileSystemService fileSystemService;

    DataDelivery dataDelivery;

    @BeforeEach
    void setUp() throws Exception {
        dataDelivery = new DataDelivery(DataDeliveryId.from("123"));
    }

    @Test
    void whenAddingAFile_ExpectItExistsOnFileSystem() throws Exception {
        MultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "test 123".getBytes(StandardCharsets.UTF_8));
        FileSystemService.AddFileCommand command = new FileSystemService.AddFileCommand(multipartFile, dataDelivery.getId());

        FileUpload fileUpload = fileSystemService.addToDataDeliveryAndWriteToFileSystem(command);

        File fsFile = fileUpload.getPathToFile().toFile();

        assertThat(fsFile.exists()).isTrue();
    }
}