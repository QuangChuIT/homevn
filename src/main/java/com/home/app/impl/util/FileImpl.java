package com.home.app.impl.util;

import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Repository;

import java.io.*;

public class FileImpl implements com.home.app.service.kernel.util.File {

    public void deleteIfExist(String pathFile) throws Exception {
        File file = new File(pathFile);

        file.deleteOnExit();
    }

    public void delete(String pathFile) throws Exception {
        File file = new File(pathFile);

        if (file.exists() && file.canWrite()) {
            file.delete();
        } else {
            throw new Exception(String.format("file with {path: %s} can not delete", pathFile));
        }
    }

    public void mkdirs(String pathName) {
        File file = new File(pathName);

        file.mkdirs();
    }

    @Override
    public void write(String fileName, byte[] bytes) throws IOException {
        write(new File(fileName), bytes);

    }

    @Override
    public void write(File file, byte[] bytes) throws IOException {
        write(file, bytes, 0, bytes.length);

    }

    @Override
    public void write(File file, byte[] bytes, int offset, int length) throws IOException {

        if (file.getParent() != null) {
            mkdirs(file.getParent());
        }

        FileOutputStream fos = new FileOutputStream(file);

        fos.write(bytes, offset, length);
        fos.close();
    }

    public void write(String fileName, InputStream is) throws IOException {
        write(new File(fileName), is);
    }

    public void write(File file, InputStream is) throws IOException {
        if (file.getParent() != null) {
            mkdirs(file.getParent());
        }

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);

            byte[] bytes = new byte[BUFFER_SIZE];
            int count = -1;

            while ((count = is.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, count);
            }

            fileOutputStream.flush();
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    @Override
    public String read(String filePath) throws IOException {
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                sb.append(currentLine);
            }

            return sb.toString();
        } catch (Exception e) {
            _log.error(e);
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }

            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        return null;
    }

    private final int BUFFER_SIZE = 1024;
    private static final Log _log = LogFactoryUtil.getLog(FileImpl.class);
}
