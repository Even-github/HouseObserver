package per.even.HouseObserver.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import per.even.HouseObserver.admin.controller.AdminUserController;

public class SessionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, 
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        if (session.getAttribute(AdminUserController.ADMINUSERID) != null){
            // session存在
            chain.doFilter(httpRequest, httpResponse);
            return;
        } else {
        	String serverUrl = "/noPermisson";
        	RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(serverUrl);
            dispatcher.forward(servletRequest, servletResponse);
            chain.doFilter(httpRequest, httpResponse);
            return;
        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
