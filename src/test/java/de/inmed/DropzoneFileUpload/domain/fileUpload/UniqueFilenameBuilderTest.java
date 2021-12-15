package de.inmed.DropzoneFileUpload.domain.fileUpload;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class UniqueFilenameBuilderTest {

    String[] dir = new String[] {
            "filename.txt",
            "filename_1.txt"
    };

    @Test
    void whenAPathIsGiven_ExpectFilenameBuilderWorks() throws Exception {
        URL test_dir = UniqueFilenameBuilderTest.class.getResource("/test_dir");

        assertThat(test_dir).isNotNull();
        Path testDirPath = Path.of(test_dir.toURI());

        IndexedFilename filename = new UniqueFilenameBuilder("filename.txt", testDirPath).build();

        assertThat(filename.toString()).isEqualTo("filename_2.txt");

    }

    @Test
    void filenameWithUnderscoreButNoIndex() throws Exception {
        IndexedFilename filename = new SimpleIndexedFilename("prepend.some_file_1-fg.txt");

        assertThat(filename.index()).isEqualTo("");
        assertThat(filename.basename()).isEqualTo("prepend.some_file_1-fg");
    }

    @Test
    void indexOfGivenFilenameTest() throws Exception {
        IndexedFilename filename = new SimpleIndexedFilename("prepend.some_file_1_name_3.txt");

        assertThat(filename.index()).isEqualTo("3");
    }


    @Test
    void parseTest() throws Exception {
        IndexedFilename filename = new SimpleIndexedFilename("filename_1.txt");

        assertThat(filename.basename()).isEqualTo("filename");
        assertThat(filename.index()).isEqualTo("1");
        assertThat(filename.extension()).isEqualTo("txt");
    }

    @Test
    void whenNextFilenameIsGenerated_ExpectIndexIs2() throws Exception {
        IndexedFilename filename = new UniqueFilenameBuilder("filename.txt", dir).build();

        assertThat(filename.toString()).isEqualTo("filename_2.txt");
        assertThat(filename.index()).isEqualTo("2");
        assertThat(filename.basename()).isEqualTo("filename");
        assertThat(filename.extension()).isEqualTo("txt");
    }

}
