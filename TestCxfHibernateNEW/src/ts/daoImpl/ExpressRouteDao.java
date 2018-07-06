package ts.daoImpl;

import ts.daoBase.BaseDao;
import ts.model.ExpressRoute;

public class ExpressRouteDao extends BaseDao<ExpressRoute,String> {
	public ExpressRouteDao(){
		super(ExpressRoute.class);
	}
	
}
