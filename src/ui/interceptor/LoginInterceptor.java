package ui.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import ui.action.SessionVariables;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor implements StrutsStatics {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginInterceptor.class);

	public String intercept(ActionInvocation invocation) throws Exception {		
		ActionContext context = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(HTTP_REQUEST);
		HttpSession session = request.getSession(true);

		// Is there a "user" object stored in the user's HttpSession?
		Object user = session.getAttribute(SessionVariables.LOGGED_IN_USER.toString());
		if (user == null) {
			// The user has not logged in yet.

			// Is the user attempting to log in right now?
			String loginAttempt = request.getParameter("loginAttempt");

			/* The user is attempting to log in. */
			if (!StringUtils.isBlank(loginAttempt)) {
				return invocation.invoke();
			}
			return ActionSupport.LOGIN;
		} else {
			return invocation.invoke();
		}
	}

	public void init() {
		System.out.println("Initializing LoginInterceptor...");
	}

	public void destroy() {
		System.out.println("Destroying LoginInterceptor...");
	}
}
