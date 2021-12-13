package de.inmed.DropzoneFileUpload.adapter.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.inmed.DropzoneFileUpload.domain.FileUpload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UploadControllerTest {

    private MockMvc mockMvc;
    private UploadController uploadController;
    private ObjectMapper om;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new UploadController()).build();
        om = new ObjectMapper();
    }

    @Test
    void uploadingAFileTest() throws Exception {
        MockMultipartFile testFile = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "test text".getBytes(StandardCharsets.UTF_8)
        );

        MvcResult some = mockMvc.perform(multipart("/upload")
                .file(testFile)
                .param("some", "100"))
                .andExpect(status().isOk())
                .andReturn();

        String uploadedFile = some.getResponse().getContentAsString();

        assertThat(uploadedFile).isNotNull();
        assertThat(uploadedFile).isNotEmpty();

        FileUpload upload = om.readValue(uploadedFile, FileUpload.class);

        assertThat(upload.getOrigFilename()).isEqualTo("test.txt");
        assertThat(upload.getSize()).isEqualTo("test text".getBytes(StandardCharsets.UTF_8).length);
    }
}
