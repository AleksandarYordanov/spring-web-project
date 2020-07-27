package project.spring.project.admin.utils.googleCloudStorage;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class GoogleCloudStorageServiceImpl implements GoogleCloudStorageService{

   private final static String SERVICE_ACCOUNT_JSON_PATH = "D:\\Programming\\Projects\\SoftUni\\Java Web\\Spring Project\\Spring Web E-commerce-e040a2049303.json";
   private final static String WEB_PATH = "https://storage.googleapis.com/spring-web-project-e-commerce/";

   @Override
    public String saveToWeb(byte[] bytes , String directory) throws IOException {

        String bucketName = "spring-web-project-e-commerce";
        Bucket bucket = getBucket(bucketName);

       Blob blob = bucket.create(directory,bytes,"jpg");
       // Blob blob = bucket.create("opi/photo", inputStream, "jpg");


        return WEB_PATH+directory;
    }

    private Bucket getBucket(String bucketName) throws IOException {

        Storage storage =
                StorageOptions.newBuilder()
                        .setCredentials(
                                ServiceAccountCredentials.fromStream(
                                        new FileInputStream(SERVICE_ACCOUNT_JSON_PATH)))
                        .build()
                        .getService();
        Bucket bucket = storage.get(bucketName);
        if (bucket == null) {
            throw new IOException("Bucket not found:"+bucketName);
        }
        return bucket;
    }

}
