package de.inmed.DropzoneFileUpload.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUploadTest {

    @Test
    void setterTest() throws Exception {
        FileUpload upload = new FileUpload();

        upload.setSize(1500);
        upload.setOrigFilename("testdatei.txt");

        assertions(upload);
    }

    @Test
    void createAndSerialize() throws Exception {
        FileUpload upload = new FileUpload(1500, "testdatei.txt");

        assertions(upload);
    }

    private void assertions(FileUpload upload) {
        assertThat(upload.getOrigFilename()).isEqualTo("testdatei.txt");
        assertThat(upload.getSize()).isEqualTo(1500);
    }
}
