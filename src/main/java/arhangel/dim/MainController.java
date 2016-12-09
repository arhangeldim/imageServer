package arhangel.dim;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import arhangel.dim.arhangel.dim.core.User;

@Controller
public class MainController {

    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public ModelAndView getPage() {
        ModelAndView model = new ModelAndView("HelloWorldPage");
        model.addObject("msg", "hello response!");

        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alisa"));
        users.add(new User(2, "Bob"));

        return users;
    }

    /*
     * Запрос мапится на определнный урл
     * Простой запрос/ответ
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void test(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            name = StringUtils.isNotEmpty(name) ? name : "юзер";

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(String.format("<h1>Превед %s!</h1>", name));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
        @ResponseBody - содержимое возвращается в теле HTTP-ответа
        produces = The producible media types of the mapped request, narrowing the primary mapping
        produces = MediaType.IMAGE_PNG_VALUE выставит Content-Type:image/png
     */
    @ResponseBody
    @RequestMapping(value = "/getPhoto", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] testphoto(HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        InputStream in = this.getClass().getResourceAsStream("/images/service.png");
        return IOUtils.toByteArray(in);
    }

}