package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import ts.daoBase.BaseDao;
import ts.model.Region;
import ts.model.UserInfo;


public class UserInfoDao extends BaseDao<UserInfo, Integer> {
	public UserInfoDao(){
		super(UserInfo.class);
	}
	
	//���ָ���û�UID�������û��б�
	public UserInfo checkLogin(int uid,String pwd) {	
		List<UserInfo> userInfo =  findBy("UID", true,Restrictions.eq("UID", uid),Restrictions.eq("PWD", pwd));
        if(userInfo.isEmpty()) {
		return new UserInfo();
	}
        else {
        	return userInfo.get(0);
        }

	}
}