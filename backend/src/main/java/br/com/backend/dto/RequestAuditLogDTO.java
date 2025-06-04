package br.com.backend.dto;

import lombok.Data;

@Data
public class RequestAuditLogDTO {
    private String url;
    private String headers;
    private String method;
    private String body;
}
