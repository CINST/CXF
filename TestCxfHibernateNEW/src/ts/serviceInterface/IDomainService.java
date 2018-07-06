package ts.serviceInterface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ts.model.CommentInfo;
import ts.model.ExpressHistory;
import ts.model.ExpressRoute;
import ts.model.ExpressSheet;
import ts.model.TransPackage;
import ts.model.UserInfo;

@Path("/Domain")	//ҵ�����
public interface IDomainService {
	//快件操作访问接口=======================================================================
	/*设置媒体类型xml格式和json格式，
     * 如果想让rest返回xml,需要在rest的url后边添加 ?_type=xml,默认为xml
                     如果想让rest返回json.需要在rest的url后边添加?_type=json*/
	@GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getExpressList/{Property}/{Restrictions}/{Value}") //指定请求路径
	public List<ExpressSheet> getExpressList(@PathParam("Property")String property, @PathParam("Restrictions")String restrictions, @PathParam("Value")String value);

	@GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getExpressByTel/{Property1}/{Value1}/{Property2}/{Value2}") //指定请求路径
	public List<ExpressSheet> getExpressByTel(@PathParam("Property1")String property1,@PathParam("Value1")String value1,@PathParam("Property2")String property2,@PathParam("Value2")String value2);

	
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getExpressListInPackage/PackageId/{PackageId}") 
	public List<ExpressSheet> getExpressListInPackage(@PathParam("PackageId")String packageId);

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getExpressSheet/{id}") 
	public Response getExpressSheet(@PathParam("id")String id);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/newExpressSheet/id/{id}/uid/{uid}") 
	public Response newExpressSheet(@PathParam("id")String id, @PathParam("uid")int uid);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/saveExpressSheet") 
	public Response saveExpressSheet(ExpressSheet obj);
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/receiveExpressSheetId/id/{id}/uid/{uid}") 
	public Response ReceiveExpressSheetId(@PathParam("id")String id, @PathParam("uid")int uid);
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/receiveFinished/uid/{uid}") 
	public Response receiveFinished(@PathParam("uid")int uid);
    
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/dispatchExpressSheetId/id/{id}/uid/{uid}") 
	public Response DispatchExpressSheet(@PathParam("id")String id, @PathParam("uid")int uid);
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/deliveryExpressSheetId/id/{id}/uid/{uid}") 
	public Response DeliveryExpressSheetId(@PathParam("id")String id, @PathParam("uid")int uid);
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getExpressHistory/{expressId}") 
	public List<ExpressHistory> getExpressHistory(@PathParam("expressId")String expressId);
    

  //包裹操作访问接口=======================================================================
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getTransPackageList/{Property}/{Restrictions}/{Value}") 
	public List<TransPackage> getTransPackageList(@PathParam("Property")String property, @PathParam("Restrictions")String restrictions, @PathParam("Value")String value);

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getTransPackage/{id}") 
	public Response getTransPackage(@PathParam("id")String id);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getPkgListInPkg/{pkgId}") 
	public List<TransPackage> getPkgListInPkg(@PathParam("pkgId")String pkgId);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/packageExist/{pkgId}") 
	public Response packageExist(@PathParam("pkgId")String pkgId);
    

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/newTransPackage/id/{id}/uid/{uid}") 
    public Response newTransPackage(@PathParam("id")String id, @PathParam("uid")int uid);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveTransPackage") 
	public Response saveTransPackage(TransPackage obj);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/packPackage/{id}/{targetPkgId}") 
	public Response packPackage(@PathParam("id") String id, @PathParam("targetPkgId") String targetPkgId);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/packFinished/{pkgId}") 
	public Response packFinished(@PathParam("pkgId") String pkgId);
    
