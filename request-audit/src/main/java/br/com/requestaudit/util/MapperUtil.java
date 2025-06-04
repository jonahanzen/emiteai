package br.com.requestaudit.util;

import br.com.requestaudit.dto.request.RequestAuditLogRequestDTO;
import br.com.requestaudit.entity.RequestAuditLog;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static RequestAuditLog mapToRequestAuditLog(RequestAuditLogRequestDTO requestAuditLogDTO) {
        RequestAuditLog requestAuditLog = new RequestAuditLog();
        BeanUtils.copyProperties(requestAuditLogDTO, requestAuditLog);
        return requestAuditLog;
    }

    public static RequestAuditLog mapToRequestAuditLog(String payload) {
        try {
            return objectMapper.readValue(payload, RequestAuditLog.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to map payload to RequestAuditLog", e);
        }
    }
}
