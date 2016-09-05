package neos.tool.fudannlp;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.fudan.nlp.chinese.ner.TimeNormalizer;
import edu.fudan.nlp.chinese.ner.TimeUnit;

public class NeosFudanTimeTool {
	private final static String model="./data/TimeExp.gz";
	private final static NeosFudanTimeTool tool=new NeosFudanTimeTool();
	private final static SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
	private TimeNormalizer normalizer;
	
	
	private NeosFudanTimeTool(){
		normalizer = new TimeNormalizer(model);
	}
	
	public static NeosFudanTimeTool getInstance(){
		return tool;
	}
	
	public TimeUnit[] parse(String text, Date date){
		String day=fmt.format(date);		
		normalizer.parse(text, day);		
		return normalizer.getTimeUnit();
	}
	
	public TimeUnit[] parse(String text){
		return parse(text, new Date());
	}
	
	public static void main(String[] args){
		String testStr="��9��11�գ����ǿ����˱���ٸ������ٸ��1958��10��23�����������ֵġ���һ���ľ���ʮ��һ�ս����������ܶ��οͶ��������˴����������ʮһ�¶�ʮ����Ϊֹ������һ������������";
		
		NeosFudanTimeTool t=new NeosFudanTimeTool();
		TimeUnit[] units=t.parse(testStr);
		
		for(TimeUnit u:units){
			System.out.println(u.toString());
		}
	}
}
