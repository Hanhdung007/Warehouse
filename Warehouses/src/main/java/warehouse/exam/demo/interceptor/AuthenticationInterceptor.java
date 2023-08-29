package warehouse.exam.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        String path = request.getServletPath();
        boolean isAuthenticated = session.getAttribute("isAuthenticated") == null || session.getAttribute("isAuthenticated").equals("false");
        if (path.contains("/auth/login")) {
            return true;
        } else if (path.contains("/api/") && isAuthenticated) {
            return true;
        } else {
            response.sendRedirect("/auth/login");
            return false;
        }
    }
}
