package neos.tool.whois;

//~--- JDK imports ------------------------------------------------------------

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.net.InetAddress;
import java.net.Socket;

public class WhoisQuery {
    //public final static String CN_HOST      = "whois.cnnic.net.cn";
    //public final static String COM_HOST     = "whois.apnic.net";
    public final static int    DEFAULT_PORT = 43;
    
    /** ��������Ϣ���� */
	public final static String DEFAULT_HOST = "whois.internic.net";
	
	/** ������̫ƽ�����������Ϣ����(�Ĵ�����ī����) */
	public final static String APNIC_HOST   = "whois.apnic.net";
	
	/** �й���������м������������Ϣ����(�廪��ѧ���й�����) */
    public final static String CERNIC_HOST  = "whois.edu.cn";
    
    
    /** �й�����������Ϣ����(�й���ѧԺ���㼼���о������й�����) */
    public final static String CNNIC_HOST   = "whois.cnnic.net.cn";
    
    /** ̨�廥��������Ϣ����(�й�̨��̨��) */
    public final static String TWNIC_HOST   = "whois.twnic.net";
    
    /** �ձ�����������Ϣ����(�ձ�����) */
    public final static String JPNIC_HOST ="whois.nic.ad.jp";
    
    /** ��������������Ϣ����(��������) */
    public final static String KRNIC_HOST="whois.krnic.net";
    
    /** ŷ��IP��ַע������(������ķ˹�ص�) */
    public final static String RIPE_HOST="whois.ripe.net";
    
    /** �������޼����ձȻ���������Ϣ����(����ʥ����) */
    public final static String LACNIC_HOST="whois.lacnic.net";
    
    /** ����Internet����ע������(��������������Chantilly��) */
    public final static String ARIN_HOST="whois.arin.net";
    
    /** ���޻���������Ϣ����(Cyber City, Eb��ne, Mauritius) */
    public final static String AFRINIC_HOST="www.afrinic.net";

    public WhoisQuery() {

        //
    }

    /**
     * ��������������ȡwhois��Ϣ
     * @param name
     * @return
     */
    public SiteInfoBean getSiteInfo(String name) {
        SiteInfoBean siteInfo = new SiteInfoBean();
        InetAddress  server;
        int          port = DEFAULT_PORT;
        String       str  = "";
        String       type = getDomainType(name);
        String       host = "";

        try {
            if (type.equals("com")) {
                host = DEFAULT_HOST;
            } else if (type.equals("cn")) {
                host = CNNIC_HOST;
            } else {
                host = DEFAULT_HOST;
            }

            server = InetAddress.getByName(host);

            Socket theSocket = new Socket(server, port);
            Writer out       = new OutputStreamWriter(theSocket.getOutputStream(), "UTF-8");

            out.write(name + " " + "\r\n");
            out.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(theSocket.getInputStream(), "UTF-8"));

            while ((str = br.readLine()) != null) {
                setSiteInfo(siteInfo, str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return siteInfo;
    }

    /**
     * �������������ж���.com����.cn
     * ��ͬ����������ѯϵͳ��һ��
     * @param name
     * @return
     */
    public String getDomainType(String name) {
        String type = "";

        if (name.endsWith("com")) {
            type = "com";
        } else if (name.endsWith("cn")) {
            type = "cn";
        } else {

            //
        }

        return type;
    }

    /**
     * �����ַ�����ʾ����Ϣ��Ӧ�ĸ���SiteInfoBean
     * @param siteInfo
     * @param str
     */
    public void setSiteInfo(SiteInfoBean siteInfo, String str) {
        String[] info = str.split(":");

        if (info[0].equals("Domain Name")) {
            siteInfo.setDomainName(info[1]);
        } else if (info[0].equals("ROID")) {
            siteInfo.setRoId(info[1]);
        } else if (info[0].equals("Domain Status")) {
            siteInfo.addDomainStatus(info[1]);
        } else if (info[0].equals("Registrant Organization")) {
            siteInfo.setOrganization(info[1]);
        } else if (info[0].equals("Registrant Name")) {
            siteInfo.setRegistrantName(info[1]);
        } else if (info[0].equals("Administrative Email")) {
            siteInfo.setEmail(info[1]);
        } else if (info[0].equals("Sponsoring Registrar")) {
            siteInfo.setSponsorRegistrar(info[1]);
        } else if (info[0].equals("Name Server")) {
            siteInfo.addNameServer(info[1]);
        } else if (info[0].equals("Registration Date")) {
            siteInfo.setRegistrationDate(info[1].substring(0, info[1].length() - 3));
        } else if (info[0].equals("Expiration Date")) {
            siteInfo.setExpirationDate(info[1].substring(0, info[1].length() - 3));
        }
    }
}
