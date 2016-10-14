package ${package};

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by yannis on 16-9-16.
 */
public class BaseController {

    private boolean isDebug = true;

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    public String exp(HttpServletRequest request, Exception e) {

        log.error("Golbal Exception caught at "+request.getRequestURL()+": ",e);

        if(isDebug == true){
            //HttpServletResponse response = request.getServletContext().getServlet(arg0)
        }

        return "page/error/error";
    }

}