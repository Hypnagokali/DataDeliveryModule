package de.inmed.DropzoneFileUpload.domain.fileUpload;

public interface IndexedFilename {

    String extension();
    String basename();

    void setBasename(String basename);
    void setIndex(int index);

    String index();

}
