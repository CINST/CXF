/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package ts.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@Entity           //声明这是一个持久化类
@org.hibernate.annotations.Proxy(lazy=false) //指的是不使用懒加载,如果对某个对象使用懒加载，那么在用query查询的时候并不是马上去访问数据库，而是在 用到该对象的属性时再去访问数据库，这样就减少了不必用的数据库访问   
@Table(name="UserInfo")   //设置当前持久化类所映射的数据库表
@XmlRootElement(name="UserInfo")  //转换成xml时，该类在xml文档中会以最外层或根节点形式出现
public class UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6899152987896840262L;

	public UserInfo() {
	}
	
	/*@Column:将持久化类的属性与数据库表中的字段进行映射，name属性值为映射的字段名，length属性值为字段的长度，
	 * unique属性表示该列上设置唯一的约束，nullable属性设置该列的值是否可以为空，
	 * precision实现设置该字段的精度，scale属性设置该字段的小数位数
	 */
	/*@GeneratedValue:设置当前主键的生成策略*/
	/*@Id:设置当前持久化类的标示符属性，即该字段为主键*/
	
	@Column(name="UID", nullable=false)	
	@Id	
	@GeneratedValue(generator="MODEL_USERINFO_UID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="MODEL_USERINFO_UID_GENERATOR", strategy="native")	
	private int UID;
	
	@Column(name="PWD", nullable=true, length=8)	
	private String PWD;
	
	@Column(name="Name", nullable=true, length=16)	
	private String Name;
	
	@Column(name="URull", nullable=true, length=4)	
	private Integer URull;
	
	@Column(name="TelCode", nullable=true, length=24)	
	private String telCode;
	
	@Column(name="Status", nullable=true, length=4)	
	private Integer status;
	
	@Column(name="DptID", nullable=true, length=16)	
	private String dptID;
	
	@Column(name="ReceivePackageID", nullable=true, length=24)	
	private String receivePackageID; //接收的包裹id

	@Column(name="DelivePackageID", nullable=true, length=24)	
	private String delivePackageID;   //派送的包裹id
	
	@Column(name="TransPackageID", nullable=true, length=24)	
	private String transPackageID;     //转运的包裹id
	
	@OneToMany(mappedBy="userU", targetEntity=UsersPackage.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<UsersPackage> usersPackage = new java.util.HashSet<UsersPackage>();
	
	public void setUID(int value) {
		this.UID = value;
	}
	
	public int getUID() {
		return UID;
	}
	
	public int getORMID() {
		return getUID();
	}
	
	public void setPWD(String value) {
		this.PWD = value;
	}
	
	public String getPWD() {
		return PWD;
	}
	
	public void setName(String value) {
		this.Name = value;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setURull(Integer value) {
		this.URull = value;
	}
	
	public Integer getURull() {
		return URull;
	}
	
	public void setTelCode(String value) {
		this.telCode = value;
	}
	
	public String getTelCode() {
		return telCode;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setDptID(String value) {
		this.dptID = value;
	}
	
	public String getDptID() {
		return dptID;
	}
	
	public void setReceivePackageID(String value) {
		this.receivePackageID = value;
	}
	
	public String getReceivePackageID() {
		return receivePackageID;
	}
	
	public void setDelivePackageID(String value) {
		this.delivePackageID = value;
	}
	
	public String getDelivePackageID() {
		return delivePackageID;
	}
	
	public void setTransPackageID(String value) {
		this.transPackageID = value;
	}
	
	public String getTransPackageID() {
		return transPackageID;
	}
	
	public void setUsersPackage(java.util.Set<UsersPackage> value) {
		this.usersPackage = value;
	}
	
	@XmlTransient
	public java.util.Set<UsersPackage> getUsersPackage() {
		return usersPackage;
	}
	
	
	@Transient	
	private String dptString;
	public void setdptString(String value) {
		this.dptString = value;
	}
	
	public String getdptString() {
		return dptString;
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
			sb.append("UserInfo[ ");
			sb.append("UID=").append(getUID()).append(" ");
			sb.append("PWD=").append(getPWD()).append(" ");
			sb.append("Name=").append(getName()).append(" ");
			sb.append("URull=").append(getURull()).append(" ");
			sb.append("TelCode=").append(getTelCode()).append(" ");
			sb.append("Status=").append(getStatus()).append(" ");
			sb.append("DptID=").append(getDptID()).append(" ");
			sb.append("dptString=").append(getdptString()).append(" ");
			sb.append("ReceivePackageID=").append(getReceivePackageID()).append(" ");
			sb.append("DelivePackageID=").append(getDelivePackageID()).append(" ");
			sb.append("TransPackageID=").append(getTransPackageID()).append(" ");
			sb.append("UsersPackage.size=").append(getUsersPackage().size()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

}
