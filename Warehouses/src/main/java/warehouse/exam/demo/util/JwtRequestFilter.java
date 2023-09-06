package warehouse.exam.demo.util;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import warehouse.exam.demo.service.AccountService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final AccountService accountService;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtRequestFilter(AccountService accountService, JwtTokenUtil jwtTokenUtil) {
        this.accountService = accountService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        logger.info("Authorization header value: " + requestTokenHeader);
        if (StringUtils.isNotEmpty(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);
            try {
                UserDetails userDetails = accountService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));

                // Validate and possibly refresh token
                String validatedToken = jwtTokenUtil.validateAndRefreshToken(jwtToken, userDetails);
                if (validatedToken != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext()
                            .setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (IllegalArgumentException e) {
                logger.error("Unable to fetch JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token is expired");
                redirectToLoginPageWithMessage(response);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        chain.doFilter(request, response);
    }

    // Hàm để redirect về trang đăng nhập với thông báo lỗi
    private void redirectToLoginPageWithMessage(HttpServletResponse response) throws IOException {
        // Gửi thông báo lỗi dưới dạng parameter trong URL hoặc làm cách khác
        response.sendRedirect("/auth/login?error=" + URLEncoder.encode("Your Token Has Expired. Please Login again!", StandardCharsets.UTF_8));
    }
}
