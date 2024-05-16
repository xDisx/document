package com.xdisx.document.e2e.document.steps.context;

import com.xdisx.document.e2e.common.context.ValidatedContext;
import feign.FeignException;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@Data
public class GenerateDocumentContext implements ValidatedContext {
    private int status;
    private FeignException exception;
    ResponseEntity<Resource> documentResponse;
}
