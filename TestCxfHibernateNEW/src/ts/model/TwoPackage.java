package ts.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="TwoPackage")
@XmlRootElement(name="TwoPackage")
public class TwoPackage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8049317584167634805L;

	

	public TwoPackage() {
	}
	
	
	@Column(name="SN", nullable=false)	
	@Id	
	@GeneratedValue(generator="MODEL_USERSPACKAGE_SN_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="MODEL_USERSPACKAGE_SN_GENERATOR", strategy="native")	
	private int SN;
	
	@ManyToOne(targetEntity=TransPackage.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="bigID", referencedColumnName="ID", nullable=false) })	
	private TransPackage bigPkg;
	
	@ManyToOne(targetEntity=TransPackage.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="smaID", referencedColumnName="ID", nullable=false) })	
	private TransPackage smallPkg;
	
	@Column(name="smallContent", nullable=true, length=4)	
	private Integer smallContent;
	
	public void setSN(int value) {
		this.SN = value;
	}
	
	public int getSN() {
		return SN;
	}
	
	public int getORMID() {
		return getSN();
	}
	
	
	public void setBigPkg(TransPackage value) {
		this.bigPkg = value;
	}
	
	public TransPackage getBigPkg() {
		return bigPkg;
	}
	
	public void setSmallPkg(TransPackage value) {
		this.smallPkg = value;
	}
	
	public TransPackage getSmallPkg() {
		return smallPkg;
	}
	
	public void setSmallContent(Integer value) {
		this.smallContent = value;
	}
	
	public Integer getSmallContent() {
		return smallContent;
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
			sb.append("TwoPackage[ ");
			sb.append("SN=").append(getSN()).append(" ");
			if (getSmallPkg() != null)
				sb.append("SmallPkg.Persist_ID=").append(getSmallPkg().toString(true)).append(" ");
			else
				sb.append("SmallPkg=null ");
			if (getBigPkg() != null)
				sb.append("BigPkg.Persist_ID=").append(getBigPkg().toString(true)).append(" ");
			else
				sb.append("BigPkg=null ");
			sb.append("SmallContent=").append(getSmallContent()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
	public static final class STATUS{
		public static final int STATUS_NOEMPTY = 1;
		public static final int STATUS_EMPTY = 0;
	}
	
}
