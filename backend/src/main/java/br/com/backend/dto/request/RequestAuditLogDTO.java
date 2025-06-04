package br.com.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAuditLogDTO {
    private String url;
    private String headers;
    private String method;
    private String body;
}
