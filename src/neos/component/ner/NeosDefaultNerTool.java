package neos.component.ner;

//~--- non-JDK imports --------------------------------------------------------

import neos.component.ner.NeosNamedEntity.NamedEntityType;

import neos.tool.fudannlp.NeosFudanNerTool;
import neos.tool.stanfordnlp.NeosStanfordNerTool;

//~--- JDK imports ------------------------------------------------------------

import java.util.HashMap;

public class NeosDefaultNerTool implements NeosNerTool {
    private final static String datePat01_1 =
        "(?<=[\\D[^��һ�����������߰˾�ʮ]])(((([1920]{2})?\\d{2})|([��һ�����������߰˾�ʮ]{2,4}))[��\\-/\\s])(?!((\\d{3,}|[��һ�����������߰˾�ʮ]{4,})))(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[��\\-/\\s])?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[�պ�\\s]?)?(����|�賿|����|����|����|����|��ҹ)?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[ʱ��\\-/:])?(((\\d{1,2})|([��һ�����������߰˾�ʮ��]{1,3}))[��\\-/:])?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[��]?)?(?<!(\\d{1,2}[\\s\\-:]?)|[��һ�����������߰˾�ʮ])(?=\\D)";
    private final static String datePat01_2 =
        "(?<=[\\D[^��һ�����������߰˾�ʮ]])(((([1920]{2})?\\d{2})|([��һ�����������߰˾�ʮ]{2,4}))[��\\-/\\s])?(?!((\\d{3,}|[��һ�����������߰˾�ʮ]{4,})))(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[��\\-/\\s])(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[�պ�\\s]?)?(����|�賿|����|����|����|����|��ҹ)?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[ʱ��\\-/:])?(((\\d{1,2})|([��һ�����������߰˾�ʮ��]{1,3}))[��\\-/:])?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[��]?)?(?<!(\\d{1,2}[\\s\\-:]?)|[��һ�����������߰˾�ʮ])(?=\\D)";
    private final static String datePat01_3 =
        "(?<=[\\D[^��һ�����������߰˾�ʮ]])(((([1920]{2})?\\d{2})|([��һ�����������߰˾�ʮ]{2,4}))[��\\-/\\s])?(?!((\\d{3,}|[��һ�����������߰˾�ʮ]{4,})))(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[��\\-/\\s])?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[�պ�\\s]?)(����|�賿|����|����|����|����|��ҹ)?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[ʱ��\\-/:])?(((\\d{1,2})|([��һ�����������߰˾�ʮ��]{1,3}))[��\\-/:])?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[��]?)?(?<!(\\d{1,2}[\\s\\-:]?)|[��һ�����������߰˾�ʮ])(?=\\D)";
    private final static String datePat01_4 =
        "(?<=[\\D[^��һ�����������߰˾�ʮ]])(((([1920]{2})?\\d{2})|([��һ�����������߰˾�ʮ]{2,4}))[��\\-/\\s])?(?!((\\d{3,}|[��һ�����������߰˾�ʮ]{4,})))(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[��\\-/\\s])?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[�պ�\\s]?)?(����|�賿|����|����|����|����|��ҹ)?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[ʱ��\\-/:])(((\\d{1,2})|([��һ�����������߰˾�ʮ��]{1,3}))[��\\-/:])?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[��]?)?(?<!(\\d{1,2}[\\s\\-:]?)|[��һ�����������߰˾�ʮ])(?=\\D)";
    private final static String datePat01_5 =
        "(?<=[\\D[^��һ�����������߰˾�ʮ]])(((([1920]{2})?\\d{2})|([��һ�����������߰˾�ʮ]{2,4}))[��\\-/\\s])?(?!((\\d{3,}|[��һ�����������߰˾�ʮ]{4,})))(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[��\\-/\\s])?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[�պ�\\s]?)?(����|�賿|����|����|����|����|��ҹ)?(((\\d{1,2})|([��һ�����������߰˾�ʮ]{1,3}))[ʱ��\\-/:])(((\\d{1,2})|([��һ�����������߰˾�ʮ��]{1,3}))[��\\-/:]?)?(?<!\\d{2}[\\s\\-:]?)(?=\\D)";
    private final static String datePat02 = "[��������][��]?(��|���|����)[һ��������������\\d]";
    private final static String datePat03 = "[�����±�][��]?��[����ĩ]?([��һ�����������߰˾�ʮ]{1,3}|\\d{1,2})[�պ�]";
    private final static String datePat04 = "(?<=[\\D[^��һ�����������߰˾�ʮ]])[��һ�����������߰˾�ʮ]{1,3}([����������]|���|����|Сʱ)[��֮][ǰ��]";
    private final static String datePat05 =
        "((Jan(uary)?)|(Feb(ruary)?)|(Mar(ch)?)|(Apr(il)?)|(May)|(Jun(e)?)|(Jul(y)?)|(Aug(ust)?)|(Sep(tember)?)|(Oct(ober)?)|(Nov(ember)?)|(Dec(ember)))[\\.,](\\s*)[0-9]{1,2}((st)|(nd)|(rd)|(th))?([,]*[\\s*][\\d]+)?";
    private final static String datePat06 =
        "((last)|(next)|(this))?(\\s)*((Mon(day)?)|(Tues(day)?)|(Wed(nesday)?)|(Thur(day)?)|(Fri(day)?)|(Sat(day)?)|(Sun(day)))";
    private final static String emailPat =
        "[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.(([0-9]{1,3})|([a-zA-Z]{2,3})|(aero|coop|info|museum|name))";
    private final static String idCardPat01 = "(?<=\\D)[1-9]\\d{14}(?=\\D)";
    private final static String idCardPat02 = "(?<=\\D)[1-9]\\d{17}(?=\\D)";
    private final static String locPat01    =
        "(?<=[�ڵ�ȥ��])[\\w\u4e00-\u9fa5]{2,4}(·|��|(���)|(С��)|(����))([��һ�����������߰˾�ʮ\\d]{1,3}��)?";
    private final static String locPat02  = "(?<=[������ȥ])([\\w\u4e00-\u9fa5]{2,4}(��|ʡ|��|��|��|��|(������)))+";
    private final static String locPat03  = "(?<=[������ȥ])([^\\s������ȥ\\pP\\pS]){3,20}(?=(��|��ͷ|����|��))";
    private final static String mobilePat = "(?<=\\D)1[358]\\d{9}(?=\\D)";
    private final static String orgPat01  =
        "(?<=[������ȥ])([^\\s������ȥ\\pP\\pS]){2,8}(��˾|����|��|��|��|��|��֯|����|����|Ժ|��)(?=(����|ѧϰ|�ϰ�)?)";
    private final static String phonePat01 =
        "(?<=\\D)(\\+[\\d]{2,4}[\\s]?)?(\\(?0[\\d]{2,4}\\)?[\\s-]?)?([\\d]{3,4})[\\s-]?([\\d]{3,4})(?=\\D)";
    private final static String phonePat02 = "(?<=\\D)\\(?[\\d]{3}\\)?[\\s-]?[\\d]{3}[\\s-]?[\\d]{4}(?=\\D)";
    private final static String phonePat03 = "(?<=\\D)(1?(-?\\d{3})-?)?(\\d{3})(-?\\d{4})(?=\\D)";
    //private final static String phonePat04="(?<=\\D)[\\d]"
    private final static String urlPat     =
        "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
    private final static String zipCodePat   = "(?<=\\D)\\d{6}(?=\\D)";
    private boolean             isEnglishNer = true;
    private boolean             isChineseNer = true;

