package de.inmed.DropzoneFileUpload.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FileUpload {

    private String origFilename = "";
    private long size;

    public FileUpload(long size, String origFilename) {
        this.origFilename = origFilename;
        this.size = size;
    }


}
