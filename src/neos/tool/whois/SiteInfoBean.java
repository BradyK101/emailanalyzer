package neos.tool.whois;

import java.util.LinkedList;

public class SiteInfoBean {

	//����
	private String domainName = "";
	
	//����ID
	private String roId = "";
	
	//����״̬
	private LinkedList<String> domainStatus = new LinkedList<String>();
	
	//����������
	private String organization = "";
	
	//ע����
	private String registrantName = "";
	
	//����Ա�ʼ�
	private String email = "";
	
	//ע����
	private String sponsorRegistrar = "";
	
	//DNS������
	private LinkedList<String> nameServer = new LinkedList<String>();
	
	//ע��ʱ��
	private String registrationDate = "";
	
	//����ʱ��
	private String expirationDate = "";

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getRoId() {
		return roId;
	}

	public void setRoId(String roId) {
		this.roId = roId;
	}

	public LinkedList<String> getDomainStatus() {
		return domainStatus;
	}

	public void setDomainStatus(LinkedList<String> domainStatus) {
		this.domainStatus = domainStatus;
	}
	
	public void addDomainStatus(String status) {
		getDomainStatus().add(status);
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getRegistrantName() {
		return registrantName;
	}

	public void setRegistrantName(String registrantName) {
		this.registrantName = registrantName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSponsorRegistrar() {
		return sponsorRegistrar;
	}

	public void setSponsorRegistrar(String sponsorRegistrar) {
		this.sponsorRegistrar = sponsorRegistrar;
	}

	public LinkedList<String> getNameServer() {
		return nameServer;
	}

	public void setNameServer(LinkedList<String> nameServer) {
		this.nameServer = nameServer;
	}
	
	public void addNameServer(String nameServer) {
		getNameServer().add(nameServer);
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Domain Name: " + this.getDomainName() + "\n");
		str.append("ROID: " + this.getRoId() + "\n");
		str.append("Domain Status: " + this.getDomainStatus().toString() + "\n");
		str.append("Registrant Organization: " + this.getOrganization() + "\n");
		str.append("Registrant Name: " + this.getRegistrantName() + "\n");
		str.append("Administrative Email: " + this.getEmail() + "\n");
		str.append("Sponsoring Registrar: " + this.getSponsorRegistrar() + "\n");
		str.append("Name Server: " + this.getNameServer().toString() + "\n");
		str.append("Registration Date: " + this.getRegistrationDate() + "\n");
		str.append("Expiration Date: " + this.getExpirationDate() + "\n");
		return new String(str);
	}
}
