package com.activedge.controller;

import com.activedge.dto.ETLResponse;
import com.activedge.service.CertificateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@Slf4j
@RequestMapping("/api/v1/certificate")
public class ValidateCertificateController {

    private String fileDirectory="" ;
    @PostMapping("/validate")
    public ResponseEntity<?> validateCertificate(@RequestParam(value = "csvrecord",required = true) MultipartFile file){
        boolean checkExtension = !FilenameUtils.getExtension(file.getOriginalFilename()).isEmpty()&&FilenameUtils.getExtension(file.getOriginalFilename()).equalsIgnoreCase("") ;
        if(!checkExtension){
            ETLResponse etlResponse = ETLResponse.builder().responseMessage("Invalid file uploaded").status(String.valueOf(HttpStatus.BAD_REQUEST.value())).build() ;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(etlResponse)  ;
        }
        File filetoupload = CertificateService.saveFile(file,fileDirectory) ;  // save file for processing
       // Collection collection = Arrays.asList(fileHeaders.split(",")) ;
        List<String> list  = new CopyOnWriteArrayList<>() ;  //this part is not clear.you didnt specify the kind of certificaten and i ask you in a mail
        return ResponseEntity.ok(list);
    }
}
