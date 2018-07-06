package ts.daoImpl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import ts.daoBase.BaseDao;
import ts.model.Region;

//区域的持久化操作类
public class RegionDao  extends BaseDao<Region, String>{
	public RegionDao(){
		super(Region.class);
	}
	/**
     * 通过ID(regioncode)获取带有全名的行政区对象
     */
	public Region getFullNameRegionByID(String ID){
		String id_tmp = "";
		Region rg_0 = null;
		Region ret_rg =  get(ID);

		if(ret_rg.getStage() == 3){
			id_tmp = ID.substring(0, 4)+"00"; //截取字符串ID从第0个字符到第4个字符（无第4个字符）
			rg_0 = get(id_tmp);
			ret_rg.setCty(rg_0.getCty());

			id_tmp = ID.substring(0, 2)+"0000";
			rg_0 = get(id_tmp);
			ret_rg.setPrv(rg_0.getPrv());
		}
		else if(ret_rg.getStage() == 2){         //是一个城市码，它的Pro属性为空
			id_tmp = ID.substring(0, 2)+"0000";  //得到一个省份码
			rg_0 = get(id_tmp);                  //得到该省份码对应的一个实例region
			ret_rg.setPrv(rg_0.getPrv());        //为城市码对应的实例region的Pro属性赋值
		}
		return ret_rg;
	}
	
	/**
     * 通过ID(regioncode)获取行政区全名的字符串
     */
	public String getRegionNameByID(String ID) {
		Region rg = getFullNameRegionByID(ID);

		StringBuffer sbname = new StringBuffer();
		if(rg.getStage()>0)
			sbname.append(rg.getPrv());
		if(rg.getStage()>1)
			sbname.append(rg.getCty());
		if(rg.getStage()>2)
			sbname.append(rg.getTwn());
		return sbname.toString();
	}

	/**
     * 获取省，即region表中stage为1的记录
     */
	public List<Region> getProvinceList() {
		List<Region> listrg = findBy("stage", 1, "regionCode", true);
		return listrg;
	}

	/**
     * 获取市，即region表中stage为2的记录
     */
	public List<Region> getCityList(String ID) {
		String sid = ID.substring(0, 2).concat("%"); //得到省的编码，并加上%，从而查到该省下的城市
		List<Region> listrg = (List<Region>) findBy("regionCode", true,Restrictions.like("regionCode", sid),Restrictions.eq("stage", 2));
		return listrg;
	}
	
	/**
     * 获取县，即region表中stage为3的记录
     */
	public List<Region> getTownList(String ID) {
		String sid = ID.substring(0, 4).concat("%");
		List<Region> listrg = (List<Region>) findBy("regionCode", true,Restrictions.like("regionCode", sid),Restrictions.eq("stage", 3));
		return listrg;
	}
	

/**
     * 通过dptId获取带有全名的行政区对象
     */
	public String getFullNameRegionBydptID(String dptId){
		dptId = dptId.substring(0,6);
		String fullName= null;
		fullName = getRegionNameByID(dptId)+"网点";
		return fullName;	
	}
}
