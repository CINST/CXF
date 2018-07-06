package ts.daoImpl;

import ts.daoBase.BaseDao;
import ts.model.UserPosition;

public class UserPositionDao extends BaseDao<UserPosition,String> {
	public UserPositionDao(){
		super(UserPosition.class);
	}
	
}
