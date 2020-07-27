package project.spring.project.admin.utils.googleCloudStorage;

import java.io.IOException;

public interface GoogleCloudStorageService {


    String saveToWeb(byte[] bytes, String directory) throws IOException;
}
