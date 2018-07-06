package ts.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@Entity           //声明这是一个持久化类
@org.hibernate.annotations.Proxy(lazy=false) //指的是不使用懒加载,如果对某个对象使用懒加载，那么在用query查询的时候并不是马上去访问数据库，而是在 用到该对象的属性时再去访问数据库，这样就减少了不必用的数据库访问   
@Table(name="CommentInfo")   //设置当前持久化类所映射的数据库表
@XmlRootElement(name="CommentInfo")  //转换成xml时，该类在xml文档中会以最外层或根节点形式出现
public class CommentInfo implements Serializable {
	private static final long serialVersionUID = 6899152987896840262L;

	public CommentInfo() {
		
	}
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="MODEL_CommentInfo_id_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="MODEL_CommentInfo_id_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="username", nullable=true, length=8)	
	private String username;

	@Column(name="content", nullable=true, length=8)	
	private String content;

	@Column(name="dptName", nullable=true, length=8)	
	private String dptName;

	@Column(name="tel", nullable=true, length=8)	
	private String tel;
	
	public void setid(int value) {
		this.id = value;
	}
	
	public int getid() {
		return id;
	}
	
	public void setusername(String value) {
		this.username = value;
	}
	
	public String getusername() {
		return username;
	}
	
	public void setdptName(String value) {
		this.dptName = value;
	}
	
	public String getdptName() {
		return dptName;
	}
	
	
	public void setcontent(String value) {
		this.content = value;
	}
	
	public String getcontent() {
		return content;
	}
	
	
	public void settel(String value) {
		this.tel = value;
	}
	
	public String gettel() {
		return tel;
	}
	
	
	

	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getid());
		}
		else {
			StringBuffer sb = new StringBuffer();
			sb.append("CommentInfo[ ");
			sb.append("id=").append(getid()).append(" ");
			sb.append("username=").append(getusername()).append(" ");
			sb.append("content=").append(getcontent()).append(" ");
			sb.append("dptName=").append(getdptName()).append(" ");
			sb.append("tel=").append(gettel()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
}

