package com.home.app.service.kernel.util;

import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

	public static void deleteIfExist(String pathFile) throws Exception{
		getFile().deleteIfExist(pathFile);
	}

	public static void delete(String pathFile) throws Exception{
		getFile().delete(pathFile);
	}

	public static void mkdirs(String pathName) {
		getFile().mkdirs(pathName);
	}

	public static void write(String fileName, byte[] bytes) throws IOException {
		getFile().write(fileName, bytes);
	}

	public static void write(java.io.File file, byte[] bytes)
			throws IOException {
		getFile().write(file, bytes);
	}

	public static void write(java.io.File file, byte[] bytes, int offset,
			int length) throws IOException {
		getFile().write(file, bytes, offset, length);
	}

	public static void write(String fileName, InputStream is)
			throws IOException {

		getFile().write(fileName, is);
	}

	public static void write(java.io.File file, InputStream is) throws IOException {
		getFile().write(file, is);
	}

	public static String read(String filePath) throws Exception{
		return getFile().read(filePath);
	}

	public static File getFile() {
		return _file;
	}

	public void setFile(File file) {
		_file = file;
	}

	private static File _file;

}
