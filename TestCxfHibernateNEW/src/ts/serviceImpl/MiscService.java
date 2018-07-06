package ts.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ts.daoImpl.CustomerInfoDao;
import ts.daoImpl.RegionDao;
import ts.daoImpl.TransNodeDao;
import ts.model.CodeNamePair;
import ts.model.CustomerInfo;
import ts.model.ExpressSheet;
import ts.model.Region;
import ts.model.TransNode;
import ts.serviceInterface.IMiscService;

public class MiscService implements IMiscService{
	//TransNodeCatalog nodes;	//自己做的缓存和重定向先不要了,用Hibernate缓存对付一下，以后加上去
	//RegionCatalog regions;
	private TransNodeDao transNodeDao;           //转运结点
	private RegionDao regionDao;                 //地区
	private CustomerInfoDao customerInfoDao;     //顾客信息


	public TransNodeDao getTransNodeDao() {
		return transNodeDao;
	}

	public void setTransNodeDao(TransNodeDao dao) {
		this.transNodeDao = dao;
	}

	public RegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}

	public CustomerInfoDao getCustomerInfoDao() {
		return customerInfoDao;
	}

	public void setCustomerInfoDao(CustomerInfoDao dao) {
		this.customerInfoDao = dao;
	}

	public MiscService(){
//		nodes = new TransNodeCatalog();
//		nodes.Load();
//		regions = new RegionCatalog();
//		regions.Load();
	}

	
	
//转运结点操作的访问方法=======================================================================	
	@Override
	public TransNode getNode(String code) {
		return transNodeDao.get(code);
	}

	@Override
	public List<TransNode> getNodesList(String regionCode, int type) {
		List<TransNode> list = new ArrayList<TransNode>();
		
		return null;
	}
	
	
	    //得到县级网点的列表，返回的是网点代码和对应的网点名字
		@Override
		public List<CodeNamePair> getTwnNodeList(String city) {		
			List<Region> listrg = regionDao.getTownList(city);
			List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
			for(Region rg : listrg){
				String code = rg.getORMID()+"00";
				String name = rg.getTwn()+"网点";
				CodeNamePair cn = new CodeNamePair(code,name);
				listCN.add(cn);
			}
			return listCN;
		}
	
	

//顾客信息操作访问方法=======================================================================	
	
	//通过Name属性找到符合条件的顾客列表，并以电话号码TelCode升序排序
	@Override
	public List<CustomerInfo> getCustomerListByName(String name) {
//		List<CustomerInfo> listci = customerInfoDao.findByName(name);
//		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
//		for(CustomerInfo ci : listci){
//			CodeNamePair cn = new CodeNamePair(String.valueOf(ci.getID()),ci.getName());
//			listCN.add(cn);
//		}
//		return listCN;
		return customerInfoDao.findByName(name);
	}

	//通过TelCode属性找到符合条件的顾客列表，并以电话号码TelCode升序排序
	@Override
	public List<CustomerInfo> getCustomerListByTelCode(String TelCode) {
//		List<CustomerInfo> listci = customerInfoDao.findByTelCode(TelCode);
//		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
//		for(CustomerInfo ci : listci){
//			CodeNamePair cn = new CodeNamePair(String.valueOf(ci.getID()),ci.getName());
//			listCN.add(cn);
//		}
//		return listCN;
		return customerInfoDao.findByTelCode(TelCode);
	}

	//通过id查找顾客
	@Override
	public Response getCustomerInfo(String id) {
		CustomerInfo cstm = customerInfoDao.get(Integer.parseInt(id));
//		try{
//			cstm.setRegionString(regionDao.getRegionNameByID(cstm.getRegionCode()));	//这部分功能放到DAO里去了
//		}catch(Exception e){}
		return Response.ok(cstm).header("EntityClass", "CustomerInfo").build(); 
	}
	
	//删除某一id的顾客
	@Override
	public Response deleteCustomerInfo(int id) {
		customerInfoDao.removeById(id);
		return Response.ok("Deleted").header("EntityClass", "D_CustomerInfo").build(); 
	}
    
	//保存顾客信息
	@Override
	public Response saveCustomerInfo(CustomerInfo obj) {
		try{
			customerInfoDao.save(obj);			
			return Response.ok(obj).header("EntityClass", "R_CustomerInfo").build(); 
		}
		catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}
	
	
	
	
//地区region操作访问方法=======================================================================
	
	//得到省的列表，返回的是地区码和对应的省名
	@Override
	public List<CodeNamePair> getProvinceList() {		
		List<Region> listrg = regionDao.getProvinceList();
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for(Region rg : listrg){
			CodeNamePair cn = new CodeNamePair(rg.getORMID(),rg.getPrv());
			listCN.add(cn);
		}
		return listCN;
	}

	//得到某个省的市列表，返回的是地区码和对应的市名
	@Override
	public List<CodeNamePair> getCityList(String prv) {
		List<Region> listrg = regionDao.getCityList(prv);
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for(Region rg : listrg){
			CodeNamePair cn = new CodeNamePair(rg.getORMID(),rg.getCty());
			listCN.add(cn);
		}
		return listCN;
	}

	//得到某个市的县列表，返回的是地区码和对应的县名
	@Override
	public List<CodeNamePair> getTownList(String city) {
		List<Region> listrg = regionDao.getTownList(city);
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for(Region rg : listrg){
			CodeNamePair cn = new CodeNamePair(rg.getORMID(),rg.getTwn());
			listCN.add(cn);
		}
		return listCN;
	}

	//根据一个regioncode得到其对应的地区的全名（包括省、市、县）
	@Override
	public String getRegionString(String code) {
		return regionDao.getRegionNameByID(code);
	}

	//根据一个regioncode得到对应的region实例，并为其赋全名
	@Override
	public Region getRegion(String code) {
		return regionDao.getFullNameRegionByID(code);
	}

	
	

	
	
	
	
	
	
	
	
	
//=======================================================================
	@Override
	public void CreateWorkSession(int uid) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void RefreshSessionList() {
		// TODO Auto-generated method stub
		
	}
}
