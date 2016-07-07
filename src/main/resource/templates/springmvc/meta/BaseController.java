package com.zk.oms.basis;

import com.zk.oms.cfg.OmsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(com.zk.oms.basis.BaseController.class);
    
    @ExceptionHandler  
    public String exp(HttpServletRequest request, Exception e) {  
          
        log.error("Golbal Exception caught at "+request.getRequestURL()+": ",e);
        
        if(OmsConfig.isDebug==true){
        	//HttpServletResponse response = request.getServletContext().getServlet(arg0)
        }
        	
        return "page/error/error";
    }  
}  
  