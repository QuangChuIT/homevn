package com.home.app.service.kernel.util;

import java.io.IOException;
import java.io.InputStream;

public interface File {

	void deleteIfExist(String pathFile) throws Exception;

	void delete(String pathFile) throws Exception;

	void mkdirs(String pathName);
	
	void write(String fileName, byte[] bytes) throws IOException;
	
	void write(java.io.File file, byte[] bytes) throws IOException;
	
	void write(java.io.File file, byte[] bytes, int offset, int length) throws IOException;

	void write(String fileName, InputStream is) throws IOException;
	
	void write(java.io.File file, InputStream is) throws IOException;

	String read(String filePaht) throws IOException;
}
