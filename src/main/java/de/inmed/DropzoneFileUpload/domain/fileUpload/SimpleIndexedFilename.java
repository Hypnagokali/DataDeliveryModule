package de.inmed.DropzoneFileUpload.domain.fileUpload;

public class SimpleIndexedFilename implements IndexedFilename {

    private String extension = "";
    private String basename = "";
    private String index = "";

    public SimpleIndexedFilename(IndexedFilename filename) {
        this.extension = filename.extension();
        this.basename = filename.basename();
    }

    public SimpleIndexedFilename(String filename) {
        String[] filenameFields = filename.split("\\.");

        if (filenameFields.length > 1) {
            this.extension = filenameFields[filenameFields.length - 1];
            String basenameWithIndex = generateBasenameFromArray(filenameFields);
            this.index = parseIndexFromBasename(basenameWithIndex);
            this.basename = withoutIndex(basenameWithIndex, index);


        } else {
            this.basename = filename;
        }

    }

    private String withoutIndex(String basename, String index) {
        if (index.length() == 0) return basename;

        int i = basename.lastIndexOf(index);
        if (i > 0) {
            return basename.substring(0, i - 1);
        }

        return basename;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof SimpleIndexedFilename)) return false;
        IndexedFilename that = (SimpleIndexedFilename) o;

        return that.toString().equalsIgnoreCase(this.toString());
    }

    @Override
    public String extension() {
        return extension;
    }

    @Override
    public String basename() {
        return basename;
    }

    @Override
    public String index() {
        return index;
    }

    @Override
    public void setBasename(String basename) {
        this.basename = basename;
    }

    @Override
    public void setIndex(int index) {
        this.index = String.valueOf(index);
    }

    @Override
    public String toString() {
        String indexString = index.length() > 0 ? "_" + index : "";

        return basename + indexString + "." + extension;
    }

    private String parseIndexFromBasename(String basename) {
        int i = basename.lastIndexOf("_");
        if (i > -1 && basename.length() - 1 > i) {
            String indexString = basename.substring(i + 1);
            if (indexString.matches("\\d+")) {
                return indexString;
            }
        }

        return "";
    }

    private String generateBasenameFromArray(String[] filenameFields) {
        String[] basenameFields = new String[filenameFields.length - 1];
        System.arraycopy(filenameFields, 0, basenameFields, 0, basenameFields.length);

        return String.join(".", basenameFields);
    }
}
