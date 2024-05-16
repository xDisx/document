package com.xdisx.document.e2e.document.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xdisx.document.api.dto.request.DocumentRequestDto;
import com.xdisx.document.e2e.CucumberBootstrap;
import com.xdisx.document.e2e.common.utils.AssertionsUtils;
import com.xdisx.document.e2e.document.rest.DocumentController;
import com.xdisx.document.e2e.document.steps.context.GenerateDocumentContext;
import feign.FeignException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import static com.xdisx.document.e2e.common.utils.ReadFileUtil.readJson;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
public class DocumentSteps extends CucumberBootstrap {
    private GenerateDocumentContext generateDocumentContext;

    private final DocumentController documentController;

    @When("I send a request to generate a new document")
    public void ISendARequestToGenerateANewDocument() {
        var documentRequest = getDocumentRequest();
        generateDocumentContext = new GenerateDocumentContext();

        try{
            generateDocumentContext.setDocumentResponse(documentController.generateDocument(documentRequest));
            generateDocumentContext.setStatus(OK.value());
        } catch (FeignException e) {
            generateDocumentContext.setException(e);
            generateDocumentContext.setStatus(e.status());
        }
    }

    @Then("I receive the generated document")
    public void iReceiveTheGeneratedDocument() {
        AssertionsUtils.assertAPISuccess(generateDocumentContext);

        var response = generateDocumentContext.getDocumentResponse();
        assertNotNull(response);
        assertNotNull(response.getBody());
    }

    private DocumentRequestDto getDocumentRequest() {
        var filePath = "/json/document/DocumentRequestDto.json";
        DocumentRequestDto documentRequestDto = null;

        try{
            documentRequestDto = readJson(DocumentRequestDto.class, filePath);
        } catch (JsonProcessingException e) {
            fail("Failed to read json file");
        }

        return documentRequestDto;
    }
}
