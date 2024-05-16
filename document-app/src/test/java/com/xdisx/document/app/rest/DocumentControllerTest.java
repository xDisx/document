package com.xdisx.document.app.rest;

import com.xdisx.document.app.mock.DocumentMock;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xdisx.document.api.dto.request.DocumentRequestDto;
import com.xdisx.document.app.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {
  @Mock
  private DocumentService documentService;

  @InjectMocks
  private DocumentController documentController;

  private DocumentRequestDto documentRequestDto;

  @BeforeEach
  void setUp() {
    documentRequestDto = new DocumentRequestDto();
    Map<String, Object> data = new HashMap<>();
    data.put("contractId", BigInteger.ONE);

    documentRequestDto.setData(data);
  }

  @Test
  void generateDocumentTest() {
    ByteArrayInputStream bis = new ByteArrayInputStream(new byte[]{1, 2, 3, 4});
    when(documentService.generateDocument(any(DocumentRequestDto.class))).thenReturn(bis);

    ResponseEntity<Resource> response = documentController.generateDocument(documentRequestDto);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("inline; filename=document.pdf", response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));
    assertEquals(org.springframework.http.MediaType.APPLICATION_PDF, response.getHeaders().getContentType());
    assertEquals(InputStreamResource.class, response.getBody().getClass());
  }
}
