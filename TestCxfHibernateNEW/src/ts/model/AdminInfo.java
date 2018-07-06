package ts.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
/*import javax.xml.bind.annotation.XmlTransient;*/
@Entity           //声明这是一个持久化类
@org.hibernate.annotations.Proxy(lazy=false) //指的是不使用懒加载,如果对某个对象使用懒加载，那么在用query查询的时候并不是马上去访问数据库，而是在 用到该对象的属性时再去访问数据库，这样就减少了不必用的数据库访问   
@Table(name="AdminInfo")   //设置当前持久化类所映射的数据库表
@XmlRootElement(name="AdminInfo")  //转换成xml时，该类在xml文档中会以最外层或根节点形式出现
public class AdminInfo implements Serializable {
	private static final long serialVersionUID = 6899152987896840262L;

	public AdminInfo() {
		
	}
	@Column(name="UID", nullable=false)	
	@Id	
	@GeneratedValue(generator="MODEL_ADMININFO_UID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="MODEL_ADMININFO_UID_GENERATOR", strategy="native")	
	private int UID;
	
	@Column(name="PWD", nullable=true, length=8)	
	private String PWD;

	@Column(name="adminName", nullable=true, length=8)	
	private String adminName;


	
	public void setUID(int value) {
		this.UID = value;
	}
	
	public int getUID() {
		return UID;
	}
	
	/*public int getORMID() {
		return getUID();
	}*/
	
	public void setPWD(String value) {
		this.PWD = value;
	}
	
	public String getPWD() {
		return PWD;
	}
	
	public void setadminName(String value) {
		this.adminName = value;
	}
	
	public String getadminName() {
		return adminName;
	}
	
	

	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getUID());
		}
		else {
			StringBuffer sb = new StringBuffer();
			sb.append("AdminInfo[ ");
			sb.append("UID=").append(getUID()).append(" ");
			sb.append("PWD=").append(getPWD()).append(" ");
			sb.append("adminName=").append(getadminName()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
}
