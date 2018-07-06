package ts.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="ExpressHistory")
@XmlRootElement(name="ExpressHistory")
public class ExpressHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5397570935169885209L;

	/**
	 * 
	 */

	public ExpressHistory() {
	}
	
	@Column(name="SN", nullable=false)	
	@Id	
	@GeneratedValue(generator="MODEL_EXPRESSHISTORY_SN_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="MODEL_EXPRESSHISTORY_SN_GENERATOR", strategy="native")	
	private int SN;
	
	@ManyToOne(targetEntity=ExpressSheet.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="ExpressID", referencedColumnName="ID", nullable=false) })	
	private ExpressSheet nes;

	@ManyToOne(targetEntity=TransNode.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="SourceNodeID", referencedColumnName="ID", nullable=true) })	
	private TransNode sourceNode;
	
	@ManyToOne(targetEntity=TransNode.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="TargetNodeID", referencedColumnName="ID", nullable=true) })	
	private TransNode targetNode;
	
	@Column(name="status", nullable=false)	
	private int status;
	
	@Column(name="tim", nullable=false)	
	private Date tim;
	
	@Column(name="username", nullable=true)	
	private String username;
	
	@Column(name="tel", nullable=true)	
	private String tel;
	
	
	public void setSN(int value) {
		this.SN = value;
	}
	
	public int getSN() {
		return SN;
	}
	
	public int getORMID() {
		return getSN();
	}
	
	public void setExpress(ExpressSheet value) {
		this.nes = value;
	}
	
	public ExpressSheet getExpress() {
		return nes;
	}
	
	public void setSourceNode(TransNode value) {
		this.sourceNode = value;
	}
	
	public TransNode getSourceNode() {
		return sourceNode;
	}
	
	public void setTargetNode(TransNode value) {
		this.targetNode = value;
	}
	
	public TransNode getTargetNode() {
		return targetNode;
	}
	
	public void setStatus(int value) {
		this.status= value;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setTime(Date value) {
		this.tim = value;
	}
	
	public Date getTime() {
		return tim;
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setTel(String value) {
		this.tel = value;
	}
	
	public String getTel() {
		return tel;
	}
  
	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean ExpressIdOnly) {
		if (ExpressIdOnly) {
			return String.valueOf(getExpress());
		}
		else {
			StringBuffer sb = new StringBuffer();
			sb.append("ExpressHistory[ ");
			sb.append("SN=").append(getSN()).append(" ");
			sb.append("Express=").append(getExpress()).append(" ");
			sb.append("SourceNode=").append(getSourceNode()).append(" ");
			sb.append("TargetNode=").append(getTargetNode()).append(" ");
			sb.append("status=").append(getStatus()).append(" ");
			sb.append("time=").append(getTime()).append(" ");
			sb.append("username=").append(getUsername()).append(" ");
			sb.append("tel=").append(getTel()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
	
	@Transient	
	private String SourceString;
	public void setSourceString(String value) {
		this.SourceString = value;
	}
	
	public String getSourceString() {
		return SourceString;
	}
	
	@Transient	
	private String TargetString;
	public void setTargetString(String value) {
		this.TargetString = value;
	}
	
	public String getTargetString() {
		return TargetString;
	}
	
	@Transient	
	private String TimeString;
	public void setTimeString(String value) {
		this.TimeString = value;
	}
	
	public String getTimeString() {
		return TimeString;
	}

	@Transient	
	private boolean _saved = false;
	
	public void onSave() {
		_saved=true;
	}
	
	
	public void onLoad() {
		_saved=true;
	}
	
	
	public boolean isSaved() {
		return _saved;
	}
	
}

		/*
		public static final class STATE{
			public static final int STATE_CREATED = 0;   //0表示快件已经揽件
			public static final int STATE_REACH = 2;      //2表示快件正在转运途中
			public static final int STATE_TRANSPORT = 1;   //1表示快件正在派送
			public static final int STATE_RECEIVED = 3;   //3表示快件已经签收
		}

		*/
	

