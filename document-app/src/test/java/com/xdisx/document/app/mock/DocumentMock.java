package com.xdisx.document.app.mock;

import com.xdisx.document.api.dto.request.DocumentRequestDto;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class DocumentMock {
    public static DocumentRequestDto getDocumentRequest() {
        Map<String, Object> data = new HashMap<>();
        data.put("contractId", BigInteger.ONE);
        return DocumentRequestDto.builder().data(data).build();
    }
}
