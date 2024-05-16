package com.xdisx.document.e2e.document.rest;

import com.xdisx.document.api.DocumentApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        contextId = "documentService",
        name = "${xdisx.service.document.client-name}",
        url = "${xdisx.service.document.url}")
public interface DocumentController extends DocumentApi {}
