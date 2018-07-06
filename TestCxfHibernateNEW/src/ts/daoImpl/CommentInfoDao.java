

package ts.daoImpl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import ts.daoBase.BaseDao;
import ts.model.AdminInfo;
import ts.model.CommentInfo;
import ts.model.UserInfo;

public class CommentInfoDao extends BaseDao<CommentInfo, Integer> {
	

	public CommentInfoDao(){
		super(CommentInfo.class);
		
	}

	public List<CommentInfo> AllCommentInfoLoad() {
		List<CommentInfo> commentInfo =  findBy("id", true);
		 if(commentInfo.isEmpty()) {
             return null;
      }
      else{
	          return commentInfo;  //返回所用用户的详细列表
      }
	}
}


/*public List<UserInfo> getAllUserInfoLoad() {
	List<UserInfo> userInfo =  findBy("UID", true);
        if(userInfo.isEmpty()) {
               return null;
        }
        else{
	            return userInfo;  //返回所用用户的详细列表
        }

}*/