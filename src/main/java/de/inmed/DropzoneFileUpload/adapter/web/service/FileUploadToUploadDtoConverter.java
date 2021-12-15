package de.inmed.DropzoneFileUpload.adapter.web.service;

import de.inmed.DropzoneFileUpload.adapter.web.dto.UploadDto;
import de.inmed.DropzoneFileUpload.domain.FileUpload.FileUpload;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FileUploadToUploadDtoConverter implements FileUploadDtoConverter<UploadDto, Set<UploadDto>> {

    private final ModelMapper modelMapper;

    @Override
    public UploadDto fileUploadToDto(FileUpload fileUpload) {
        UploadDto dto = modelMapper.map(fileUpload, UploadDto.class);
        return dto;
    }

    @Override
    public FileUpload dtoToFileUpload(UploadDto dto) {
        return null;
    }

    @Override
    public Set<UploadDto> fileUploadsToDtos(Collection<FileUpload> fileUploads) {
        return null;
    }

    @Override
    public Collection<FileUpload> dtosToFileUploads(Set<UploadDto> dtos) {
        return null;
    }
}
