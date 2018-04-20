package com.sweetitech.tiger.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.service.ImageService;
import com.sweetitech.tiger.storage.StorageService;

@RestController
public class RestUploadController {
	
	
	@Value("${file.download.base}")
	private String DOWNLOAD_FOLDER;

	private final StorageService storageService;

    @Autowired
    public RestUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    
    

	@Value("${file.upload.url}")
	private String UPLOADED_FOLDER;

	@Autowired
	ImageService imageService;
	
	
	// Single file upload
	@PostMapping("/api/upload")
	// @ResponseBody
	public ResponseEntity<Image> uploadFile( @RequestParam("file") MultipartFile uploadfile) {
		
		
		
		if (uploadfile.isEmpty()) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {
			//saveUploadedFiles(Arrays.asList(uploadfile));
			Image i = saveUploadedFiles(Arrays.asList(uploadfile)).get(0);
			return new ResponseEntity(i, new HttpHeaders(), HttpStatus.OK);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}catch (org.springframework.web.multipart.MultipartException eo) {
			
			
			System.out.println("Maximum file size exceeds");
			return new ResponseEntity("Maximum file size exceeds", HttpStatus.FORBIDDEN);
			
		}catch (Exception e) {
			System.out.println("just an exception");
			System.out.println(e.getStackTrace().toString());
			System.out.println(e.getLocalizedMessage());
			return new ResponseEntity("Some thing went wrong please try again later", HttpStatus.BAD_GATEWAY);
		}
		


	}

	// Multiple file upload
	@PostMapping("/api/upload/multi")
	public ResponseEntity<List<Image>> uploadFileMulti(
			@RequestParam("files") MultipartFile[] uploadfiles) {

		// logger.debug("Multiple file upload!");

		// Get file name
		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

		if (StringUtils.isEmpty(uploadedFileName)) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {

			List<Image> images= saveUploadedFiles(Arrays.asList(uploadfiles));
			return new ResponseEntity(images, HttpStatus.OK);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}catch (org.springframework.web.multipart.MultipartException eo) {
			
			System.out.println("Maximum file size exceeds");
			
			
			return new ResponseEntity("Maximum file size exceeds", HttpStatus.FORBIDDEN);
		}catch (Exception e) {
			
			System.out.println("just an exception");
			return new ResponseEntity("Some thing went wrong please try again later", HttpStatus.BAD_GATEWAY);
		}

		

	}

	// save file
	private List<Image> saveUploadedFiles(List<MultipartFile> files) throws IOException {

		List<Image> imagesToBeReturned = new ArrayList<Image>();
		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; // next pls
			}

//			byte[] bytes = file.getBytes();
//			Path path = Paths.get(UPLOADED_FOLDER + getSaltString()+URLEncoder.encode(file.getOriginalFilename(), "UTF-8"));
//			
//			System.out.println(path.toString());
//			
//			
//			Files.write(path, bytes);
			
			String fileName = storageService.store(file);
			
			
			 Resource fileo = storageService.loadAsResource(fileName);
			 
			
			 System.out.println(fileo.getURL().toString());
			 
			
			 
			 String url = DOWNLOAD_FOLDER+"/"+fileo.getFilename().toString();
			 System.out.println("demo download url when u add the base url: " + url);
			 
			 
			 System.out.println(fileo.getFilename().toString());
			 
			 
			imageService.addImage(new Image(fileo.getFilename().toString()));
			imagesToBeReturned.add(imageService.findByUrl(fileo.getFilename().toString()));

		}
		return imagesToBeReturned;
	}
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 100) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return URLEncoder.encode(saltStr);

    }
}