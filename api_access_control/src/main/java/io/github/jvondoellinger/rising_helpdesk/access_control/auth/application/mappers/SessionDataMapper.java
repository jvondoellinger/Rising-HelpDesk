package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.data.SessionData;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class SessionDataMapper {
	public SessionData fromRequest(RequestAttributes requestAttributes) {
		var servlet = (ServletRequestAttributes) requestAttributes;
		var request = servlet.getRequest();
		var userAgent = request.getHeader("User-Agent");
		var ip = request.getHeader("X-Forwarded-For");

		return new SessionData();
	}
}
