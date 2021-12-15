package de.inmed.DropzoneFileUpload.adapter.web.service;

import de.inmed.DropzoneFileUpload.domain.FileUpload.FileUpload;

import java.util.Collection;

public interface FileUploadDtoConverter<DTO, DtoCollection extends Collection<DTO>> {

    DTO fileUploadToDto(FileUpload fileUpload);
    FileUpload dtoToFileUpload(DTO dto);
    DtoCollection fileUploadsToDtos(Collection<FileUpload> fileUploads);
    Collection<FileUpload> dtosToFileUploads(DtoCollection dtos);


}
