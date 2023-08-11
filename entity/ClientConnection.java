package entity;

public class ClientConnection {
	private String ip;
	private String Host;
	private String Status;
	public ClientConnection(String ip, String host, String status) {
		this.ip = ip;
		Host = host;
		Status = status;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHost() {
		return Host;
	}
	public void setHost(String host) {
		this.Host = host;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		this.Status = status;
	}
	
}
