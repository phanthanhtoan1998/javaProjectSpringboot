package com.example.demo.Service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageStorageService implements IStorageService {
    private final Path storageFolder = Paths.get("uploads");
    //constructor

    public ImageStorageService() {
        try {

            Files.createDirectories(storageFolder);
        } catch (Exception exception) {

            throw new RuntimeException("Cannot initialize storage", exception);
        }
    }

    private boolean isImageFile(MultipartFile file) {

        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[]{"png", "jpg", "jpeg", "bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {


            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file. ");

            }

            if (!isImageFile(file)) {
                throw new RuntimeException("You can only upload image file ");
            }
            float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
            if (fileSizeInMegabytes > 5.0f) {
                throw new RuntimeException("File must be <=  5MB ");

            }
            String fileException = FilenameUtils.getExtension((file.getOriginalFilename()));
            String generatedFileName = UUID.randomUUID().toString().replace("-", "");
            generatedFileName= generatedFileName+ "."+fileException ;
            Path destinationFilePath = this.storageFolder.resolve(
                    Paths.get(generatedFileName)
            ).normalize().toAbsolutePath();
            if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outside current directory  ");

            }
            try(InputStream inputStream=file.getInputStream()){
                Files.copy(inputStream,destinationFilePath, StandardCopyOption.REPLACE_EXISTING) ;

            }
            return  generatedFileName ;


        } catch (Exception exception) {

            throw new RuntimeException("Failed to store file.", exception);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public byte[] readFilecontent(String fileName) {
        return new byte[0];
    }

    @Override
    public void deleteAllfiles() {

    }
}
