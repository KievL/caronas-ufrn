package br.ufrn.umbrella.caronasufrn.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@RequiredArgsConstructor
@Service
public class FilebaseService{
	private final S3Client s3Client;
		
	public String uploadImage(String bucketName, String key, MultipartFile productFile) throws IOException{
		//check if bucket exists
		try {
			HeadBucketRequest headBucketRequest = HeadBucketRequest.builder()
					.bucket(bucketName)
					.build();
			
			s3Client.headBucket(headBucketRequest);
		}catch(NoSuchBucketException e) {
			CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
					.bucket(bucketName)
					.build();
			s3Client.createBucket(createBucketRequest);
		}
		
		Path tempFile = Files.createTempFile(null, ".tmp");
		productFile.transferTo(tempFile);
		
		PutObjectResponse response = s3Client.putObject(
			PutObjectRequest.builder()
				.bucket(bucketName)
				.key(key)
				.build(),
			RequestBody.fromFile(tempFile));
		
		Files.delete(tempFile);	
		
		// returns image CID
		return response.sdkHttpResponse().headers().get("x-amz-meta-cid").get(0);
	}
	
	public void removeImage(String bucketName, String key) {
		s3Client.deleteObject(
			DeleteObjectRequest.builder()
				.bucket(bucketName)
				.key(key)
				.build());			
	}
}

