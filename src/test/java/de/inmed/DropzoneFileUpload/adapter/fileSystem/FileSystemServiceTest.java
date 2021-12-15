package de.inmed.DropzoneFileUpload.adapter.fileSystem;

import de.inmed.DropzoneFileUpload.application.in.CreateOrLoadDataDeliveryUseCase;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDelivery;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDeliveryId;
import de.inmed.DropzoneFileUpload.domain.fileUpload.FileUpload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FileSystemServiceTest {

    @Mock
    Environment environment;
    @Mock
    CreateOrLoadDataDeliveryUseCase createOrLoadDataDeliveryUseCase;
    @InjectMocks
    FileSystemService fileSystemService;

    DataDelivery dataDelivery;
    String tempPath = "";


    @BeforeEach
    void setUp() throws Exception {
        dataDelivery = new DataDelivery(DataDeliveryId.from("123"));
        URL resource = this.getClass().getResource("/data-temp");
        if (resource != null) {
            tempPath = Path.of(resource.toURI()).toString();
        }
    }

    @Test
    void whenAddingAFile_ExpectItExistsOnFileSystem() throws Exception {
        // Given
        // Eine Datei und eine dataDelivery ID werden hochgeladen
        MultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "test 123".getBytes(StandardCharsets.UTF_8));
        FileSystemService.AddFileCommand command = new FileSystemService.AddFileCommand(multipartFile, dataDelivery.getId());

        // And
        // Es existiert eine DataDelivery mit dieser ID
        BDDMockito.given(createOrLoadDataDeliveryUseCase.findDataDelivery(command.getDataDeliveryId())).willReturn(dataDelivery);
        BDDMockito.given(environment.getProperty("inmed.temppath")).willReturn(tempPath);
        // init Service
        fileSystemService.init();

        // When
        FileUpload fileUpload = fileSystemService.addToDataDeliveryAndWriteToFileSystem(command);

        // Then
        // Eine Datei wurde auf dem Filesystem mit dem Inhalt der hochgeladenen Datei angelegt
        File fsFile = fileUpload.getPathToFile().toFile();
        assertThat(fsFile.exists()).isTrue();
    }
}