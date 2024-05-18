package com.xdisx.document.e2e.document.rest;

import com.xdisx.document.api.DocumentApi;
import com.xdisx.document.e2e.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        contextId = "documentService",
        name = "${xdisx.service.document.client-name}",
        url = "${xdisx.service.document.url}",
        configuration = FeignConfig.class)
public interface DocumentController extends DocumentApi {}
