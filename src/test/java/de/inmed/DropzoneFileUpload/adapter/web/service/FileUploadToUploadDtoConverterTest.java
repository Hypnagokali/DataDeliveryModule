package de.inmed.DropzoneFileUpload.adapter.web.service;

import de.inmed.DropzoneFileUpload.adapter.web.dto.UploadDto;
import de.inmed.DropzoneFileUpload.domain.fileUpload.FileId;
import de.inmed.DropzoneFileUpload.domain.fileUpload.FileUpload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUploadToUploadDtoConverterTest {

    ModelMapper modelMapper;
    FileUploadToUploadDtoConverter fileUploadToUploadDtoConverter;

    @BeforeEach
    void setUp() throws Exception {
        modelMapper = new ModelMapper();

        fileUploadToUploadDtoConverter = new FileUploadToUploadDtoConverter(modelMapper);
    }

    @Test
    void afterMappingToDto_ExpectAllFieldsAreMapped() throws Exception {
        FileUpload fileUpload = new FileUpload(FileId.fromTransient(1), 1000, "test.txt");

        UploadDto uploadDto = fileUploadToUploadDtoConverter.fileUploadToDto(fileUpload);

        assertThat(uploadDto.getId()).isEqualTo(fileUpload.getId().value());
        assertThat(uploadDto.getSize()).isEqualTo(1000L);
        assertThat(uploadDto.getOrigFilename()).isEqualTo("test.txt");

    }
}
