package de.inmed.DropzoneFileUpload.domain.FileUpload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Path;

@NoArgsConstructor
@Setter
@Getter
public class FileUpload {

    private String origFilename = "";
    private long size;
    private FileId id;
    private String path;
    private String fileName;

    public FileUpload(FileId id, long size, String origFilename) {
        this.id = id;
        this.origFilename = origFilename;
        this.size = size;
    }

    public Path getPathToFile() {
        return Path.of(path + "/" + fileName);
    }

    public FileId getId() {
        return id;
    }
}
