package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import ts.daoBase.BaseDao;
import ts.model.ExpressHistory;


public class ExpressHistoryDao extends BaseDao<ExpressHistory,String>{
	
	
	
	public ExpressHistoryDao(){
		super(ExpressHistory.class);
	}
	
	
	public List<ExpressHistory> getExpressList(String express_id) {	
		List<ExpressHistory> list  = new ArrayList<ExpressHistory>();
		list = super.findBy("SN", true, 
				Restrictions.sqlRestriction("ExpressID = '"+ express_id+"'"));
		System.out.println("查到的list22是："+list);
		if(list.size() == 0)
			return null;
		return list;
    }
	
	public ExpressHistory getByExpress(String express_id) {	
		List<ExpressHistory> list  = new ArrayList<ExpressHistory>();
		list = super.findBy("tim", false, 
				Restrictions.sqlRestriction("ExpressID = '"+ express_id+"'"));
		System.out.println("查到的list11是："+list);
		if(list.size() == 0)
			return null;
		return list.get(0);
    }
	
}
	
