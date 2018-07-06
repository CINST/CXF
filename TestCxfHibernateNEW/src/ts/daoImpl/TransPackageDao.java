package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import ts.daoBase.BaseDao;
import ts.model.ExpressSheet;
import ts.model.TransPackage;
import ts.model.TwoPackage;

public class TransPackageDao extends BaseDao<TransPackage,String> {
	
	private RegionDao regionDao;
	public RegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}
	
	public TransPackageDao(){
		super(TransPackage.class);
	}
	
	public TransPackage get(String id) {
		
		System.out.println("---------a");
		TransPackage pkg = super.get(id);     //首先调用基类的方法
		System.out.println(pkg);
		
		String sourceNode=pkg.getSourceNode();
		String targetNode=pkg.getTargetNode();
		System.out.println(sourceNode);
		
		if(sourceNode!=null && targetNode!=null) {
		String sourceRegion=sourceNode.substring(0, 6);
		String targetRegion=targetNode.substring(0, 6);
		
		System.out.println(sourceRegion);
		
		String value1=regionDao.getRegionNameByID(sourceRegion)+"网点";
		System.out.println(value1);
		String value2=regionDao.getRegionNameByID(targetRegion)+"网点";
		System.out.println(value2);
		//增添有转运结点的名字字符串
		pkg.setSourceNodeString(value1);
		pkg.setTargetNodeString(value2);
		System.out.println("-------------1");
		} 
		return pkg;
	}
	
	  //获得指定网点包裹ID的所有包裹列表
		public List<TransPackage> getListInNode(String NodeId) {	
			String sql = "{alias}.ID in (select smaID from TwoPackage where bigID = '"+NodeId+"')";
			List<TransPackage> list = new ArrayList<TransPackage>();
			list = findBy("ID", true, Restrictions.sqlRestriction(sql));		
			return list;
		}
		
		//获得指定快件ID的所有包裹列表
		public TransPackage getPackage(String express_id) {	
			String sql = "{alias}.ID in (select PackageID from TransPackageContent where Status = 1 and ExpressID = '"+express_id+"')";
			List<TransPackage> list = new ArrayList<TransPackage>();
			list = findBy("ID", true, Restrictions.sqlRestriction(sql));		
			return list.get(0);
		}
		
		//从大包裹找小包裹
		public List<TransPackage> getSmallInBig(String bigId){
			List<TransPackage> list  = new ArrayList<TransPackage>();
			String sql = "{alias}.ID in (select smaID from TwoPackage where bigID = '"+bigId+"')";
			
			list = findBy("ID", true, Restrictions.sqlRestriction(sql));
			if(list.size() == 0)
				return null;
			return list;
		}
		
		//获得指定网点包裹ID的所有空包裹列表
		public List<TransPackage> getEmptyPkgListInNode(String NodeId) {	
			String sql = "{alias}.ID in (select smaID from TwoPackage where smallContent = 0 and bigID = '"+NodeId+"')";
			List<TransPackage> list = new ArrayList<TransPackage>();
			list = findBy("ID", true, Restrictions.sqlRestriction(sql));		
			return list;
		}
}
