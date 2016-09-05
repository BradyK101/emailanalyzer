package neos.tool.googlemap.staticmap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MapParameter {
	private final static int DefaultZoomLevel=14;
	
	/**
	 * centerMap �洢��ͼ����λ����Ϣ
	 * ����γ�ȣ����ȣ��;������
	 * ʵ�ʹ�����ֻ��������ȣ�γ�� ���ߵ�����Ϣ
	 * float latitude , float longitude , float location
	 */
	private Location center = new Location();
	
	//zoom ��ͼ���Ų���
	private int zoom = DefaultZoomLevel;
	
	//size �����ͼ��С width,height(��������)
	private HashMap<String,String> sizeMap = new HashMap<String,String>();
	
	//format �������ɵ�ͼͼƬ�ĸ�ʽ gif,png,jpeg
	private String format = "PNG";
	
	//maptype �����ͼ����  roadmap��satellite��hybrid �� terrain
	private String maptype = "roadmap";
	
	//mobile ָ���Ƿ����ƶ��豸����ʾ��ͼ
	private boolean mobile = false;
	
	//language ���õ�ͼͼ������ʾ��ǩʱ���õ����� �� ��֧�ֲ��ֹ��Һ͵���
	private String language = "";
	
	//sensor ָ������̬��ͼ��Ӧ�ó����Ƿ�ʹ�ô�����ȷ���û���λ��(��������),Ĭ��Ϊfalse
	private boolean sensor = false;
	
	//visible ָ��һ��������ʹ����ʾ��ǻ�����ָʾ��ҲӦ���ڵ�ͼ�ϱ��ֿɼ���λ��
	private List<Location> visibleLocation = new LinkedList<Location>();
	
	//path path �������ڶ���һ��λ�ü��ϣ�����һ������λ�ã�����Щλ����һ�������ڵ�ͼͼ���ϵ�·������
	private Path path = new Path();
	
	//markers ��������Ϊĳ��λ�ö���һ����Ǽ��ϣ�����һ��������ǣ�
	private List<Mark> markList = new LinkedList<Mark>();

	public MapParameter() {
		zoom = DefaultZoomLevel;
		format = "PNG";
		maptype = "roadmap";
		mobile = false;
		sensor = false;
		sizeMap = new HashMap<String,String>();
		sizeMap.put("width", "600");
		sizeMap.put("height", "600");
	}
	

	 /************************ Location parameter:center**********************/

	/**
	 * Define the center of the map use the latitude and longitude
	 * @param latitude(γ��)
	 * @param longitude(����)
	 * Latitude and longitude have a precision to 6 decimal places.
	 * For example "40.714728,-73.998672"
	 * Precision beyond the 6 decimal places is ignored
	 * Latitude value between -90 and 90
	 * Longitude value between -180 and 180
	 */
	public void setCenter(float latitude, float longitude) {
		Location l = new Location(latitude,longitude);
		this.center = l;
	}
	
	/**
	 * @param location
	 * Define the center of the map use the location
	 */
	public void setCenter(String location) {
		Location l = new Location(location);
		this.center = l;
	}
	
	public void setCenter(Location l) {
		this.center = l;
	}
	
	public Location getCenter() {
		return center;
	}
	
	 /************************ Location parameter:zoom**********************/
	/**
	 * Define the zoom level of the map
	 * ����ʹ�ô� 0��������ż����ڵ�ͼ�Ͽ��Կ����������磩�� 21+�����Կ�����������壩�����ż���
	 * Ĭ��ʹ��14
	 */
	public void setZoom(int zoom) {
		if(zoom>=0 && zoom<=21) {
			this.zoom = zoom;
		}
		else {
			zoom = DefaultZoomLevel;
		}
	}
	
	public int getZoom() {
		return zoom;
	}
	
	 /************************ Map parameter:size**********************/
	/**
	 * �����ͼ��С���������ÿ�͸���������
	 */
	public void setSize(int width, int height) {
		Integer w = new Integer(width);
		Integer h = new Integer(height);
		getSize().put("width", w.toString());
		getSize().put("height", h.toString());
	}
	
	public HashMap<String,String> getSize() {
		return sizeMap;
	}
	
	/************************ Map parameter:format**********************/
	/**
	 * �������ɵ�ͼͼƬ��ʽ������ GIF��JPEG �� PNG ����
	 * Ĭ��ʹ��PNG��ʽ
	 */
	public void setFormat(String format) {
		if(checkformat(format)) {
			this.format = format;
		}
		else {
			this.format = "png";
		}
	}
	
	public String getFormat() {
		return format;
	}
	
	public boolean checkformat(String format) {
		if(format.equals("png") || format.equals("png8") || format.equals("png32") || 
				format.equals("gif") || format.equals("jpg") || format.equals("jpg-baseline")) {
			return true;
		}
		return false;
		
	}
	
	/************************ Map parameter:maptype**********************/
	/**
	 * ���õ�ͼ���ͣ� һ����roadmap��satellite��hybrid �� terrain��������
	 * Ĭ��ʹ��roadmap����
	 */
	public void setMapType(String maptype) {
		if(checktype(maptype)) {
			this.maptype = maptype;
		}
		else {
			this.maptype = "roadmap";
		}
	}
	
	public String getMapType() {
		return maptype;
	}
	
	public boolean checktype(String type) {
		if(type.equals("rodemap") || type.equals("satellite") || 
				type.equals("hybrid") || type.equals("terrain")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/************************ Map parameter:mobile**********************/
	/**
	 * ָ���Ƿ����ƶ��豸����ʾ��ͼ Ĭ��Ϊfasle
	 */
	public void setMobile(boolean bool) {
		this.mobile = bool;
	}
	
	public boolean getMobile() {
		return mobile;
	}
	
	/************************ Map parameter:language**********************/
	/**
	 * language ���õ�ͼͼ������ʾ��ǩʱ���õ����� �� ��֧�ֲ��ֹ��Һ͵���
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getLanguage() {
		return language;
	}
	
	/************************ Report parameter:sensor**********************/
	/**
	 * ָ������̬��ͼ��Ӧ�ó����Ƿ�ʹ�ô�����ȷ���û���λ��(��������),Ĭ��Ϊfalse
	 */
	public void setSensor(boolean bool) {
		this.sensor = bool;
	}
	
	public boolean getSensor() {
		return sensor;
	}
	
	/************************ Feature parameter:visible**********************/
	/**
	 * visible ָ��һ��������ʹ����ʾ��ǻ�����ָʾ��ҲӦ���ڵ�ͼ�ϱ��ֿɼ���λ��
	 */
	public void addVisibleLocation(float latitude, float longitude) {
		Location l = new Location(latitude,longitude);
		visibleLocation.add(l);
	}
	
	public void addVisibleLocation(String address) {
		Location l = new Location(address);
		visibleLocation.add(l);
	}
	
	public void addVisibleLocation(Location l) {
		visibleLocation.add(l);
	}
	
	public void setVisibleLocation(LinkedList<Location> locationList) {
		this.visibleLocation = locationList;
	}
	
	public List<Location> getVisibleLocation() {
		return visibleLocation;
	}
	
	/************************ Feature parameter:path**********************/
	/**
	 * path �������ڶ���һ��λ�ü��ϣ�����һ������λ�ã�����Щλ����һ�������ڵ�ͼͼ���ϵ�·������
	 */
	public void setPath(Path p) {
		this.path = p;
	}
	
	public Path getPath() {
		return path;
	}

	
	/************************ Feature parameter:mark**********************/
	/**
	 * markers ��������Ϊĳ��λ�ö���һ����Ǽ��ϣ�����һ��������ǣ���
	 */
	public List<Mark> getMarkList() {
		return markList;
	}


	public void setMarkList(List<Mark> markList) {
		this.markList = markList;
	}
	
	public void addMark(Mark mark) {
		this.markList.add(mark);
	}
	
	
	
	
	
}
