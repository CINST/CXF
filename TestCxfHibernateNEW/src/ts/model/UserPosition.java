package ts.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="UserPosition")
@XmlRootElement(name="UserPosition")
public class UserPosition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5186815468829777064L;

	

	public UserPosition() {
	}
	
	
	@Column(name="SN", nullable=false)	
	@Id	
	@GeneratedValue(generator="MODEL_TRANSPACKAGECONTENT_SN_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="MODEL_TRANSPACKAGECONTENT_SN_GENERATOR", strategy="native")	
	private int SN;
	
	@ManyToOne(targetEntity=UserInfo.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="UserID", referencedColumnName="UID", nullable=false) })	
	private UserInfo user;
	
	@Column(name="x", nullable=false)	
	private float x;
	
	@Column(name="y", nullable=false)	
	private float y;
	
	@Column(name="tm", nullable=false)	
	private Date tm;
	
	public void setSN(int value) {
		this.SN = value;
	}
	
	public int getSN() {
		return SN;
	}
	
	public int getORMID() {
		return getSN();
	}
	
	public void setX(float value) {
		this.x = value;
	}
	
	public float getX() {
		return x;
	}
	
	public void setY(float value) {
		this.y = value;
	}
	
	public float getY() {
		return y;
	}
	
	public void setTm(Date value) {
		this.tm = value;
	}
	
	public Date getTm() {
		return tm;
	}
	
	public void setUser(UserInfo value) {
		this.user = value;
	}
	
	@XmlTransient
	public UserInfo getUser() {
		return user;
	}
	
	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getSN());
		}
		else {
			StringBuffer sb = new StringBuffer();
			sb.append("UserPosition[ ");
			sb.append("SN=").append(getSN()).append(" ");
			if (getUser() != null)
				sb.append("User.Persist_ID=").append(getUser().toString(true)).append(" ");
			else
				sb.append("User=null ");
			sb.append("X=").append(getX()).append(" ");
			sb.append("Y=").append(getY()).append(" ");
			sb.append("Tm=").append(getTm()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}
