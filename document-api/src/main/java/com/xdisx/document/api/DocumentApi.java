package com.xdisx.document.api;

import com.xdisx.document.api.dto.request.DocumentRequestDto;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface DocumentApi {
    String ROOT_PATH = "/xdisx";

    @PostMapping(ROOT_PATH + "/document")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Resource> generateDocument(@RequestBody @Valid DocumentRequestDto documentRequestDto);
}
