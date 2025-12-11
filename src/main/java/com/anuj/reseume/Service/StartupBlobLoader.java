package com.anuj.reseume.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class StartupBlobLoader  {
	
	@Value("${azure.storage.connection-string}")
	private String connectionString;
	
	@Value("${azure.storage.container}")
	private String containerName;
	
	@Value("${azure.storage.blob}")
	private String blobName;
	
	
	@Autowired
	private BlobService blobService;
	
	public static String RESUME_CONTENT;
	
	@PostConstruct
	public void init() {
		RESUME_CONTENT = blobService.readBlob(connectionString, containerName, blobName);
		System.out.println("✔ Resume loaded into static cache at startup. Size: " + RESUME_CONTENT.length());
	}
	
	
}
