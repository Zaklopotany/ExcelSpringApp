package xyz.zaklopotany.excelApp.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import xyz.zaklopotany.excelApp.storage.StorageService;

@Controller
@RequestMapping(path = "/file")
public class UploadFileController {

	private final StorageService storageService;

	@Autowired
	public UploadFileController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping(path = "/")
	public String listFilesUploaded(Model model) {
		model.addAttribute("files",
				storageService.loadAll().map(path -> MvcUriComponentsBuilder.fromMethodName(UploadFileController.class,
						"serveFile", path.getFileName().toString())).collect(Collectors.toList()));
		return "uploadForm";
	}

	@GetMapping(path = "/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);

	}

	public void upladFile() {

	}

}
