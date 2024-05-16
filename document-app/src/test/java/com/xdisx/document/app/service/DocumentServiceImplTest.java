package com.xdisx.document.app.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.xdisx.document.api.dto.request.DocumentRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {

    private DocumentServiceImpl documentService;
    private DocumentRequestDto documentRequestDto;

    @BeforeEach
    void setUp() {
        documentService = new DocumentServiceImpl();

        Map<String, Object> data = new HashMap<>();
        data.put("contractId", "12345");
        data.put("deviceCode", "DVC001");
        data.put("deviceType", "Type A");
        data.put("deviceAcquisitionDate", "2023-05-01");
        data.put("contractPrice", "1000");
        data.put("contractPeriod", "12 months");
        data.put("customerFirstName", "John");
        data.put("customerLastName", "Doe");
        data.put("customerEmail", "john.doe@example.com");
        data.put("customerPhoneNumber", "1234567890");
        data.put("customerAddress", "123 Main St");
        data.put("contractStartDate", "2024-01-01");
        data.put("contractPlannedEndDate", "2024-12-31");
        data.put("productName", "Product X");
        data.put("productDescription", "Description of Product X");

        documentRequestDto = new DocumentRequestDto();
        documentRequestDto.setData(data);
    }

    @Test
    void generateDocumentTest() throws Exception {
        ByteArrayInputStream bis = documentService.generateDocument(documentRequestDto);

        PdfDocument pdfDoc = new PdfDocument(new PdfReader(bis));
        String textFromPage = PdfTextExtractor.getTextFromPage(pdfDoc.getFirstPage());

        assertTrue(textFromPage.contains("Maintenance Contract"));
        assertTrue(textFromPage.contains("Contract ID"));
        assertTrue(textFromPage.contains("12345"));
        assertTrue(textFromPage.contains("Device Code"));
        assertTrue(textFromPage.contains("DVC001"));
        assertTrue(textFromPage.contains("Customer First Name"));
        assertTrue(textFromPage.contains("John"));
        assertTrue(textFromPage.contains("Customer Last Name"));
        assertTrue(textFromPage.contains("Doe"));
        assertTrue(textFromPage.contains("Legal Information"));
        assertTrue(textFromPage.contains("Terms and Conditions"));

        pdfDoc.close();
    }
}