    //从包裹中删除快件
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/deletePkg/{pkgId}/{id}") 
	public Response deletePkg(@PathParam("pkgId") String pkgId, @PathParam("id") String id);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/disPackPackage/{uid}/{sourcePkgId}") 
	public Response disPackPackage(@PathParam("uid") int uid,@PathParam("sourcePkgId") String sourcePkgId);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getPkgInNode/{NodeId}") 
	public List<TransPackage> getPkgInNode(@PathParam("NodeId")String NodeId);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getPkgByTarget/{NodeId}/{TargetNode}") 
	public List<TransPackage> getPkgByTarget(@PathParam("NodeId")String NodeId,@PathParam("TargetNode")String TargetNode);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/filePackage/{NodeId}") 
	public List<TransPackage>filePackage(@PathParam("NodeId")String NodeId);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/removePackage/{NodeId}") 
	public Response removePackage(@PathParam("NodeId")String NodeId);
    
    
  //登陆操作=========================================================================================
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/dogetUserInfoLoad/{uid}") 
	public Response dogetUserInfoLoad(@PathParam("uid") int uid);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doLogOut/{uid}") 
	public Response doLogOut(@PathParam("uid") int uid);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doRegister/{name}/{pwd}/{telcode}/{dptid}/{urull}/{status}/{receivepackageID}/{delivepackageID}/{transpackageID}") 
	public Response doRegister(@PathParam("name") String name, @PathParam("pwd") String pwd, @PathParam("telcode") String telcode, @PathParam("dptid") String dptid,@PathParam("urull") int urull,@PathParam("status") int status, @PathParam("receivepackageID") String receivepackageID,@PathParam("delivepackageID") String delivepackageID,@PathParam("transpackageID") String transpackageID);


    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doLogin/{name}/{pwd}") 
	public Response doLogin(@PathParam("name") String name, @PathParam("pwd") String pwd);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/updateUserInfo/{uid}/{name}/{pwd}/{telcode}/{dptid}/{urull}/{status}/{receivepackageID}/{delivepackageID}/{transpackageID}") 
    public Response updateUserInfo(@PathParam("uid") int uid,@PathParam("name") String name, @PathParam("pwd") String pwd, @PathParam("telcode") String telcode, @PathParam("dptid") String dptid,@PathParam("urull") int urull,@PathParam("status") int status, @PathParam("receivepackageID") String receivepackageID,@PathParam("delivepackageID") String delivepackageID,@PathParam("transpackageID") String transpackageID);

    //删除用户
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/deleteUserInfo/{uid}") 
    public Response deleteUserInfo(@PathParam("uid") int uid);

    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doadminLogin/{adminName}/{PWD}") 
	public Response doadminLogin(@PathParam("adminName") String adminName, @PathParam("PWD") String PWD);
	
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/dogetAllUserInfoLoad") 
	public List<UserInfo> dogetAllUserInfoLoad();
    
 //定位操作=========================================================================================
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/saveUserPosition/{uid}/{x}/{y}") 
	public Response saveUserPosition(@PathParam("uid")int uid, @PathParam("x")float x,@PathParam("y")float y);

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getExpressRouteList/{expressId}") 
	public List<ExpressRoute> getExpressRouteList(@PathParam("expressId") String expressId);

 //转运操作=========================================================================================
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/transport/{uid}/{pkgId}") 
	public Response transport(@PathParam("uid")int uid, @PathParam("pkgId") String pkgId);

    
  /*  @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doAdminLogin/{aid}/{pwd}") 
	public Response doAdminLogin(@PathParam("aid") int aid, @PathParam("pwd") String pwd);*/
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doInsertComment/{username}/{content}/{dptName}/{tel}") 
	public Response doInsertComment(@PathParam("username") String username, @PathParam("content") String content,@PathParam("dptName")  String dptName, @PathParam("tel") String tel);
     
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/deleteComment/{id}") 
	public Response deleteComment(@PathParam("id") int id);
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/dogetAllCommentInfoLoad")
    public List<CommentInfo> dogetAllCommentInfoLoad();

}
