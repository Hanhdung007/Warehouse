package warehouse.exam.demo.config;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomRedirectStrategy {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        redirectStrategy.sendRedirect(request, response, url);
    }
}

