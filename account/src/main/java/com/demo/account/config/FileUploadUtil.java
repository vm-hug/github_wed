package com.demo.account.config;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/* uploadDir : đường dẩn nơi lưu file trong thư mục tạo ra
   fileName : tên file mà bạn muốn lưu
   multipartFile : file được upload từ  client
*/

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName , MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // Tạo thư mục nếu chưa tồn tại
        }

        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream , filePath , StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save file: " + fileName, e);
        }
    }
}
