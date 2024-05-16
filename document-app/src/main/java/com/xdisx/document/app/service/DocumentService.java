package com.xdisx.document.app.service;

import com.xdisx.document.api.dto.request.DocumentRequestDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import java.io.ByteArrayInputStream;

public interface DocumentService {

    ByteArrayInputStream generateDocument(DocumentRequestDto documentRequestDto);
}
