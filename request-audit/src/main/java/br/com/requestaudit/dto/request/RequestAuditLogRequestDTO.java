package br.com.requestaudit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAuditLogRequestDTO {
    private String url;
    private String headers;
    private String method;
    private String body;
}