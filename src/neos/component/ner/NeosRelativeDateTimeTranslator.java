package neos.component.ner;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import neos.util.ConvertResult;

public class NeosRelativeDateTimeTranslator implements NeosEntityTranslator {
	private final static Map<String, String> map=new Hashtable<String, String> ();	
	
	private final static String[] ThisDays={"����","����","��","����","���","today","tonight"};
	private final static String[] NextDays={"����","����","����","����","����","tomorrow"};
	private final static String[] Next2Days={"����","���","day after tomorrow"};
	private final static String[] Next3Days={"�����"};
	private final static String[] PrevDays={"����","����","����","yesterday"};
	private final static String[] Prev2Days={"ǰ��","ǰ��","day before yesterday"};
	private final static String[] Prev3Days={"��ǰ��"};
	private final static String[] NextDaysPattern={"\\d+���","\\d+���Ժ�","\\d+��֮��"};
	private final static String[] PrevDaysPattern={"\\d+��ǰ","\\d+����ǰ","\\d+��֮ǰ"};
	private final static String[] ThisWeekPattern={"����\\d{1}","����\\d{1}","�����\\d{1}","����\\d{1}","��\\d{1}","���\\d{1}","����\\d{1}"};
	private final static String[] NextWeekPattern={"����\\d{1}","�����\\d{1}","�¸����\\d{1}","������\\d{1}","�¸�����\\d{1}"};
	private final static String[] PrevWeekPattern={"����\\d{1}","�����\\d{1}","�ϸ����\\d{1}","������\\d{1}","�ϸ�����\\d{1}"};
	
	
	public NeosRelativeDateTimeTranslator(){
		this(new Date());
	}
	
	public NeosRelativeDateTimeTranslator(Date refDate){
		this(refDate, NeosDateTimeEntity.DefaultFlag);
	}
	
	public NeosRelativeDateTimeTranslator(Date refDate, int flag){
		
	}

	@Override
	public boolean isCapable(String src) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ConvertResult<NeosNamedEntity>> translate(String src) {
		// TODO Auto-generated method stub
		return null;
	}

}
