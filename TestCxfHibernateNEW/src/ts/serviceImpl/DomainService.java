package ts.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.hibernate.criterion.Criterion;

import ts.daoImpl.AdminInfoDao;
import ts.daoImpl.CommentInfoDao;
import ts.daoImpl.CustomerInfoDao;
import ts.daoImpl.ExpressHistoryDao;
import ts.daoImpl.ExpressRouteDao;
import ts.daoImpl.ExpressSheetDao;
import ts.daoImpl.NodePackagePairDao;
import ts.daoImpl.PackageRouteDao;
import ts.daoImpl.RegionDao;
import ts.daoImpl.TransHistoryDao;
import ts.daoImpl.TransNodeDao;
import ts.daoImpl.TransPackageContentDao;
import ts.daoImpl.TransPackageDao;
import ts.daoImpl.TwoPackageDao;
import ts.daoImpl.UserInfoDao;
import ts.daoImpl.UserPositionDao;
import ts.daoImpl.UsersPackageDao;
import ts.model.AdminInfo;
import ts.model.CommentInfo;
import ts.model.CustomerInfo;
import ts.model.ExpressHistory;
import ts.model.ExpressRoute;
import ts.model.ExpressSheet;
import ts.model.PackageRoute;
import ts.model.TransHistory;
import ts.model.TransNode;
import ts.model.TransPackage;
import ts.model.TransPackageContent;
import ts.model.TwoPackage;
import ts.model.UserInfo;
import ts.model.UserPosition;
import ts.model.UsersPackage;
import ts.serviceInterface.IDomainService;

public class DomainService implements IDomainService {
	
	private ExpressSheetDao expressSheetDao;
	private TransPackageDao transPackageDao;
	private CommentInfoDao commentInfoDao;
	private TransHistoryDao transHistoryDao;
	private TransPackageContentDao transPackageContentDao;
	private UserPositionDao userPositionDao;
	private UserInfoDao userInfoDao;
	private PackageRouteDao packageRouteDao;
	private ExpressRouteDao expressRouteDao;
	private CustomerInfoDao customerInfoDao;
	private UsersPackageDao usersPackageDao;
	private TwoPackageDao twoPackageDao;
	private NodePackagePairDao nodePackagePairDao;
	private TransNodeDao transNodeDao;
	private AdminInfoDao adminInfoDao;
	private ExpressHistoryDao expressHistoryDao; 
	private RegionDao regionDao;
	
