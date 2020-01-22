package cn.kuaipan.android.http.multipart;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FilePartSource implements PartSource {
    private File file = null;
    private String fileName = null;

    public FilePartSource(File file2) throws FileNotFoundException {
        this.file = file2;
        if (file2 == null) {
            return;
        }
        if (!file2.isFile()) {
            throw new FileNotFoundException("File is not a normal file.");
        } else if (file2.canRead()) {
            this.fileName = file2.getName();
        } else {
            throw new FileNotFoundException("File is not readable.");
        }
    }

    public InputStream createInputStream() throws IOException {
        File file2 = this.file;
        return file2 != null ? new FileInputStream(file2) : new ByteArrayInputStream(new byte[0]);
    }

    public String getFileName() {
        String str = this.fileName;
        return str == null ? "noname" : str;
    }

    public long getLength() {
        File file2 = this.file;
        if (file2 != null) {
            return file2.length();
        }
        return 0;
    }
}
