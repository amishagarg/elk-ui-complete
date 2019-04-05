package io.elk.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.elk.entities.ModifiedBizdoc;
import io.elk.service.ModifiedBizdocService;

@Controller
@RequestMapping
public class ModifiedBizdocController {

	private static final Logger logger = LoggerFactory.getLogger(ModifiedBizdocController.class);

	ModifiedBizdocService modifiedBizdocService;

	@Autowired
	public ModifiedBizdocController(ModifiedBizdocService modifiedBizdocService) {
		this.modifiedBizdocService = modifiedBizdocService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<ModifiedBizdoc> getAll() {
		try {
			/*
			 * List<ModifiedBizdoc> list= new ArrayList<ModifiedBizdoc>();
			 * list=modifiedBizdocService.findAll(); System.out.println(list+"  JJJJJJ   ");
			 * 
			 * List<ModifiedBizdoc> list2= new ArrayList<ModifiedBizdoc>();
			 * list2=modifiedBizdocService.scanForLatest();
			 * System.out.println(list2+" KKKKKK ");
			 */

			// return list;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	// The Other app starts here

	private String upload_folder = "D:\\Software\\F\\logs\\";

	@GetMapping("/")
	public String index() {
		return "upload.html";
	}
	
	@GetMapping("/pathpage")
	public String pathpage() {
		return "pathpage.html";
	}
	
	
	
	@PostMapping("/browse")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:/uploadStatus";
		}
		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(upload_folder + file.getOriginalFilename());
			Files.write(path, bytes);
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/uploadStatus";
	}

	@PostMapping("/upload")
	public String addPaths(@RequestParam("DynamicTextBox") String arr_path,  RedirectAttributes redirectAttributes) throws IOException {

			File fileToBeModified = new File("D:\\Software\\F\\pathfilebeat.yml");
			BufferedReader br = new BufferedReader(new FileReader(fileToBeModified));

			FileWriter writer = null;
			String st = "";
			String stnew = "";
			System.out.println("======================");
			while ((st = br.readLine()) != null)
				{
					stnew = stnew + st + System.lineSeparator();
				}
			System.out.println(stnew);
			System.out.println("--------------");
			String[] values = arr_path.split(",");
	
			for (int i = 0; i < values.length; i++) {
	
				System.out.println(values[i]);
			}
			String str = "paths:" + System.lineSeparator();
	
			for (int i = 0; i < values.length; i++) {
				str = str + "  - " + values[i] + "//.*log" + System.lineSeparator();
				str = str.replace("\\", "/");
			}
			
			System.out.println(str);	
			// Replacing oldString with newString in the oldContent
			String newContent = stnew.replaceAll("paths:", str);

			// Rewriting the input text file with newContent

			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
			try {
				// Closing the resources

				br.close();

				writer.close();
				redirectAttributes.addFlashAttribute("message",
						"You successfully updated log path location.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		try {
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"D:\\cdbatchpath.bat\"");
		} catch (Exception e) {
			System.out.println("HEY Buddy ! U r Doing Something Wrong. You're a horrible person, buddy. ");
			e.printStackTrace();
		}

		return "redirect:/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
	
	//trigger buttons start here
	@GetMapping("/FilebeatTrigger")
	public String FilebeatTrigger() {
	
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"D:\\elk-demo\\batchfiles\\batchfileF.bat\"");
			} catch (Exception e) {
				System.out.println("HEY Buddy ! U r Doing Something Wrong. You're a horrible person, buddy. ");
				e.printStackTrace();
			}
			return "redirect:/uploadStatus";
	}
	@GetMapping("/FilebeatTriggerPath")
	public String FilebeatTriggerPath() {	
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"D:\\elk-demo\\batchfiles\\batchpathF.bat\"");
			} catch (Exception e) {
				System.out.println("HEY Buddy ! U r Doing Something Wrong. You're a horrible person, buddy. ");
				e.printStackTrace();
			}
			return "redirect:/uploadStatus";
	}
	
	@GetMapping("/ElkTrigger")
	public String ElkTrigger() {
		
		try {
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"D:\\elk-demo\\batchfiles\\elkstart.bat\"");
		} catch (Exception e) {
			System.out.println("HEY Buddy ! U r Doing Something Wrong. You're a horrible person, buddy. ");
			e.printStackTrace();
		}
		return "redirect:/uploadStatus";
	}
	
	@GetMapping("/FilebeatKiller")
	public String FilebeatKiller() {
		
		try {
	
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"D:\\elk-demo\\batchfiles\\filebeatkill.bat\"");
		} catch (Exception e) {
			System.out.println("HEY Buddy ! U r Doing Something Wrong. You're a horrible person, buddy. ");
			e.printStackTrace();
		}
		return "redirect:/uploadStatus";
	}

	@GetMapping("/ElasticKiller")
	public String ElasticKiller() {
		
		try {		
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"D:\\elk-demo\\batchfiles\\elkkill.bat\"");		
		} catch (Exception e) {
			System.out.println("HEY Buddy ! U r Doing Something Wrong. You're a horrible person, buddy. ");
			e.printStackTrace();
		}
		return "redirect:/uploadStatus";
	}
}
