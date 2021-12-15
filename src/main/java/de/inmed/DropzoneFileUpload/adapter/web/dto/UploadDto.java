package de.inmed.DropzoneFileUpload.adapter.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadDto {

    private String id;
    private long size;
    private String origFilename;


}