	public RegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}
	

	public AdminInfoDao getAdminInfoDao() {
		return adminInfoDao;
	}

	public void setAdminInfoDao(AdminInfoDao dao) {
		this.adminInfoDao = dao;
	}
	
	public CommentInfoDao getCommentInfoDao() {
		return commentInfoDao;
	}

	public void setCommentInfoDao(CommentInfoDao dao) {
		this.commentInfoDao = dao;
	}
	
	
	public ExpressSheetDao getExpressSheetDao() {
		return expressSheetDao;
	}

	public void setExpressSheetDao(ExpressSheetDao dao) {
		this.expressSheetDao = dao;
	}

	public TransPackageDao getTransPackageDao() {
		return transPackageDao;
	}

	public void setTransPackageDao(TransPackageDao dao) {
		this.transPackageDao = dao;
	}

	public TransHistoryDao getTransHistoryDao() {
		return transHistoryDao;
	}

	public void setTransHistoryDao(TransHistoryDao dao) {
		this.transHistoryDao = dao;
	}

	public TransPackageContentDao getTransPackageContentDao() {
		return transPackageContentDao;
	}

	public void setTransPackageContentDao(TransPackageContentDao dao) {
		this.transPackageContentDao = dao;
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao dao) {
		this.userInfoDao = dao;
	}
	
	public UserPositionDao getUserPositionDao() {
		return userPositionDao;
	}

	public void setUserPositionDao(UserPositionDao dao) {
		this.userPositionDao = dao;
	}
	
	public PackageRouteDao getPackageRouteDao() {
		return packageRouteDao;
	}

	public void setPackageRouteDao(PackageRouteDao dao) {
		this.packageRouteDao = dao;
	}
	
	public ExpressRouteDao getExpressRouteDao() {
		return expressRouteDao;
	}

	public void setExpressRouteDao(ExpressRouteDao dao) {
		this.expressRouteDao = dao;
	}
	
	public CustomerInfoDao getCustomerInfoDao() {
		return customerInfoDao;
	}

	public void setCustomerInfoDao(CustomerInfoDao dao) {
		this.customerInfoDao = dao;
	}

	public UsersPackageDao getUsersPackageDao() {
		return usersPackageDao;
	}

	public void setUsersPackageDao(UsersPackageDao dao) {
		this.usersPackageDao = dao;
	}
	
	public TwoPackageDao getTwoPackageDao() {
		return twoPackageDao;
	}

	public void setTwoPackageDao(TwoPackageDao dao) {
		this.twoPackageDao = dao;
	}
	
	public NodePackagePairDao getNodePackagePairDao() {
		return nodePackagePairDao;
	}

	public void setNodePackagePairDao(NodePackagePairDao dao) {
		this.nodePackagePairDao = dao;
	}
	public TransNodeDao getTransNodeDao() {
		return transNodeDao;
	}

	public void setTransNodeDao(TransNodeDao dao) {
		this.transNodeDao = dao;
	}
	
	public ExpressHistoryDao getExpressHistoryDao() {
		return expressHistoryDao;
	}

	public void setExpressHistoryDao(ExpressHistoryDao dao) {
		this.expressHistoryDao = dao;
	}
	
	
	public Date getCurrentDate() {
		//产生一个不带毫秒的时间,不然,SQL时间和JAVA时间格式不一致
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date tm = new Date();  //获取当前系统时间
		try {
			/*a=sdf.format(date); 将日期装换成字符串形式,2016-08-01
			 *sdf.parse(a); 将字符串装换成日期形式 ,Mon Aug 01 00:00:00 CST 2016 */
			tm= sdf.parse(sdf.format(new Date()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return tm;
	}
	
	
	
//快件操作访问方法=======================================================================
	
	//根据某一属性值，得到一个快件列表
	@Override
	public List<ExpressSheet> getExpressList(String property,
			String restrictions, String value) {
		List<ExpressSheet> list = new ArrayList<ExpressSheet>();
		switch(restrictions.toLowerCase()){
		//如果ID为数字，则精确查询
		case "eq":
			list = expressSheetDao.findBy(property, value, "ID", true);
			break;
		//如果ID为字符串，则模糊查询
		case "like":
			list = expressSheetDao.findLike(property, value+"%", "ID", true);
			break;
		}
		return list;
	}
	
	//根据某两个属性值，得到一个快件列表
		@Override
		public List<ExpressSheet> getExpressByTel(String property1,String value1,String property2,String value2) {
			List<ExpressSheet> list = new ArrayList<ExpressSheet>();
			List<CustomerInfo> customer11=customerInfoDao.findByTelCode(value1);
			List<CustomerInfo> customer22=customerInfoDao.findByTelCode(value2);
			
			CustomerInfo customer1=customer11.get(0);
			CustomerInfo customer2=customer22.get(0);
			
				list = expressSheetDao.findByTwo(property1, customer1, property2, customer2, "ID", true);
				//break;
			//如果ID为字符串，则模糊查询
			//case "like":
				//list = expressSheetDao.findLike(property, value+"%", "ID", true);
				//break;
			//}
			return list;
		}
	
	//获得指定包裹ID的所有快件列表
	@Override
	public List<ExpressSheet> getExpressListInPackage(String packageId){
		List<ExpressSheet> list = new ArrayList<ExpressSheet>();
		list = expressSheetDao.getListInPackage(packageId);
		
		return list;		
	}
    
	//根据id得到一个快件
	@Override
	public Response getExpressSheet(String id) {
		System.out.println("------------ttttttt-----------"); 
		ExpressSheet es = null;
		try{
			 es = expressSheetDao.get(id); //查询数据库中有没有此id的快递单
		} catch (Exception e1) {}

		if(es == null){
				return Response.ok("快件不存在！！！").header("EntityClass", "F_ExpressSheet").build();  
		}
		else    return Response.ok(es).header("EntityClass", "ExpressSheet").build(); 
	}

	
	//创建一个新快件，并为其设置id和uid（分拣员）
	@Override
	public Response newExpressSheet(String id, int uid) {
		
		ExpressSheet es = null;
		try{
			es = expressSheetDao.get(id); //查询数据库中有没有此id的快递单
		} catch (Exception e1) {}

		if(es != null){
//			if(es.getStatus() != 0)
//				return Response.ok(es).header("EntityClass", "L_ExpressSheet").build(); //已经存在,且不能更改
//			else
				return Response.ok(es).header("EntityClass", "E_ExpressSheet").build();  
				//该快件已经存在
		}
		try{
			//快件不存在
			String pkgId = userInfoDao.get(uid).getReceivePackageID();  //得到该接收该快件的分拣员的接收包裹号
			ExpressSheet nes = new ExpressSheet();
			nes.setID(id);
			nes.setType(0);
			nes.setAccepter(String.valueOf(uid));
			nes.setAccepteTime(getCurrentDate());
			nes.setStatus(ExpressSheet.STATUS.STATUS_CREATED);
			System.out.println("66666");
			expressSheetDao.save(nes);
			System.out.println("55555");
			//放到收件包裹中
			MoveExpressIntoPackage(nes.getID(),pkgId);
			return Response.ok(nes).header("EntityClass", "ExpressSheet").build(); 
		}
		catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}
    
	//保存快件信息
	@Override
	public Response saveExpressSheet(ExpressSheet obj) {
		try{
			System.out.println("\n寄件人是------"+obj.getSender());
			//如果快件状态不是新建，即快件已经在运送过程中了，其属性不能再更改
			if(obj.getStatus() != ExpressSheet.STATUS.STATUS_CREATED){
				return Response.ok("快件运单已付运!无法保存更改!").header("EntityClass", "E_ExpressSheet").build(); 
			}
			expressSheetDao.save(obj);			
			return Response.ok(obj).header("EntityClass", "R_ExpressSheet").build(); 
		}
		catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}

	//扫描刚刚生成的运单，保存之后，点击收件后，快件状态改为“转运”
	@Override
	public Response ReceiveExpressSheetId(String id, int uid) {
		try{
			ExpressSheet nes = expressSheetDao.get(id);
		//必须是刚刚创建的运单，才可以说是揽收，揽收过后快件的状态就一直是转运，直到签收
			if(nes.getStatus() != ExpressSheet.STATUS.STATUS_CREATED){
				return Response.ok("快件运单状态错误!无法收件!").header("EntityClass", "E_ExpressSheet").build(); 
			}
			if(nes.getSender()==null || nes.getRecever()==null) {
				return Response.ok("先保存快件信息！").header("EntityClass", "E_ExpressSheet").build(); 
			}
			nes.setAccepter(String.valueOf(uid)); //设置
			nes.setAccepteTime(getCurrentDate());
			nes.setStatus(ExpressSheet.STATUS.STATUS_TRANSPORT);  
			expressSheetDao.save(nes);
			
			//更新快件历史
			ExpressHistory history = new ExpressHistory();
			history.setExpress(nes);
			String nodeId = userInfoDao.get(uid).getDptID();
			TransNode sourceNode = transNodeDao.get(nodeId);
			history.setSourceNode(sourceNode);
			history.setStatus(0);
			history.setTime(getCurrentDate());
			expressHistoryDao.save(history);
			
			return Response.ok(nes).header("EntityClass", "ExpressSheet").build(); 
		}
		catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}
	
	//揽收完成（与画图有关）
	@Override
	public Response receiveFinished(int uid){
		try {
		     String receivePackage=userInfoDao.get(uid).getReceivePackageID();
		     TransPackage pkg=transPackageDao.get(receivePackage);
		     
		     TransHistory history=new TransHistory();
		     history.setPkg(pkg);
		     history.setUIDFrom(uid);
		     history.setUIDTo(uid);
		     history.setActTime(getCurrentDate());
		     transHistoryDao.save(history);
		     return Response.ok(history).header("EntityClass", "TransHistory").build();
		}
		catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}

	@Override
	public Response DispatchExpressSheet(String id, int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//将某一快件移进某一包裹
	public boolean MoveExpressIntoPackage(String id, String targetPkgId) {
		
		System.out.println("*****4****"+targetPkgId);
		TransPackage targetPkg = transPackageDao.get(targetPkgId);
		System.out.println("*****5***"+targetPkg.getStatus());
		//包裹的状态快点定义,打开的包裹（新建的包裹）或者货篮才能操作==================================================================
		if((targetPkg.getStatus() == 1) || (targetPkg.getStatus() == 4)){
			System.out.println("*****状态有误！");
			return false;
		}

		//将该快件加到该包裹里
		TransPackageContent pkg_add = new TransPackageContent();
		pkg_add.setPkg(targetPkg);
		System.out.println("*****2");
		pkg_add.setExpress(expressSheetDao.get(id));
		System.out.println("*****1");
		pkg_add.setStatus(TransPackageContent.STATUS.STATUS_ACTIVE); 
		transPackageContentDao.save(pkg_add); 
		System.out.println("*****3");
		return true;
	}

	//将某一快件从某一包裹中移出
	public boolean MoveExpressFromPackage(String id, String sourcePkgId) {
		System.out.println("*****44****"+sourcePkgId);
		TransPackage sourcePkg = transPackageDao.get(sourcePkgId);
		System.out.println("*****55****"+sourcePkg.getStatus());
		/*if((sourcePkg.getStatus() != 0) && (sourcePkg.getStatus() != 2) && (sourcePkg.getStatus() != 3)){
			System.out.println("*****状态有误！");
			return false;
		}
		*/
		
		System.out.println("*****77****");
		TransPackageContent pkg_add = transPackageContentDao.get(id, sourcePkgId);
		//取消该包裹和该快件的关联
		pkg_add.setStatus(TransPackageContent.STATUS.STATUS_OUTOF_PACKAGE);
		System.out.println("*****66****"+pkg_add.getStatus());
		transPackageContentDao.save(pkg_add); 
		return true;
	}
	
     //将快件在两个包裹间转移
	public boolean MoveExpressBetweenPackage(String id, String sourcePkgId, String targetPkgId) {
		//需要加入事务机制
		MoveExpressFromPackage(id,sourcePkgId);
		MoveExpressIntoPackage(id,targetPkgId);
		return true;
	}

	//快件已装入派送车，签收过程
	@Override
	public Response DeliveryExpressSheetId(String id, int uid) {
		try{
			//得到该派送人员的派送包裹id（派送车id）
			String pkgId = userInfoDao.get(uid).getDelivePackageID();
			ExpressSheet nes = expressSheetDao.get(id);
			//只有“转运”状态的快件，才能签收
			if(nes.getStatus() != ExpressSheet.STATUS.STATUS_TRANSPORT){
				return Response.ok("快件运单状态错误!无法交付").header("EntityClass", "E_ExpressSheet").build(); 
			}
			//如果该快件不在该派送员的车中
			if(transPackageContentDao.getSn(id, pkgId) == 0){
				//临时的一个处理方式,断路了包裹的传递过程,自己的货篮倒腾（刷新）一下
				MoveExpressBetweenPackage(id, userInfoDao.get(uid).getReceivePackageID(),pkgId);
				return Response.ok("快件运单状态错误!\n快件信息没在您的派件包裹中!").header("EntityClass", "E_ExpressSheet").build(); 
			}
				
			nes.setDeliver(String.valueOf(uid));
			nes.setDeliveTime(getCurrentDate());
			nes.setStatus(ExpressSheet.STATUS.STATUS_DELIVERIED);
			expressSheetDao.save(nes);
			//从派件包裹中删除
			MoveExpressFromPackage(nes.getID(),pkgId);
			//快件没有历史记录,很难给出收件和交付的记录
			
			//更新快件历史信息
			ExpressHistory history = new ExpressHistory();
	        history.setExpress(nes);
		    history.setStatus(3);
		    history.setTime(getCurrentDate());
		    expressHistoryDao.save(history);
			
			return Response.ok(nes).header("EntityClass", "ExpressSheet").build(); 
		}
		catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}

	//查询快件历史
	@Override
	public List<ExpressHistory> getExpressHistory(String expressId) {
		
		List<ExpressHistory> list = expressHistoryDao.getExpressList(expressId);
		for(ExpressHistory history : list) {
			TransNode sourceNode = history.getSourceNode();
			TransNode targetNode = history.getTargetNode();
			history.setTimeString(history.getTime().toString());
			if(sourceNode != null && targetNode != null) {
				String sourceNodeRegion = history.getSourceNode().getID().substring(0, 6);
				String targetNodeRegion = history.getTargetNode().getID().substring(0, 6);
				System.out.println("源节点是："+sourceNodeRegion+"   目的节点是："+targetNodeRegion);
				history.setSourceString(regionDao.getRegionNameByID(sourceNodeRegion)+"网点");
				history.setTargetString(regionDao.getRegionNameByID(targetNodeRegion)+"网点");
				System.out.println(regionDao.getRegionNameByID(sourceNodeRegion)+"网点");
			}
		}
		System.out.println("返回的list:"+list);
		return list;
	}
	
	
//包裹操作访问方法=======================================================================		
	
	//根据某一属性值，得到一个包裹列表
	@Override
	public List<TransPackage> getTransPackageList(String property,
			String restrictions, String value) {
		List<TransPackage> list = new ArrayList<TransPackage>();
		switch(restrictions.toLowerCase()){
		case "eq":
			list = transPackageDao.findBy(property, value, "ID", true);
			break;
		case "like":
			list = transPackageDao.findLike(property, value+"%", "ID", true);
			break;
		}
		return list;
	}

	//根据id得到一个包裹
	@Override
	public Response getTransPackage(String id) {
		TransPackage es = transPackageDao.get(id);
		return Response.ok(es).header("EntityClass", "TransPackage").build(); 
	}
	
	//得到某一网点或转运车里的包裹
	@Override
	public List<TransPackage> getPkgListInPkg(String pkgId){
		List<TransPackage> list = new ArrayList<TransPackage>();
		list=transPackageDao.getSmallInBig(pkgId);
		return list;
	}
	
	//创建一个新包裹前，判断该包裹是否已经存在
	@Override
	public Response packageExist(String pkgId) {
		TransPackage pkg = null;
		try{
			pkg = transPackageDao.get(pkgId); //查询数据库中有没有此id的包裹
		} catch (Exception e1) {}
        if(pkg == null) {
        	pkg=new TransPackage();
        	pkg.setID("0");
        }
        return Response.ok(pkg).header("EntityClass", "E_TransPackage").build();   //该包裹已经存在
	}

	//创建一个新包裹，并为其设置id和uid（分拣员）
	@Override
	public Response newTransPackage(String id, int uid) {
		TransPackage pkg = null;
		try{
			pkg = transPackageDao.get(id); //查询数据库中有没有此id的包裹
		} catch (Exception e1) {}

		if(pkg != null){
//			if(es.getStatus() != 0)
//				return Response.ok(es).header("EntityClass", "L_ExpressSheet").build(); //已经存在,且不能更改
//			else
				return Response.ok(pkg).header("EntityClass", "E_TransPackage").build();  
				//该包裹已经存在
		}
		
		try{
			//生成新包裹
			userInfoDao.get(uid).setTransPackageID(id);
			TransPackage npk = new TransPackage();
			npk.setID(id);
			npk.setStatus(TransPackage.STATUS.STATUS_CREATED); //包裹状态改为新建
			npk.setCreateTime(getCurrentDate());
			saveTransPackage(npk);
			
		    //建立包裹与转运结点之间的联系
			System.out.println("111");
		    String targetPkgId = userInfoDao.get(uid).getReceivePackageID();
		    System.out.println("222");
		    MoveSmallIntoBig(id,targetPkgId);
		    System.out.println("333");
			
			return Response.ok(npk).header("EntityClass", "TransPackage").build(); 
		}
		catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}

	//保存包裹信息
	@Override
	public Response saveTransPackage(TransPackage obj) {
		
		try{
			if(obj.getStatus() != TransPackage.STATUS.STATUS_CREATED){
				return Response.ok("包裹已付运!无法保存更改!").header("EntityClass", "E_TransPackage").build(); 
			}
			transPackageDao.save(obj);			
			return Response.ok(obj).header("EntityClass", "R_TransPackage").build(); 
		}
		catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}
	
	   //打包过程,将某一id的快件添加到包裹targetId中
		@Override
		public Response packPackage (String id, String targetPkgId) {
			try {
				 TransPackageContent transContent=transPackageContentDao.get(id, targetPkgId);
				 int SN=transPackageContentDao.getSn(id, targetPkgId);
				 System.out.println("***111***"+SN);
				 if(SN != 0) {
					
					 //快件已经在该包裹中
					 if(transContent.getStatus() == 1) {
						 return Response.ok("快件已经在包裹中，无法添加！").header("EntityClass", "ExpressSheet").build();
					 }
					 else {    //快件曾经在这个包裹中
						 transContent.setStatus(1);
						 transPackageContentDao.save(transContent);
						 return Response.ok("成功添加！").header("EntityClass", "ExpressSheet").build();
					 }
				 }
				 else { //快件从没有在这个包裹中
					 ExpressSheet nes = expressSheetDao.get(id);
					 //将该快件与网点的关联取消
					 TransPackage pkg = transPackageDao.getPackage(id);
					 System.out.println("***222***"+pkg);
					 if(pkg!=null) {
						 String pkgId = pkg.getID();
						 MoveExpressFromPackage(id,pkgId);
					 }
					 
				     MoveExpressIntoPackage(id,targetPkgId);
				     return Response.ok(nes).header("EntityClass", "ExpressSheet").build();
				 }
			}
			catch(Exception e)
			{
				return Response.serverError().entity(e.getMessage()).build(); 
			}
		}
		
	   //打包完成后
		@Override
		public Response packFinished(String pkgId) {
			try{
				TransPackage pkg = transPackageDao.get(pkgId);
				if(pkg.getStatus()==0) {
				    pkg.setStatus(TransPackage.STATUS.STATUS_TRANSPORT); //包裹状态改为转运1
				}
				//System.out.println(pkg.getStatus());
				transPackageDao.save(pkg);
				return Response.ok("打包完成").header("EntityClass", "R_package").build(); 
				
			} catch (Exception e) {
				return Response.serverError().entity(e.getMessage()).build(); 
			}
		}
		
		
	  //从包裹中删除快件
	  @Override
	  public Response deletePkg(String pkgId,String id) {
		  try {
			     ExpressSheet nes = expressSheetDao.get(id);
			     MoveExpressFromPackage(id,pkgId);
			     return Response.ok(nes).header("EntityClass", "ExpressSheet").build();
			}
			catch(Exception e)
			{
				return Response.serverError().entity(e.getMessage()).build(); 
			}
	  }

	   //更新包裹中的快件历史，拆包时用
	  void updateExpressHistory(int uid,String pkgId) {
		  List<ExpressSheet> list = new ArrayList<ExpressSheet>();
	      list=expressSheetDao.getListInPackage(pkgId);
	    
	        //更新快件历史
	        for (ExpressSheet c : list) {
	    	        ExpressHistory history = new ExpressHistory();
	    	        history.setExpress(c);
	    	        String nodeId = userInfoDao.get(uid).getDptID();
				    TransNode sourceNode = transNodeDao.get(nodeId);
				    history.setSourceNode(sourceNode);
				    history.setStatus(1);
				    history.setTime(getCurrentDate());
				    expressHistoryDao.save(history);
	        }
	  }
	  
	  //拆包过程
	  @Override
	  public Response disPackPackage(int uid,String sourcePkgId) {
		  try {
			    TransPackage pkg1 = null;
			    pkg1 = transPackageDao.get(sourcePkgId);
			    if(pkg1 == null) {
			    	  return Response.ok("包裹信息不存在！！！").header("EntityClass", "F_TransPackage").build();
			    }
			    
				UserInfo userInfo = userInfoDao.getUserInfoLoad(uid);  //根据用户UID得到用户的详细信息
			    String targetPkgId = userInfo.getReceivePackageID();   //得到该分拣员的揽收包裹
			      
			    //先判断包裹类型，是一个转运车、  还是一个揽收货篮 、 还是一个普通的包裹
                TwoPackage twoPackage = twoPackageDao.getBySma(sourcePkgId);
                
                if(twoPackage==null) {    //可能是转运车，也可能是揽收货篮
                	 List<TwoPackage> list1 = twoPackageDao.getByBig(sourcePkgId);
                	 if(list1 == null) {   
                		//将揽收货篮里的包裹中的快件历史都更新
                		System.out.println("是揽收货篮！！！");
                		updateExpressHistory(uid,sourcePkgId);
                		
                		List<ExpressSheet> expresslist = expressSheetDao.getListInPackage(sourcePkgId);
                		for (ExpressSheet c : expresslist) {
    			    	    String expressSheetId=c.getID();
    			    	    MoveExpressBetweenPackage(expressSheetId,sourcePkgId,targetPkgId);
    			        }
                	}
                	else {    
                		System.out.println("是转运车！！！");
                		 for(TwoPackage pkg:list1) {
                			  String pkgId = pkg.getSmallPkg().getID();
     			              MovePackageBetweenPackages(pkgId, sourcePkgId, targetPkgId);
     			              
     			              //更新转运车里所有包裹里的快件
     			              updateExpressHistory(uid,pkgId);
                		 }
                	}
               }
               else {
            	   System.out.println("是普通小包裹！！！");
            	   List<ExpressSheet> expresslist = expressSheetDao.getListInPackage(sourcePkgId);
           		   for (ExpressSheet c : expresslist) {
			    	    String expressSheetId=c.getID();
			    	    MoveExpressBetweenPackage(expressSheetId,sourcePkgId,targetPkgId);
			    	    
			    	    //更新快件历史信息
			    	    ExpressHistory history = expressHistoryDao.getByExpress(c.getID());
			    	    if(history!=null) {
						   history.setStatus(2);
						   history.setTime(getCurrentDate());
						   expressHistoryDao.save(history);
			    	    }
			       }
           		  //将该包裹设置为废弃包裹 
           		  twoPackage.setSmallContent(TwoPackage.STATUS.STATUS_EMPTY);
		          twoPackageDao.save(twoPackage);
               }
			    
			   return Response.ok("成功拆包").header("EntityClass", "S_TransPackage").build();
			}
			catch(Exception e)
			{
				return Response.serverError().entity(e.getMessage()).build(); 
			}
	  }
	  
	  //查询某一网点内的所有包裹
		@Override
		public List<TransPackage> getPkgInNode(String NodeId) {
			String pkgId = nodePackagePairDao.get(NodeId).getPkg().getID();  //得到与该网点对应的包裹号
			System.out.println("pkgid==="+pkgId);
			
			List<TransPackage> list = new ArrayList<TransPackage>();
			list = transPackageDao.getListInNode(pkgId);
			
			return list;
		}
		
	 //查询该网点中某寄件地的包裹
	   @Override
	   public  List<TransPackage> getPkgByTarget(String NodeId,String TargetNode) {
		    String pkgId = nodePackagePairDao.get(NodeId).getPkg().getID();  //得到与该网点对应的包裹号
			List<TransPackage> list = new ArrayList<TransPackage>();
			list = transPackageDao.getListInNode(pkgId);
			
			List<TransPackage> list2 = new ArrayList<TransPackage>();
		    for(TransPackage pkg:list) {
		    	String tt=pkg.getTargetNode();
		    	if(tt.equals(TargetNode)) {
		    		System.out.println("***targetNode"+TargetNode);
		    		list2.add(pkg);
		    	}
		    }
		    System.out.println("*****list2=="+list2);
		    return list2;
	   }
	   
	   
	 //包裹归档
	  @Override
	  public List<TransPackage> filePackage(String NodeId) {
		  String pkgId = nodePackagePairDao.get(NodeId).getPkg().getID();  //得到与该网点对应的包裹号
		  List<TransPackage> list = new ArrayList<TransPackage>();
		  list = transPackageDao.getEmptyPkgListInNode(pkgId);
		  return list;
	  }
	  
	  
	//删除某一网点里的废弃包裹
	  @Override
	  public Response removePackage(String NodeId) {
		  try {
		  String pkgId = nodePackagePairDao.get(NodeId).getPkg().getID();  //得到与该网点对应的包裹号
		  List<TransPackage> list = new ArrayList<TransPackage>();
		  list = transPackageDao.getEmptyPkgListInNode(pkgId);
		  System.out.println("1111");
		  for(TransPackage pkg:list) {
			 // String id = pkg.getID();
			  /*
			  //删除content里的内容
			  List<TransPackageContent> list1 = transPackageContentDao.getPkg(id);
			  for(TransPackageContent con:list1) {
				  System.out.println("22222");
				  transPackageContentDao.remove(con);
				  System.out.println("3333");
			  }
			  
			  //删除twoPackage里的内容
			  TwoPackage two = twoPackageDao.getBySma(id);
			  twoPackageDao.remove(two);
			  
			  //删除PackageRoute里的内容
			  List<PackageRoute> list2 = packageRouteDao.getByPkg(id);
			  for(PackageRoute route:list2) {
				  packageRouteDao.remove(route);
			  }
			  */
			  //删除TransPackage里的内容
			  transPackageDao.remove(pkg); 
			}
		    TransNode node = transNodeDao.get(NodeId);
		    return Response.ok(node).header("EntityClass", "ExpressSheet").build();
		  }
		 catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
		  
	   }
	  
	  
 
	//用户操作方法=======================================================================		
	@Override
	public Response dogetUserInfoLoad(int uid) {
		try{
			UserInfo UserInfo = new UserInfo();
			UserInfo = userInfoDao.getUserInfoLoad(uid);
			//userInfoDao.save(UserInfo);
			return Response.ok(UserInfo).header("EntityClass", "UserInfo").build();
		}
			catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}
	
	
	@Override
	public Response doLogOut(int UID) {
			try{
				UserInfo D_UserInfo = new UserInfo();
				D_UserInfo = userInfoDao.LogOut(UID);
				D_UserInfo.setStatus(0);
				return Response.ok(D_UserInfo).header("EntityClass", "D_UserInfo").build();
			}
				catch(Exception e)
			{
				return Response.serverError().entity(e.getMessage()).build(); 
			}
		}
	
	
	    //新用户的注册
	@Override
	public Response doRegister(String name, String pwd, String telcode, String dptid, int urull,int status,String receivepackageID,String delivepackageID,String transpackageID) {
		try{
			UserInfo UserInfo = new UserInfo();	
			int flag = userInfoDao.checkRegister(name); 
			if(flag == 1 )  //表示用户名不存在，可以使用
			{   
				if(urull==1 || urull ==2) {
			    UserInfo.setName(name);
			    UserInfo.setPWD(pwd);
			    UserInfo.setTelCode(telcode);
			    UserInfo.setDptID(dptid);
			    UserInfo.setURull(urull);
			    UserInfo.setStatus(status);
			    UserInfo.setReceivePackageID(receivepackageID);
			    /*UserInfo.setDelivePackageID(delivepackageID);
			    UserInfo.setTransPackageID(transpackageID);*/
			    userInfoDao.save(UserInfo);
			}
			if(urull == 3)
			{
				    UserInfo.setName(name);
				    UserInfo.setPWD(pwd);
				    UserInfo.setTelCode(telcode);
				    UserInfo.setDptID(dptid);
				    UserInfo.setURull(urull);
				    UserInfo.setStatus(status);
				    UserInfo.setTransPackageID(transpackageID);
				    userInfoDao.save(UserInfo);
			}
			if(urull == 4) 
			{
				UserInfo.setName(name);
			    UserInfo.setPWD(pwd);
			    UserInfo.setTelCode(telcode);
			    UserInfo.setDptID(dptid);
			    UserInfo.setURull(urull);
			    UserInfo.setStatus(status);
			    UserInfo.setDelivePackageID(delivepackageID);
			    userInfoDao.save(UserInfo);
			}
			
			
			}
			return Response.ok(UserInfo).header("EntityClass", "R_UserInfo").build();
		 }
		catch(Exception e)  //用户名已经存在，不能使用
		{
			  return Response.serverError().entity(e.getMessage()).build(); 
		}
	}


		
	//用户登陆操作方法
	@Override
	public Response doLogin(String name, String pwd) {
		try{
			UserInfo userInfo = new UserInfo();
			int flag = userInfoDao.checkLogin(name, pwd); 
			if(flag == 0) 
			{  										//登陆成功
				System.out.println("999");
				userInfo = userInfoDao.get1(name); 
				System.out.println("000");
				//得到用户DptID的详细信息
				userInfo.setStatus(1);
				userInfoDao.save(userInfo);
				return Response.ok(userInfo).header("EntityClass", "R_UserInfo").build();
			}
				else return Response.ok(userInfo).header("EntityClass", "R_UserInfo").build();
			
		}
		catch(Exception e)
		{
			    return Response.serverError().entity(e.getMessage()).build();
	    }
	}

		
/*		//管理员登陆操作方法
		@Override
		public Response doAdminLogin(int aid, String pwd) {
			try{
				int flag = userInfoDao.checkLogin(aid, pwd); 
				if(flag == 0) 
				{  										//登陆成功
					UserInfo UserInfo = new UserInfo();
					UserInfo = userInfoDao.get(aid);  //得到用户DptID的详细信息
					UserInfo.setStatus(1);
					userInfoDao.save(UserInfo);
					return Response.ok(UserInfo).header("EntityClass", "R_UserInfo").build();
				}
					else return Response.ok("用户名或密码不存在").header("EntityClass", "R_UserInfo").build();
				
			}
			catch(Exception e)
			{
				    return Response.serverError().entity(e.getMessage()).build();
		    }
		}
		*/
		
		
		
		
		

	/*更新某一用户的信息*/
	@Override
	public Response updateUserInfo(int uid,String name, String pwd, String telcode, String dptid, int urull,int status,String receivepackageID,String delivepackageID,String transpackageID) {
    		UserInfo UserInfo = new UserInfo();
    		UserInfo = userInfoDao.getUserInfoLoad(uid);
    		int flag = userInfoDao.checkRegister(name); 
			if(flag == 0 )  //表示用户名存在，可以使用
			{  
				if(urull==1 || urull ==2) 
				{
			    UserInfo.setName(name);
			    UserInfo.setPWD(pwd);
			    UserInfo.setTelCode(telcode);
			    UserInfo.setDptID(dptid);
			    UserInfo.setURull(urull);
			    UserInfo.setStatus(status);
			    UserInfo.setReceivePackageID(receivepackageID);
			    UserInfo.setDelivePackageID(null);
			    UserInfo.setTransPackageID(null);
			    userInfoDao.save(UserInfo);
			   }
			if(urull == 3)
			{
				    UserInfo.setName(name);
				    UserInfo.setPWD(pwd);
				    UserInfo.setTelCode(telcode);
				    UserInfo.setDptID(dptid);
				    UserInfo.setURull(urull);
				    UserInfo.setStatus(status);
				    UserInfo.setTransPackageID(transpackageID);
				    UserInfo.setReceivePackageID(null);
				    UserInfo.setDelivePackageID(null);
				  
				    userInfoDao.save(UserInfo);
			}
			if(urull == 4) 
			{
				UserInfo.setName(name);
			    UserInfo.setPWD(pwd);
			    UserInfo.setTelCode(telcode);
			    UserInfo.setDptID(dptid);
			    UserInfo.setURull(urull);
			    UserInfo.setStatus(status);
			    UserInfo.setDelivePackageID(delivepackageID);
			    UserInfo.setTransPackageID(null);
			    UserInfo.setReceivePackageID(null);
		
			    userInfoDao.save(UserInfo);
			}
			
			
			}
			return Response.ok(UserInfo).header("EntityClass", "R_UserInfo").build();
		 }

		
	@Override
	public Response doadminLogin(String adminName, String PWD) {
		try{
			AdminInfo adminInfo = new AdminInfo();
			int flag = adminInfoDao.checkLogin(adminName, PWD); 
			if(flag == 0) 
			{  										
			    AdminInfo adminInfo1 = adminInfoDao.getAdminInfoLoad(adminName); 
			    System.out.println("111");
				adminInfoDao.save(adminInfo1);
				return Response.ok(adminInfo1).header("EntityClass", "X_AdminInfo").build();
			}
				else return Response.ok(adminInfo).header("EntityClass", "R_AdminInfo").build();
			
		}
		catch(Exception e)
		{
			    return Response.serverError().entity(e.getMessage()).build();
	    }
	}

		
		//删除某一id的顾客
		@Override
		public Response deleteUserInfo(int uid) {
			try{
				UserInfo UserInfo = new UserInfo();
				int flag = userInfoDao.checkDelete(uid); 
				if(flag == 1 )
			   {  //用户存在，可以删除
					userInfoDao.removeByUId(uid);
			   }
				return Response.ok(UserInfo).header("EntityClass", "D_UserInfo").build();  //删除成功返回一个空的对象
			}
			
			catch(Exception e)  //用户不存在
			{
				  return Response.serverError().entity(e.getMessage()).build(); 
			}
			
		}

		//得到所用用户的详细信息
		@Override
		public List<UserInfo> dogetAllUserInfoLoad() {
				/*UserInfo UserInfo = new UserInfo();*/
				List<UserInfo> UserInfo = userInfoDao.getAllUserInfoLoad();
				/*userInfoDao.saveList(UserInfo);*/
				return UserInfo;
		}
		
		
		
		  
		@Override
		public Response doInsertComment(String username, String content, String dptName,String tel) {     
			 		System.out.println(username+content+dptName+tel);
					CommentInfo CommentInfo = new CommentInfo();
			 		System.out.println("111");
				    CommentInfo.setusername(username);
				    System.out.println("222");
				    CommentInfo.setcontent(content);
				    System.out.println(content);
				    CommentInfo.setdptName(dptName);
				    CommentInfo.settel(tel);
				    commentInfoDao.save(CommentInfo);
				    System.out.println("333");
				    return Response.ok(CommentInfo).header("EntityClass", "H_CommentInfo").build();
			 }
		
	    
				
				//删除某一评论
				@Override
				public Response deleteComment(int id) {
					try{
						CommentInfo CommentInfo = new CommentInfo();
					//	int flag = userInfoDao.checkDelete(uid); 
						/*if(flag == 1 )
					   { */ //用户存在，可以删除
						commentInfoDao.removeById(id);
					  //}
						return Response.ok(CommentInfo).header("EntityClass", "D_CommentInfo").build();  //删除成功返回一个空的对象
					}
					
					catch(Exception e)  //用户不存在
					{
						  return Response.serverError().entity(e.getMessage()).build(); 
					}
					
				}
				
				
				

				//得到所用评论的详细信息
				@Override
				public List<CommentInfo> dogetAllCommentInfoLoad() {
						/*UserInfo UserInfo = new UserInfo();*/
						List<CommentInfo> commentInfo = commentInfoDao.AllCommentInfoLoad();
						/*userInfoDao.saveList(UserInfo);*/
						return commentInfo;
				}
				
				
				
				
				
				
				
				
				
				
				
				
	    
	//定位操作=======================================================================
    
	    //保存用户的位置信息
		@Override
		public Response saveUserPosition(int uid,float x,float y) {
			
			UserInfo userinfo=userInfoDao.get(uid);
			try {
			   UserPosition userPosition=new UserPosition();
			   userPosition.setUser(userinfo);
			   userPosition.setX(x);
			   userPosition.setY(y);
			   userPosition.setTm(getCurrentDate());
			   System.out.println("222");
			   userPositionDao.save(userPosition);
			   updatePackageRoute(userPosition);
			   System.out.println("333");
			   return Response.ok(userPosition).header("EntityClass", "UserPosition").build();
			}
			catch(Exception e)
			{
				return Response.serverError().entity(e.getMessage()).build(); 
			}
		}
	
		//更新包裹的位置信息
		public void updatePackageRoute(UserPosition userPosition) {
			int role = userPosition.getUser().getURull();
			String pkg=null;
			float x,y;
			x=userPosition.getX();
			y=userPosition.getY();
			UserInfo userinfo=userPosition.getUser();
			if(role==1)  pkg = userinfo.getReceivePackageID();
			else if(role==2) pkg=userinfo.getReceivePackageID();
			else if(role==3) pkg=userinfo.getTransPackageID();
			else if(role==4) pkg=userinfo.getDelivePackageID();
			
			savePackageroute(pkg,x,y);
			List<TransPackage> list = transPackageDao.getSmallInBig(pkg);
			if(list!=null) {
			        for(TransPackage pack:list) {
				        savePackageroute(pack.getID(),x,y);
			        }
			}       
			
		}
		
		public void savePackageroute(String packageid,float x,float y) {
			TransPackage transpackage=transPackageDao.get(packageid);
			PackageRoute route=new PackageRoute();
			route.setPkg(transpackage);
			route.setX(x);
			route.setY(y);
			route.setTm(getCurrentDate());
			packageRouteDao.save(route);
			updateExpressRoute(route);
		}
	   
		//更新快件的位置信息
		public void updateExpressRoute(PackageRoute route) {
			float x,y;
			String expressId;
			x=route.getX();
			y=route.getY();
			TransPackage transpackage=route.getPkg();
			List<ExpressSheet> list=getExpressListInPackage(transpackage.getID());
			if(list!=null) {
			    for(ExpressSheet nes : list) {
				    expressId=nes.getID();
				    saveExpressRoute(expressId,x,y);
			    }
			}
		}
		
		public void saveExpressRoute(String expressId,float x,float y) {
			ExpressSheet nes=expressSheetDao.get(expressId);
			ExpressRoute route=new ExpressRoute();
			route.setNes(nes);
			route.setX(x);
			route.setY(y);
			route.setTm(getCurrentDate());
			expressRouteDao.save(route);
		}
		
		//根据快件的id，得到一个快件位置的列表
		@Override
		public List<ExpressRoute> getExpressRouteList(String expressId) {
	
			ExpressSheet nes=expressSheetDao.get(expressId);
			List<ExpressRoute> list = new ArrayList<ExpressRoute>();
			list = expressRouteDao.findBy("nes", nes, "tm", true);
			return list;
		}
		
		
   //转运操作=======================================================================
		//将某一包裹移进某一货篮包裹（分拣中心或转运车）
		public boolean MoveSmallIntoBig(String smallId, String bigId) {
			System.out.println(bigId);
			TransPackage smallPkg = transPackageDao.get(smallId);
			System.out.println(smallPkg);
			TransPackage bigPkg = transPackageDao.get(bigId);
			System.out.println(bigPkg);
			//大包裹不是分拣中心或转运车
			if(bigPkg.getStatus() != 3 && bigPkg.getStatus() != 4){	
				return false;
			}
            System.out.println(bigId);
			//将小包裹加到大包裹里
			TwoPackage twoPackage = new TwoPackage();
			twoPackage.setBigPkg(bigPkg);
			twoPackage.setSmallPkg(smallPkg);
			twoPackage.setSmallContent(TwoPackage.STATUS.STATUS_NOEMPTY);
			twoPackageDao.save(twoPackage);
			
			return true;
		}

		//将小包裹从大包裹中移出
		public boolean MoveSmallFromBig(String smallId, String bigId) {
			TransPackage smallPkg = transPackageDao.get(smallId);
			TransPackage bigPkg = transPackageDao.get(bigId);
			//大包裹不是分拣中心或转运车
			if(bigPkg.getStatus() != 3 && bigPkg.getStatus() != 4){	
				return false;
			}

			TwoPackage twoPackage = twoPackageDao.get(smallId , bigId);
			
			//取消两个包裹的关联
			twoPackageDao.remove(twoPackage);
			
			return true;
		}
		
		
	     //将包裹在两个包裹间转移
		public boolean MovePackageBetweenPackages(String pkgId, String sourcePkgId, String targetPkgId) {
			//需要加入事务机制
			MoveSmallFromBig(pkgId, sourcePkgId);
			MoveSmallIntoBig(pkgId,targetPkgId);
			return true;
		}
		
		//转运，即从转运车到分拣中心，或从分拣中心到转运车
		@Override
		public Response transport(int uid,String pkgId) {
			  try {
					UserInfo userInfo = userInfoDao.getUserInfoLoad(uid);  //根据用户UID得到用户的详细信息
				    String targetPkgId = userInfo.getTransPackageID();   //得到该司机的转运包裹
				    
				    TwoPackage twoPackage = twoPackageDao.getBySma(pkgId);
				    System.out.println(twoPackage);
				    
				    List<ExpressSheet> list = new ArrayList<ExpressSheet>();
			        list=expressSheetDao.getListInPackage(pkgId);
			    
				    if(twoPackage == null) {   //是派送车
				    	 System.out.println("是派送车！！！");
				    	 //更新快件历史
				        for (ExpressSheet c : list) {
				    	    ExpressHistory history = expressHistoryDao.getByExpress(c.getID());
				    	    System.out.println("查到的数据："+history);
				    	    
				    	    if(history.getStatus()==2) {
				    	    	  history.setTel(userInfoDao.get(uid).getTelCode());
				    	    	  history.setUsername(userInfoDao.get(uid).getName());
				    	    	  expressHistoryDao.save(history);
				    	    }
				        }
				    }
				    else {  //是普通小包裹
				    	    System.out.println("是普通小包裹！！！");
				    	
						    String sourcePkgId=twoPackage.getBigPkg().getID();
						    System.out.println("-----22222-----");
						    
						    MovePackageBetweenPackages(pkgId,sourcePkgId,targetPkgId);
						    System.out.println("-----33333-----");
				    	
				    	for (ExpressSheet c : list) {
				    		System.out.println("-----44444-----");
				    	    ExpressHistory history = expressHistoryDao.getByExpress(c.getID());
				    	    System.out.println("查到的数据："+history);
				    	    System.out.println("-----555555-----");
				    	    
				    	    if(history.getStatus()==1) {
				    	    	System.out.println("-----666666-----");
				    	          String nodeId = userInfoDao.get(uid).getDptID();
				    	          System.out.println("-----777777-----");
							      TransNode targetNode = transNodeDao.get(nodeId);
							      System.out.println("-----888888-----");
							      history.setTargetNode(targetNode);
							      history.setTime(getCurrentDate());
							      expressHistoryDao.save(history);
				    	    }
				        }
				    }
				    
				  return Response.ok("成功移交").header("EntityClass", "T_TransPackage").build();
				}
				catch(Exception e)
				{
					return Response.serverError().entity(e.getMessage()).build(); 
				}
		  }
		 
	 
}
