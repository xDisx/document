package com.xdisx.document.app.service;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.xdisx.document.api.dto.request.DocumentRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    @Override
    public ByteArrayInputStream generateDocument(DocumentRequestDto documentRequestDto) {
        var data = documentRequestDto.getData();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(out));
            Document document = new Document(pdfDoc, PageSize.A4);
            document.setMargins(20, 20, 20, 20);

            // Add Title
            Paragraph title = new Paragraph("Maintenance Contract")
                    .setFontSize(24)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);
            document.add(new Paragraph("\n\n"));

            // Add Contract Information Table
            Table table = new Table(2);
            table.setWidth(500);
            table.addCell("Contract ID").addCell(data.get("contractId").toString());
            table.addCell("Device Code").addCell(data.get("deviceCode").toString());
            table.addCell("Device Type").addCell(data.get("deviceType").toString());
            table.addCell("Device Acquisition Date").addCell(data.get("deviceAcquisitionDate").toString());
            table.addCell("Contract Price").addCell(data.get("contractPrice").toString());
            table.addCell("Contract Period").addCell(data.get("contractPeriod").toString());
            table.addCell("Customer First Name").addCell(data.get("customerFirstName").toString());
            table.addCell("Customer Last Name").addCell(data.get("customerLastName").toString());
            table.addCell("Customer Email").addCell(data.get("customerEmail").toString());
            table.addCell("Customer Phone Number").addCell(data.get("customerPhoneNumber").toString());
            table.addCell("Customer Address").addCell(data.get("customerAddress").toString());
            table.addCell("Contract Start Date").addCell(data.get("contractStartDate").toString());
            table.addCell("Contract Planned End Date").addCell(data.get("contractPlannedEndDate").toString());
            table.addCell("Product Name").addCell(data.get("productName").toString());
            table.addCell("Product Description").addCell(data.get("productDescription").toString());
            document.add(table);
            document.add(new Paragraph("\n\n"));

            // Add Legal Information
            Paragraph legalInfo = new Paragraph("Legal Information")
                    .setFontSize(16)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(legalInfo);

            Paragraph legalText = new Paragraph("This contract is for the maintenance of devices as specified. " +
                    "The terms and conditions of this contract are subject to the laws and regulations " +
                    "of the jurisdiction in which the contract is executed. Any disputes arising " +
                    "from this contract will be resolved through arbitration in accordance with the " +
                    "rules of the International Chamber of Commerce. The parties agree to " +
                    "comply with all applicable laws and regulations in the performance of this contract.");
            document.add(legalText);
            document.add(new Paragraph("\n\n"));

            // Add Terms and Conditions
            Paragraph termsTitle = new Paragraph("Terms and Conditions")
                    .setFontSize(16)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(termsTitle);

            Paragraph termsText = new Paragraph("1. The customer agrees to provide access to the device for maintenance.\n" +
                    "2. The dealer agrees to perform maintenance in a timely and professional manner.\n" +
                    "3. The customer shall notify the dealer of any issues with the device immediately.\n" +
                    "4. The dealer shall not be liable for any damage caused by unauthorized repairs.\n" +
                    "5. This contract is valid for the specified period and can be renewed upon agreement.");
            document.add(termsText);
            document.add(new Paragraph("\n"));

            // Add Generation Date and Signatures
            Paragraph date = new Paragraph("Date of Generation: " + LocalDate.now())
                    .setTextAlignment(TextAlignment.LEFT);
            document.add(date);
            document.add(new Paragraph("\n"));

            Paragraph signatures = new Paragraph("Signatures:")
                    .setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(16)
                    .setBold();
            document.add(signatures);

            document.add(new Paragraph("\n\n"));

            Table signatureTable = new Table(2);
            signatureTable.setWidth(500);
            signatureTable.addCell("Customer: ___________________").setPaddingRight(50);
            signatureTable.addCell("Dealer: ___________________").setPaddingRight(50);
            document.add(signatureTable);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
