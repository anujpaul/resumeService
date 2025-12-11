package com.anuj.reseume.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

@Service
public class BlobService {
	
	private BlobContainerClient  containerClient;


	
	public String readBlob(String connectionString, String containerName, String blobName) {
		
		this.containerClient = new BlobContainerClientBuilder()
				.connectionString(connectionString)
				.containerName(containerName)
				.buildClient();
		BlobClient blobClient = containerClient.getBlobClient(blobName);
		
		try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(blobClient.openInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to read resume file.";
        }
		
	}

	
}
