package com.xdisx.document.e2e.common.context;

import feign.FeignException;

public interface ValidatedContext {
    int getStatus();

    FeignException getException();
}
