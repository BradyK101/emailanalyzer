package neos.tool.ip;

public class IPEntry {
	public String area;
    public String beginIp;
    public String country;
    public String endIp;

    /**
     * ���캯��
     */
    public IPEntry() {
        beginIp = endIp = country = area = "";
    }
}
