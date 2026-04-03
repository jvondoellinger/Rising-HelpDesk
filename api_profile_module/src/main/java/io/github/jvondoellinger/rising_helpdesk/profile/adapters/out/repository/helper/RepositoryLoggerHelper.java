package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.repository.helper;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public final class RepositoryLoggerHelper {
	private String requestId;
	private String classname;

	public void afterSave() {
		log.info("LOG_ID: {} |  Initializing JPA bridge to save {} into the database", requestId, classname);
	}
	public void beforeSave() {
		log.info("LOG_ID: {} | Transaction completed successfully for entity {}", requestId, classname);
	}

	public void onError() {
		log.error("LOG_ID: {} | Transaction failed for {}.", requestId, classname);
	}

	public void rollingBack() {
		log.error("LOG_ID: {} | Rolling back transaction {}.", requestId, classname);
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
}
