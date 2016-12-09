package arhangel.dim;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Добавляем заголовки для Cross-Domain запросов
 */
public class CorsFilter implements Filter {

    @Override
    public void destroy() {
        // ...
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (response instanceof HttpServletResponse) {
                HttpServletResponse alteredResponse = ((HttpServletResponse) response);
                //addCorsHeader(alteredResponse);
            }
            chain.doFilter(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex);
            request.getRequestDispatcher("/WEB-INF/views/jsp/error.jsp").forward(request, response);
        }

    }

    private void addCorsHeader(HttpServletResponse response) {

        System.out.println("DEADBEAF: !!!");

        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//        response.addHeader("Access-Control-Max-Age", "1728000");
    }

}