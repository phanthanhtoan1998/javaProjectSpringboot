package com.example.demo.controllers;

import com.example.demo.Service.IStorageService;
import com.example.demo.models.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/v1/fileUpload")
public class FileUpdateController {
    @Autowired
    private IStorageService storageService ;
    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String generatedFileName =storageService.storeFile(file) ;
            return   ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    "ok", "upload file  successfully", generatedFileName));



        }
        catch (Exception exception) {
           return   ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", exception.getMessage(), ""));



        }
    }
//    @GetMapping("/files/{fileName:.+}")
//    public ResponseEntity<byte[]>readdetaiFile(@PathVariable String fileName){
//        return ;
//    }
}
