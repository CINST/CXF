package ts.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="ExpressRoute")
@XmlRootElement(name="ExpressRoute")
public class ExpressRoute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2810581052858392525L;

	

	public ExpressRoute(){
	}
	
	
	@Column(name="SN", nullable=false)	
	@Id	
	@GeneratedValue(generator="MODEL_PACKAGEROUTE_SN_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="MODEL_PACKAGEROUTE_SN_GENERATOR", strategy="native")	
	private int SN;
	
	@ManyToOne(targetEntity=ExpressSheet.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="ExpresssheetID", referencedColumnName="ID", nullable=false) })	
	private ExpressSheet nes;
	
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
	
	public void setNes(ExpressSheet value) {
		this.nes = value;
	}
	
	@XmlTransient
	public ExpressSheet getNes() {
		return nes;
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
			sb.append("ExpressRoute[ ");
			sb.append("SN=").append(getSN()).append(" ");
			if (getNes() != null)
				sb.append("Nes.Persist_ID=").append(getNes().toString(true)).append(" ");
			else
				sb.append("Nes=null ");
			sb.append("X=").append(getX()).append(" ");
			sb.append("Y=").append(getY()).append(" ");
			sb.append("Tm=").append(getTm()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}

