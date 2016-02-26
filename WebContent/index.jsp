<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%  
	net.sf.jguard.jee.authentication.http.HttpAuthenticationUtils httpUtils = (net.sf.jguard.jee.authentication.http.HttpAuthenticationUtils) session
            .getAttribute(net.sf.jguard.jee.authentication.http.HttpConstants.AUTHN_UTILS);
	javax.security.auth.Subject subject = null;
     if (httpUtils != null) {
         subject = httpUtils.getSubject();
     }
     boolean local = true;
     String authenticationScope = (String) session.getServletContext().getAttribute(
             net.sf.jguard.ext.SecurityConstants.AUTHENTICATION_SCOPE);
     if (net.sf.jguard.ext.SecurityConstants.JVM_SCOPE.equalsIgnoreCase(authenticationScope)) {
         local = false;
     }
     if (subject == null || session.getAttribute(net.sf.jguard.jee.authentication.http.HttpConstants.AUTHN_UTILS) == null) {
         try {
             net.sf.jguard.jee.authentication.http.HttpAuthenticationUtils.authenticate((HttpServletRequest) request,
                     (HttpServletResponse) response, false, local);
             subject = ((net.sf.jguard.jee.authentication.http.HttpAuthenticationUtils) session
                     .getAttribute(net.sf.jguard.jee.authentication.http.HttpConstants.AUTHN_UTILS)).getSubject();
         } catch (Exception e) {
         }
     }
    String value = "";
    try {
        if (subject != null) {
            java.util.Set<Object> privateCredentials = subject.getPrivateCredentials();
            java.util.Iterator<Object> it = privateCredentials.iterator();
            net.sf.jguard.core.authentication.credentials.JGuardCredential cred = null;

            while (it.hasNext()) {
                cred = (net.sf.jguard.core.authentication.credentials.JGuardCredential) it.next();
                if (cred.getId().equals(com.gtm.csims.jaas.UserCredentialName.login.name())) {
                    value = (String) cred.getValue();
                    break;
                }
            }
        }
    } catch (SecurityException sex) {
    }
    if(value.equalsIgnoreCase("guest")){%>
        <c:redirect url="/Logon.do"/>
    <%
    }else{%>
        <c:redirect url="/Welcome.do"/>
   <% }
    %>