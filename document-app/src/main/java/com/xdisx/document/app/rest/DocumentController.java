package com.xdisx.document.app.rest;

import com.xdisx.document.api.DocumentApi;
import com.xdisx.document.api.dto.request.DocumentRequestDto;
import com.xdisx.document.app.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DocumentController implements DocumentApi {

    private final DocumentService documentService;
    @Override
    public ResponseEntity<Resource> generateDocument(DocumentRequestDto documentRequestDto) {
       log.info("Generate document request: {}", documentRequestDto);

        ByteArrayInputStream bis = documentService.generateDocument(documentRequestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=document.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
