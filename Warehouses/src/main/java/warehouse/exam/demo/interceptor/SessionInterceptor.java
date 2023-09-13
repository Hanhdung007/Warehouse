package warehouse.exam.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        var loggedInUser = (Boolean) session.getAttribute("loggedInUser");
        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (loggedInUser == null || !loggedInUser) {
            response.sendRedirect("/auth/login");
            return false;
        }
        return true;
    }
}
