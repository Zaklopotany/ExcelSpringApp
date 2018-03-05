package xyz.zaklopotany.excelApp.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class FileServiceImpl implements FileService {

	final static String UploadFolder = "/home/zaklopotany/Pulpit/nowykatalog/";
	
	@Override
	public void saveFile(MultipartFile file) throws IOException {
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UploadFolder+file.getOriginalFilename());
		Files.write(path, bytes);
		
	}

}
