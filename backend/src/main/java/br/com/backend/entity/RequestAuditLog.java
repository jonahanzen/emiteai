package br.com.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "request_audit_log")
@Getter
@Setter
@Entity
public class RequestAuditLog extends AbstractEntityCreatedUpdated {
    private String url;
    private String headers;
    private String method;
    private String body;
}
