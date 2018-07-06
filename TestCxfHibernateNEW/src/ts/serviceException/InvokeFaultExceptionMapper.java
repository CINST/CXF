package ts.serviceException;

import java.util.Locale;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.service.spi.ServiceException;
import org.springframework.orm.hibernate4.HibernateObjectRetrievalFailureException;

@Provider  
public class InvokeFaultExceptionMapper implements ExceptionMapper {  
	  
    public Response toResponse(Throwable ex) {  
        StackTraceElement[] trace = new StackTraceElement[1];  
           trace[0] = ex.getStackTrace()[0];  
           ex.setStackTrace(trace); 
           
         //Response.status(Status.OK)用状态码来创建ResponseBuilder
           ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);  
           rb.type("application/json;charset=UTF-8");  
           
          if (ex instanceof HibernateObjectRetrievalFailureException) {//自定义的异常类  
        	   HibernateObjectRetrievalFailureException e = (HibernateObjectRetrievalFailureException) ex;  
//        	   rb = Response.status(Response.Status.NOT_FOUND);  
//               rb.type("application/json;charset=UTF-8");
        	   //这里需要写的复杂一点,把英文的错误转义
              rb.entity(e.getLocalizedMessage());   
           }else if (ex instanceof ServiceException) {//自定义的异常类  
               ServiceException e = (ServiceException) ex;  
               rb.entity(e.getMessage());  
           }else{  
               rb.entity(ex);  
           }  
           rb.language(Locale.SIMPLIFIED_CHINESE);  
           Response r = rb.build();    
       //用ReponseBuilder来创建Response，ResponseBuilder是一个用来创建单个Response实例的工厂类，
       //首先将要创建的response对象的状态存起来,最后当状态设置完成了，就使用builder去初始化Response
          
           return r;  
   }  
}  
