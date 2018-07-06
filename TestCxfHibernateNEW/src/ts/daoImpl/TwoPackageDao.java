package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import ts.daoBase.BaseDao;
import ts.model.TransPackageContent;
import ts.model.TwoPackage;

public class TwoPackageDao extends BaseDao<TwoPackage,String> {
	public TwoPackageDao(){
		super(TwoPackage.class);
	}
	
	public TwoPackage get(String smallId, String bigId){
		List<TwoPackage> list  = new ArrayList<TwoPackage>();
		
		list = super.findBy("SN", true, 
				Restrictions.sqlRestriction("smaID = '"+ smallId + "' and bigID = '" + bigId +"'"));
		if(list.size() == 0)
			return null;
		return list.get(0);
	}
	
	//从小包裹找到大包裹
	public TwoPackage getBySma(String smallId){
		List<TwoPackage> list  = new ArrayList<TwoPackage>();
		
		list = super.findBy("SN", true, 
				Restrictions.sqlRestriction("smaID = '"+ smallId +"'"));
		if(list.size() == 0)
			return null;
		return list.get(0);
	}
	
	
	//从大包裹找到小包裹
		public List<TwoPackage> getByBig(String bigId){
			List<TwoPackage> list  = new ArrayList<TwoPackage>();
			
			list = super.findBy("SN", true, 
					Restrictions.sqlRestriction("bigID = '"+ bigId +"'"));
			if(list.size() == 0)
				return null;
			return list;
		}
	
	
}
