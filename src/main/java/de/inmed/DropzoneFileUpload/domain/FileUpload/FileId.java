package de.inmed.DropzoneFileUpload.domain.FileUpload;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FileId {

    @JsonIgnore
    public static FileId fromTransient(long id) {
        return new FileId(id, FileType.TRANSIENT);
    }

    public enum FileType {
        TRANSIENT,
        PERSISTENT,
    }

    private long id = 0;
    private FileType type;

    public FileId(long id, FileType fileType) {
        this.id = id;
        this.type = fileType;
    }

    public String value() {
        return id + "_" + type.name().toLowerCase();
    }

    @Override
    public String toString() {
        return value();
    }
}
