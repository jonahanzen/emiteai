package br.com.requestaudit.repository;

import br.com.requestaudit.entity.RequestAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestAuditLogRepository extends JpaRepository<RequestAuditLog, Long> {
}
