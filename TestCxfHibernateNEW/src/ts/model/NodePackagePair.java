package ts.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="NodePackagePair")
@XmlRootElement(name="NodePackagePair")
public class NodePackagePair implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7638831252105462949L;

	public NodePackagePair() {
	}
	
	
	@ManyToOne(targetEntity=TransNode.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NodeID", referencedColumnName="ID", nullable=false) })		
	@Id		
	private TransNode node;
	
	@ManyToOne(targetEntity=TransPackage.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="pkgID", referencedColumnName="ID", nullable=false) })	
	private TransPackage Pkg;
	
	
	public void setNode(TransNode value) {
		this.node = value;
	}
	
	public TransNode getNode() {
		return node;
	}
	
	public TransNode getORMID() {
		return getNode();
	}
	
	
	public void setPkg(TransPackage value) {
		this.Pkg = value;
	}
	
	public TransPackage getPkg() {
		return Pkg;
	}
	
	
	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getNode());
		}
		else {
			StringBuffer sb = new StringBuffer();
			sb.append("NodePackagePair[ ");
			sb.append("Node=").append(getNode()).append(" ");
			if (getNode() != null)
				sb.append("Node.Persist_ID=").append(getNode().toString(true)).append(" ");
			else
				sb.append("Node=null ");
			if (getPkg() != null)
				sb.append("Pkg.Persist_ID=").append(getPkg().toString(true)).append(" ");
			else
				sb.append("Pkg=null ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}

