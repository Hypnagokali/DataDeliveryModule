package de.inmed.DropzoneFileUpload.domain.fileUpload;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class UniqueFilenameBuilder {

    private String filename;
    private String[] filesInDirectory;

    public UniqueFilenameBuilder(String filename, Path path) {
        this.filename = filename;

        File[] files = path.toFile().listFiles();

        if (files != null)
            filesInDirectory = Arrays.stream(files).map(File::getName).toArray(String[]::new);
        else
            filesInDirectory = new String[0];

    }

    public UniqueFilenameBuilder(String filename, String[] filesInDirectory) {
        this.filename = filename;
        this.filesInDirectory = filesInDirectory;
    }

    public IndexedFilename build() {
        IndexedFilename indexedFilename = new SimpleIndexedFilename(this.filename);

        int index = 0;
        while(Arrays.stream(filesInDirectory).anyMatch(name -> new SimpleIndexedFilename(name).equals(indexedFilename))) {
            index++;
            indexedFilename.setIndex(index);
        }

        return indexedFilename;
    }
}
