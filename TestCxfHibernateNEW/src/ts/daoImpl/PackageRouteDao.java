package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import ts.daoBase.BaseDao;
import ts.model.PackageRoute;
import ts.model.TransPackageContent;

public class PackageRouteDao extends BaseDao<PackageRoute,String> {
	public PackageRouteDao(){
		super(PackageRoute.class);
	}
	
	public List<PackageRoute> getByPkg(String packageId){
		List<PackageRoute> list  = new ArrayList<PackageRoute>();
		list = super.findBy("SN", true, 
				Restrictions.sqlRestriction("PackageID = '" + packageId +"'"));
		if(list.size() == 0)
			return null;
		return list;
	}
	
}
