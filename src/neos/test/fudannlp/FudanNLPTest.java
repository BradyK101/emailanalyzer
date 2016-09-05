package neos.test.fudannlp;

import java.util.HashMap;
import java.util.List;

import edu.fudan.nlp.chinese.ner.Address;
import edu.fudan.nlp.chinese.ner.TimeNormalizer;
import edu.fudan.nlp.chinese.ner.TimeUnit;
import edu.fudan.nlp.tag.NERTagger;
import edu.fudan.nlp.tag.POSTagger;

public class FudanNLPTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String target = "�������뵽�ˡ�08�걱�������˻ᣬ8��8�ſ�Ļʽ������ʮ�˺ű�Ļʽ��" +
			"1����7��21�ŷ����˼����¡�" +
			"�����ұ���ȥ�����ᣬ������̫���ˣ�ֱ������9���˻�����ô�ࡣ" +
			"�⼸�쿼�ǵ�����ͺ����˻�����ô�࣬������������ȥ������ʦ˵����ַ����������·53�š�"+
			"���Ÿ��������˵������˾�������ϰ˵��Ϳ���������С�������ˡ����Ϲ��͹���Ժ������ҵ����ר�ŷ�����֪ͨ������¡�";
//		TimeNormalizer normalizer;
//		normalizer = new TimeNormalizer("./data/TimeExp.gz");
//		normalizer.parse(target,"2010-09-01-10-15-20");
//		TimeUnit[] unit = normalizer.getTimeUnit();
//		for(int i = 0; i < unit.length; i++){
//			System.out.println(unit[i]);
//		}
		
		try {
			NERTagger tag = new NERTagger("./data/ner.p111014.gz");
			HashMap<String, String> s = tag.tag(target);
			System.out.println(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			POSTagger ptag=new POSTagger("data/pos.c110722.gz");
			System.out.println(ptag.tag(target));
		} catch (Exception e) {
			
			e.printStackTrace();
		}*/
		
		Address addr=new Address();
		List<String> addrList=addr.tag(target);
		for(String address:addrList){
			System.out.println(address);
		}

	}

}
