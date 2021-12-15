package de.inmed.DropzoneFileUpload.domain.fileUpload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public void setFileName(String prefix, String origFilename) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime dateTime = LocalDateTime.now();
        String timestamp = formatter.format(dateTime);
        this.fileName = timestamp + "_" + prefix + "_" + origFilename;

        // ToDo

    }

    public FileId getId() {
        return id;
    }
}
