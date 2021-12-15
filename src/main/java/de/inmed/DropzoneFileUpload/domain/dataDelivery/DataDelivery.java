package de.inmed.DropzoneFileUpload.domain.dataDelivery;

import de.inmed.DropzoneFileUpload.domain.fileUpload.FileId;
import de.inmed.DropzoneFileUpload.domain.fileUpload.FileUpload;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DataDelivery {

    private static long transientFileCounter = 0;
    private DataDeliveryId dataDeliveryId;
    private Set<FileUpload> fileUploadSet = new HashSet<>();

    public DataDelivery(DataDeliveryId dataDeliveryId) {
        this.dataDeliveryId = dataDeliveryId;
    }

    public boolean isInEditMode() {
        return true;
    }

    public Set<FileUpload> getFileUploadSet() {
        return fileUploadSet;
    }

    public DataDeliveryId getId() {
        return dataDeliveryId;
    }

    public void addFile(FileUpload file) {
        fileUploadSet.add(file);
    }

    public void removeFileById(FileId id) {
        fileUploadSet = fileUploadSet.stream().filter(file -> !file.getId().equals(id)).collect(Collectors.toSet());
    }

    public FileUpload addTransientFile(MultipartFile file, String path) {
        transientFileCounter++;
        FileId fileId = new FileId(transientFileCounter, FileId.FileType.TRANSIENT);
        FileUpload fileUpload = new FileUpload(fileId, file.getSize(), file.getOriginalFilename());
        fileUpload.setPath(path);
        fileUpload.setFileName(this.getId().value() + "_file_" + fileUpload.getId().value(), file.getOriginalFilename());
        // ToDo: User der die Datei angelegt hat und weiteres
        fileUploadSet.add(fileUpload);

        return fileUpload;
    }
}
