package com.clinic.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UrlAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	 protected final Log logger = LogFactory.getLog(this.getClass());
	    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	    protected UrlAuthenticationSuccessHandler() {
	        super();
	    }

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication a) throws IOException, ServletException {
	        handle(httpServletRequest, httpServletResponse, a);
	        clearAuthenticationAttributes(httpServletRequest);
	    }

	    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
	        final String targetUrl = determineTargetUrl(authentication);

	        if (response.isCommitted()) {
	            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
	            return;
	        }

	        redirectStrategy.sendRedirect(request, response, targetUrl);
	    }

	    protected String determineTargetUrl(Authentication authentication) {
	        boolean isPatient = false;
	        boolean isDoctor = false;
	        boolean isAdmin = false;

	        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	        for (final GrantedAuthority grantedAuthority : authorities) {
	            if (grantedAuthority.getAuthority().equals("ROLE_PATIENT")) {
	                isPatient = true;
	                break;
	            } else if (grantedAuthority.getAuthority().equals("ROLE_DOCTOR")) {
	                isDoctor = true;
	                break;
	            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
	            	isAdmin = true;
	            	break;
	            }
	            
	        }

	        if (isPatient) {
	            return "/patient/";
	        } else if (isDoctor) {
	            return "/doctor/";
	        } else if (isAdmin){
	        	return "/admin/";
	        } else {
	            return "/";
	        }

	    }
	    
	    /**
	     * Removes temporary authentication-related data which may have been stored in the session
	     * during the authentication process.
	     */
	    protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
	        final HttpSession session = request.getSession(false);

	        if (session == null) {
	            return;
	        }

	        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	    }

	    public RedirectStrategy getRedirectStrategy() {
	        return redirectStrategy;
	    }

	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }

}
