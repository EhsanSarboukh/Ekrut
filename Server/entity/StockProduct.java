package entity;

import java.io.Serializable;

public class StockProduct implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer deviceid;
	private Integer Bamba;
	private Integer klik;
	private Integer CocaCola;
	private Integer SevenUp;
	private Integer katkatat;
	
	public StockProduct(Integer deviceid,Integer bamba,Integer klik,Integer CocaCola,Integer sevanup,Integer katkatat) {
		super();
		this.deviceid = deviceid;
		this.Bamba = bamba;
		this.klik = klik;
		this.CocaCola = CocaCola;
		this.SevenUp = sevanup;
		this.katkatat = katkatat;
		
	}

	public Integer getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}

	public Integer getBamba() {
		return Bamba;
	}

	public void setBamba(Integer bamba) {
		Bamba = bamba;
	}

	public Integer getKlik() {
		return klik;
	}

	public void setKlik(Integer klik) {
		this.klik = klik;
	}

	public Integer getCocaCola() {
		return CocaCola;
	}

	public void setCocaCola(Integer cocaCola) {
		CocaCola = cocaCola;
	}

	public Integer getSevenUp() {
		return SevenUp;
	}

	public void setSevenUp(Integer sevenUp) {
		SevenUp = sevenUp;
	}

	public Integer getKatkatat() {
		return katkatat;
	}

	public void setKatkatat(Integer katkatat) {
		this.katkatat = katkatat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}





