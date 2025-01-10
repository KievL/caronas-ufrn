package br.ufrn.umbrella.caronasufrn.configs.files;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

@Configuration
public class FilebaseConfig {
    
    @Value("${filebase.s3.endpoint}")
	private String endpoint;
	
	@Value("${filebase.s3.access-key}")
	private String accessKey;
	
	@Value("${filebase.s3.secret-key}")
	private String secretKey;

    @Bean
	public S3Client s3client() {
		return S3Client.builder()
				.region(Region.US_EAST_1)
				.endpointOverride(URI.create(endpoint))
				.credentialsProvider(StaticCredentialsProvider.create(
						AwsBasicCredentials.create(accessKey, secretKey)
				))
				.serviceConfiguration(S3Configuration.builder()
						.pathStyleAccessEnabled(true)
						.build())
				.build();
	}
}
