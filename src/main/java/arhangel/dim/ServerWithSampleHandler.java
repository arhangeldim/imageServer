package arhangel.dim;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

/*
Поднимает интанс jetty-сервера и устанавливает его хэндлер
Запустите main() и зайдите на localhost:8080

 */
public class ServerWithSampleHandler {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new MyHandler());

        server.start();
        server.join();
    }

    static class MyHandler extends AbstractHandler {

        public void handle(String target, Request baseRequest,

                           HttpServletRequest request,
                           HttpServletResponse response

        ) throws IOException, ServletException {

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            response.getWriter().println("<h1>Hello World</h1>");
        }
    }
}
