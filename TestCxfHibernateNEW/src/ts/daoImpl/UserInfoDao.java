package ts.daoImpl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import ts.daoBase.BaseDao;
import ts.model.UserInfo;


public class UserInfoDao extends BaseDao<UserInfo, Integer> {
	private UserInfoDao userInfoDao; /////????
    private RegionDao regionDao;
	
    
    
    public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao dao) {
		this.userInfoDao = dao;
	}
    
    
    
    
	public RegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}
	public UserInfoDao(){
		super(UserInfo.class);
	}


	//获得指定用户UID的所有用户列表
		public int checkLogin(int uid,String pwd) {	
			int flag;
			List<UserInfo> userInfo =  findBy("UID", true,Restrictions.eq("UID", uid),Restrictions.eq("PWD", pwd));
	        if(userInfo.isEmpty()) {
	        	flag = 1;
			return flag;
		}
	        else {
	        	flag = 0;
	        	return flag;
	        }

		}
		
		
		
		//获得指定用户UID的所有用户列表
				public int checkLogin(String name,String pwd) {	
					int flag;
					List<UserInfo> userInfo =  findBy("Name", true,Restrictions.eq("Name", name),Restrictions.eq("PWD", pwd));
			        if(userInfo.isEmpty()) {
			        	flag = 1;
					return flag;
				}
			        else {
			        	flag = 0;
			        	return flag;
			        }

				}
	/*	
		int flag;
		List<UserInfo> userInfo =  findBy("UID", true,Restrictions.eq("name", name));
        if(userInfo.isEmpty()) {  
        	//userInfo.setStatus(0);
        flag = 1;
		return flag;
	}
        else {
        	flag = 0;
        	return flag;
        }

    }
		
		*/
		
		public UserInfo LogOut(int UID) {
			List<UserInfo> userInfo =  findBy("UID", true,Restrictions.eq("UID", UID));
			userInfo.get(0).setStatus(0);
			return userInfo.get(0);
		}
		
		
		//根据用户UID获得用户列表
		public UserInfo getUserInfoLoad(int uid) {   
		        List<UserInfo> userInfo =  findBy("UID", true,Restrictions.eq("UID", uid));
		        if(userInfo.isEmpty()) {
		               return  new UserInfo();
		        }
		        else{
	    	            return userInfo.get(0);
	            }

	    }
		
/*		//根据username获得用户列表
				public UserInfo getUserInfoLoad1(String name) {   
					    System.out.println("56756757567567567");
				        List<UserInfo> userInfo =  findBy("Name", true,Restrictions.eq("Name", name));
				        System.out.println("3456456456");
				        if(userInfo.isEmpty()) {
				               return  new UserInfo();
				        }
				        else{
				        	    System.out.println("123123123123123123123");
			    	            return userInfo.get(0);
			            }

			    }*/
				
				
		public List<UserInfo> getAllUserInfoLoad() {
			List<UserInfo> userInfo =  findBy("UID", true);
		        if(userInfo.isEmpty()) {
		               return null;
		        }
		        else{
	    	            return userInfo;  //返回所用用户的详细列表
	            }

	    }


     //检验用户名是否存在
		public int checkRegister(String name) {
			int flag;
			List<UserInfo> userInfo =  findBy("Name", true,Restrictions.eq("Name", name));
	        if(userInfo.isEmpty()) {  
	        	//userInfo.setStatus(0);
	        flag = 1;
			return flag;
		}
	        else {
	        	flag = 0;
	        	return flag;
	        }

	    }
		
		
		public int checkDelete(int uid) {
			int flag;
			List<UserInfo> userInfo =  findBy("UID", true,Restrictions.eq("UID", uid));
	        if(userInfo.isEmpty()) {  
	        	//userInfo.setStatus(0);
	        flag = 0;
			return flag;
		}
	        else {
	        	flag = 1;
	        	return flag;
	        }

		}

		/*根据dptid返回用户地址的详细信息*/
		public UserInfo get(int uid) {
			System.out.println("111");
			UserInfo ui = super.get(uid);
			System.out.println("222");
			ui.setdptString(regionDao.getFullNameRegionBydptID(ui.getDptID()));	
			System.out.println("333");
			return ui;
		}
		
		/*根据name返回用户地址的详细信息*/
		public UserInfo get1(String name) {
			System.out.println("123749172349712397");
			List<UserInfo> userInfo = findBy("Name", true,Restrictions.eq("Name", name));
			//UserInfo ui = super.get(userInfo.get(0).getUID());
			System.out.println("222");
			userInfo.get(0).setdptString(regionDao.getFullNameRegionBydptID(userInfo.get(0).getDptID()));	
			System.out.println("333");
			return userInfo.get(0);
		}
		
		
}