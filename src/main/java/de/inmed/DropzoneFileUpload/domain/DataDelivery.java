package de.inmed.DropzoneFileUpload.domain;

import java.util.HashSet;
import java.util.Set;

public class DataDelivery {
    private Set<FileUpload> fileUploadSet = new HashSet<>();
    private String id = "";

    public boolean isInEditMode() {
        return true;
    }

    public Set<FileUpload> getFileUploadSet() {
        return fileUploadSet;
    }

    public String getId() {
        return id;
    }
}