    // private HashMap<String, NamedEntityType> currMap;
    private final NeosFudanNerTool    ftool;
    private final NeosRegexNerTool    rtool;
    private final NeosStanfordNerTool stool;

    public NeosDefaultNerTool() {
        stool = NeosStanfordNerTool.getInstance();
        ftool = NeosFudanNerTool.getInstance();
        rtool = new NeosRegexNerTool();
        initRegexNerTool();
    }

    private void initRegexNerTool() {
        rtool.addRegex(phonePat01, NamedEntityType.PhoneNumber);
        rtool.addRegex(phonePat02, NamedEntityType.PhoneNumber);
        rtool.addRegex(phonePat03, NamedEntityType.PhoneNumber);
        rtool.addRegex(datePat01_1, NamedEntityType.DateTime);
        rtool.addRegex(datePat01_2, NamedEntityType.DateTime);
        rtool.addRegex(datePat01_3, NamedEntityType.DateTime);
        rtool.addRegex(datePat01_4, NamedEntityType.DateTime);
        rtool.addRegex(datePat01_5, NamedEntityType.DateTime);
        rtool.addRegex(datePat02, NamedEntityType.DateTime);
        rtool.addRegex(datePat03, NamedEntityType.DateTime);
        rtool.addRegex(datePat04, NamedEntityType.DateTime);
        rtool.addRegex(datePat05, NamedEntityType.DateTime);
        rtool.addRegex(datePat06, NamedEntityType.DateTime);
        rtool.addRegex(emailPat, NamedEntityType.EmailAddress);
        rtool.addRegex(zipCodePat, NamedEntityType.PostalCode);
        rtool.addRegex(idCardPat01, NamedEntityType.IDCardNumber);
        rtool.addRegex(idCardPat02, NamedEntityType.IDCardNumber);
        rtool.addRegex(mobilePat, NamedEntityType.MobilePhoneNumber);
        rtool.addRegex(urlPat, NamedEntityType.URL);
        rtool.addRegex(locPat01, NamedEntityType.LocationName);
        rtool.addRegex(locPat02, NamedEntityType.LocationName);
        rtool.addRegex(locPat03, NamedEntityType.LocationName);
        rtool.addRegex(orgPat01, NamedEntityType.OrgnizationName);
    }

    public void enableEnglishNer(boolean is) {
        isEnglishNer = is;
    }

    public void enableChineseNer(boolean is) {
        isChineseNer = is;
    }

    @Override
    public HashMap<String, NamedEntityType> locate(String text) {
        HashMap<String, NamedEntityType> map  = new HashMap<String, NamedEntityType>();
        HashMap<String, NamedEntityType> rmap = rtool.locate(text);

        for (String word : rmap.keySet()) {
            map.put(word, rmap.get(word));
        }

        rmap.clear();

        if (isEnglishNer) {
            HashMap<String, NamedEntityType> smap = stool.locate(text);

            for (String word : smap.keySet()) {
                map.put(word, smap.get(word));
            }

            smap.clear();
        }

        if (isChineseNer) {
            HashMap<String, NamedEntityType> fmap = ftool.locate(text);

            for (String word : fmap.keySet()) {
                map.put(word, fmap.get(word));
            }

            fmap.clear();
        }

        return map;
    }

    public static void main(String[] args) {
        String             text =
            "��������һ�Ű�һ��һ�¶�ʮ��������5���ں��ϳ�ɳ��������������1980-01-23����10���ں��ϰ��������ġ������Ժ��ʱ�������ȫ�ֿ��㡣I was bord on Jan. 23th, 1980. �ҵĵ绰������15829622540��";
        NeosDefaultNerTool nt   = new NeosDefaultNerTool();

        nt.enableChineseNer(true);
        nt.enableEnglishNer(true);
        System.out.println(nt.locate(text));
    }
}
