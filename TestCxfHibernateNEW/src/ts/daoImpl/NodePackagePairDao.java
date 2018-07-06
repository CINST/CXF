package ts.daoImpl;

import ts.daoBase.BaseDao;
import ts.model.NodePackagePair;

public class NodePackagePairDao extends BaseDao<NodePackagePair,String> {
	public NodePackagePairDao(){
		super(NodePackagePair.class);
	}
	
}
