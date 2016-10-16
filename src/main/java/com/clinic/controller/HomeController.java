package com.clinic.controller;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String doctor() {
		return "login";
	}

	@RequestMapping("/lastSecurityException")
	@ResponseBody
	public String getSecurityMessage(WebRequest webRequest) {
		Exception lastException = (Exception) webRequest.getAttribute(
				WebAttributes.AUTHENTICATION_EXCEPTION,
				WebRequest.SCOPE_SESSION);
		return lastException.getMessage();
	}
}
