package br.com.requestaudit.service;

import br.com.requestaudit.entity.RequestAuditLog;
import br.com.requestaudit.repository.RequestAuditLogRepository;
import br.com.requestaudit.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestAuditLogService {

    private final RequestAuditLogRepository requestAuditLogRepository;

    public RequestAuditLogService(RequestAuditLogRepository requestAuditLogRepository) {
        this.requestAuditLogRepository = requestAuditLogRepository;
    }

    @Transactional
    public void saveLog(String payload) {
        RequestAuditLog log = MapperUtil.mapToRequestAuditLog(payload);
        System.out.println("Saving RequestAuditLog: " + log);
        requestAuditLogRepository.save(log);
    }

}
