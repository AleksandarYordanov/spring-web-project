package project.spring.project.admin.utils.googleCloudStorage;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class GoogleCloudStorageServiceImpl {

   private final static String SERVICE_ACCOUNT_JSON_PATH = "D:\\Programming\\Projects\\SoftUni\\Java Web\\Spring Project\\Spring Web E-commerce-e040a2049303.json";
   private final static String WEB_PATH = "https://storage.googleapis.com/spring-web-project-e-commerce/";

    public String createSmth(byte[] bytes , String directory) throws IOException {



        Storage storage =
                StorageOptions.newBuilder()
                        .setCredentials(
                                ServiceAccountCredentials.fromStream(
                                        new FileInputStream(SERVICE_ACCOUNT_JSON_PATH)))
                        .build()
                        .getService();

        String bucketName = "spring-web-project-e-commerce";
        Bucket bucket = getBucket(bucketName);

        InputStream inputStream = new FileInputStream(new File("D:\\Programming\\Projects\\SoftUni\\Java Web\\Spring Project\\upload-files\\5991.jpg"));
       Blob blob = bucket.create(directory,bytes,"jpg");
       // Blob blob = bucket.create("opi/photo", inputStream, "jpg");

        System.out.println(("Blob Link:" + blob.getSelfLink()));
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
