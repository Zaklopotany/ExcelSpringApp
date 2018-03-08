package xyz.zaklopotany.excelApp.service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	void saveFile(MultipartFile file) throws IOException;
	File getTempFile(MultipartFile file) throws IOException;
}
