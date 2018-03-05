package xyz.zaklopotany.excelApp.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.zaklopotany.excelApp.service.FileService;

@Controller
public class UploadFileController {

	private FileService fileService;

	public UploadFileController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", " please select file");
			return "redirect:/uploadStatus";
		}

		try {
			fileService.saveFile(file);
			redirectAttributes.addFlashAttribute("message","you successfully uploaded file: " + file.getOriginalFilename() + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/uploadStatus";
	}

	@GetMapping(path="/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
	
	@GetMapping("/")
    public String index() {
        return "upload";
    }
}
