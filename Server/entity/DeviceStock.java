package entity;

import java.io.Serializable;
/**
 * @author daniella
 *
 */
public class DeviceStock implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int deviceId;
	private int bamba;
	private int klik;
	private int cocaCola;
	private int sevenUp;
	private int katkatat;
	/**
	 * @param deviceId
	 * @param bamba
	 * @param klik
	 * @param cocaCola
	 * @param sevenUp
	 * @param katkatat
	 */
	public DeviceStock(int deviceId, int bamba, int klik, int cocaCola, int sevenUp, int katkatat) {
		super();
		this.deviceId = deviceId;
		this.bamba = bamba;
		this.klik = klik;
		this.cocaCola = cocaCola;
		this.sevenUp = sevenUp;
		this.katkatat = katkatat;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public int getBamba() {
		return bamba;
	}
	public void setBamba(int bamba) {
		this.bamba = bamba;
	}
	public int getKlik() {
		return klik;
	}
	public void setKlik(int klik) {
		this.klik = klik;
	}
	public int getCocaCola() {
		return cocaCola;
	}
	public void setCocaCola(int cocaCola) {
		this.cocaCola = cocaCola;
	}
	public int getSevenUp() {
		return sevenUp;
	}
	public void setSevenUp(int sevenUp) {
		this.sevenUp = sevenUp;
	}
	public int getKatkatat() {
		return katkatat;
	}
	public void setKatkatat(int katkatat) {
		this.katkatat = katkatat;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
