package de.inmed.DropzoneFileUpload.adapter.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.inmed.DropzoneFileUpload.adapter.fileSystem.FileSystemService;
import de.inmed.DropzoneFileUpload.adapter.web.dto.UploadDto;
import de.inmed.DropzoneFileUpload.adapter.web.service.FileUploadToUploadDtoConverter;
import de.inmed.DropzoneFileUpload.application.in.FileUploadUseCase;
import de.inmed.DropzoneFileUpload.domain.DataDelivery.DataDeliveryId;
import de.inmed.DropzoneFileUpload.domain.FileUpload.FileId;
import de.inmed.DropzoneFileUpload.domain.FileUpload.FileUpload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UploadControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FileUploadToUploadDtoConverter converter;
    @Mock
    private FileSystemService fileSystemService;

    @InjectMocks
    private UploadController uploadController;


    private ObjectMapper om;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(uploadController).build();
        om = new ObjectMapper();
    }

    @Test
    void whenUploadingAMultipartFileToExistingDataDelivery_ExpectAFileUploadAsResponse() throws Exception {
        FileUpload fileUpload = givenAnGeneratedFileUpload();
        givenAnDtoAfterConverting(fileUpload);

        MockMultipartFile testFile = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "test text".getBytes(StandardCharsets.UTF_8)
        );

        MvcResult result = mockMvc.perform(multipart("/upload")
                .file(testFile)
                .param("data-delivery-id", "abc"))
                .andExpect(status().isOk())
                .andReturn();

        String uploadedFile = result.getResponse().getContentAsString();

        assertThat(uploadedFile).isNotNull();
        assertThat(uploadedFile).isNotEmpty();

        UploadDto upload = om.readValue(uploadedFile, UploadDto.class);

        assertThat(upload.getOrigFilename()).isEqualTo("test.txt");
        assertThat(upload.getSize()).isEqualTo("test text".getBytes(StandardCharsets.UTF_8).length);
    }

    private void givenAnDtoAfterConverting(FileUpload upload) {
        UploadDto dto = new UploadDto();
        dto.setId(upload.getId().toString());
        dto.setOrigFilename(upload.getOrigFilename());
        dto.setSize(upload.getSize());

        given(converter.fileUploadToDto(upload)).willReturn(dto);
    }

    private FileUpload givenAnGeneratedFileUpload() {
        FileUpload upload = createAnUpload();

        given(
                fileSystemService.addToDataDeliveryAndWriteToFileSystem(any())
        ).willReturn(upload);

        return upload;
    }

    private FileUpload createAnUpload() {
        return new FileUpload(
                FileId.fromTransient(1),
                "test text".getBytes(StandardCharsets.UTF_8).length,
                "test.txt"
        );
    }

}
