package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.session.mappers;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.session.data.SessionData;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class SessionDataMapper {
	public SessionData fromRequest(RequestAttributes requestAttributes) throws UnknownHostException {
		var servlet = (ServletRequestAttributes) requestAttributes;
		var request = servlet.getRequest();

		var userAgent = request.getHeader("User-Agent");
		var ipString = request.getRemoteAddr();

		var inet = InetAddress.getByName(ipString);

		return new SessionData(userAgent, inet);
	}
}
