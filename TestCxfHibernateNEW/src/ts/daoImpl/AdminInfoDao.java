

package ts.daoImpl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import ts.daoBase.BaseDao;
import ts.model.AdminInfo;
import ts.model.UserInfo;

public class AdminInfoDao extends BaseDao<AdminInfo, Integer> {
	public AdminInfoDao(){
		super(AdminInfo.class);
	}
	
	
	/*//获得指定用户UID的所有用户列表
			public int checkLogin(int UID,String PWD) {	
				int flag;
				List<AdminInfo> adminInfo =  findBy("UID", true,Restrictions.eq("UID", UID),Restrictions.eq("PWD", PWD));
		        if(adminInfo.isEmpty()) {
		        	flag = 1;
				return flag;
			}
		        else {
		        	flag = 0;
		        	return flag;
		        }

			}
			*/
			public int checkLogin(String adminName,String PWD) {	
				int flag;
				List<AdminInfo> adminInfo =  findBy("adminName", true,Restrictions.eq("adminName", adminName),Restrictions.eq("PWD", PWD));
		        if(adminInfo.isEmpty()) {
		        	flag = 1;
				return flag;
			}
		        else {
		        	flag = 0;
		        	return flag;
		        }

			}
			
			
			public AdminInfo getAdminInfoLoad(String adminName) {   
				System.out.println("111");
		        List<AdminInfo> AdminInfo =  findBy("adminName", true,Restrictions.eq("adminName", adminName));
		        System.out.println("333");
		        if(AdminInfo.isEmpty()) {
		               return  new AdminInfo();
		        }
		        else{
	    	            return AdminInfo.get(0);
	            }

	    }
}
