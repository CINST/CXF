package ts.interceptor;


import java.util.List;
import java.util.Map;

import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;

import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.hibernate.criterion.Restrictions;

import ts.daoImpl.UserInfoDao;
import ts.model.UserInfo;



public class HelloInInterceptor extends AbstractPhaseInterceptor<Message> {  
	
	private UserInfoDao userInfoDao;
	
	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao dao) {
		this.userInfoDao = dao;
	}
	  
    public HelloInInterceptor(String phase) {  
        super(phase);  
    }  
      
    public HelloInInterceptor() {  
        super(Phase.USER_LOGICAL);  
    }  
  
    /** <功能详细描述> 
     * 创 建 人:  XX 
     * 创建时间:  2012-9-28 下午02:34:07   
     * @param arg0 
     * @throws Fault 
     * @see [类、类#方法、类#成员] 
     */  

        @Override
        public void handleMessage(Message message) throws Fault {
            System.out.println("服务器端--请求头解析器");
        
            //System.out.println("3"+Msg.get(arg0));
            Message request = message.getExchange().getInMessage();
            Map<String, List<String>> headers = CastUtils
                        .cast((Map<?, ?>) request.get(Message.PROTOCOL_HEADERS));   //获取header参数
            System.out.println(headers);
            
           
            if (headers.get("token")==null || headers.get("token").size()==0) {
                System.out.println("没有token参数");
                message.getInterceptorChain().abort();
               return;
            } 

             String token = headers.get("token").get(0);

            if (token == null || token.length() == 0) {
               System.out.println("没有token值");
               message.getInterceptorChain().abort();           //token没有，阻止拦截链执行下去
               return;
            }
            else {  
            	  System.out.println(token.substring(0, 1));
            	  //String temp="?";
            	 if(token.substring(0, 1).equals("?")){  //业务app
            		  String username="";
            		  String pwd="";
                      String str=token.substring(1);
                      System.out.println(str);
                      int flag=0;
            		  for(int i=0;i<str.length();i++){  
            		        char ch = str.charAt(i);
            		        if(ch == '+') {
            		        	flag=1;
            		        	continue;
            		        }
            		        
            		        if(flag==0) {
            		        	int a = ch+1;
            		        	char ch1 = (char)a;
            		        	username+=ch1;
            		        }
            		        else {
            		        	int a = ch+1;
            		        	char ch1 = (char)a;
            		        	pwd+=ch1;
            		        }
            		        
            		  }  
            		  System.out.println("username:"+username);
            		  System.out.println("pwd:"+pwd);
            		  UserInfo user=userInfoDao.findBy("Name", true,Restrictions.eq("Name", username)).get(0);
            		  System.out.println("user=="+user);
            		  String username1=user.getName();
            		  String pwd1 = user.getPWD();
            		  if(username1.equals(username) && pwd1.equals(pwd)) {
            			  System.out.println("成功登陆！");
            		  }
            		  else {
            			   System.out.println("用户名和密码不正确");
            			   message.getInterceptorChain().abort();
            		  }
            	 }
            	 else if(token.substring(0, 1).equals("!")) {  //客户APP
            		  System.out.println("不拦截！");
            	 }
                 else if(token.equals("tok")){
                	  System.out.println("不拦截！");
                 }
                 else {
                	  message.getInterceptorChain().abort();
                 }
            }
            
       
         
        }
              
    
}  
