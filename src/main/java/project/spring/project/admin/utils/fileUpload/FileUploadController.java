package project.spring.project.admin.utils.fileUpload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private final UploadFileService uploadFileService;

    public FileUploadController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @PostMapping
    public void upload(@RequestParam("file") MultipartFile file) {
//        System.out.println(("uploaded file " + file.getOriginalFilename()));
//        System.out.println(file.getSize());
//
//        try {
//            saveFileToLocalDisk(file);
//            System.out.println("save local");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("save-local fail");
//        }
//
//        UploadFileDTO fileInfo = null;
//        try {
//            fileInfo = getUploadedFileInfo(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Save the file info to database
//        saveFileToDatabase(fileInfo);
//
//        MultipartFile multipartFile;



    }


    private void saveFileToLocalDisk(MultipartFile multipartFile) throws IOException,
            FileNotFoundException {

        String outputFileName = getOutputFilename(multipartFile);

        FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
    }

    private void saveFileToDatabase(UploadFileDTO uploadedFile) {

         uploadFileService.saveFile(uploadedFile);

    }

    private String getOutputFilename(MultipartFile multipartFile) {

        return getDestinationLocation() + multipartFile.getOriginalFilename();
    }

    private UploadFileDTO getUploadedFileInfo(MultipartFile multipartFile) throws IOException {

        UploadFileDTO fileInfo = new UploadFileDTO();

        fileInfo.setSize(multipartFile.getSize());
        fileInfo.setType(multipartFile.getContentType());
        fileInfo.setLocation(getDestinationLocation());

        return fileInfo;
    }

    private String getDestinationLocation() {
        return "D:/Programming/Projects/SoftUni/Java Web/Spring Project/upload-files/";
    }
}
