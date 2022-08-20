import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
//输入字符串里面有时间戳，然后进行比较，先按照日期排序，再按照字符串长度，接下来按照字符串ASCII码的字典序
class MyTime{
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    String string;
    Date date;
    public MyTime(String string) throws ParseException {
        this.string = string;
        date = df.parse(string.split("/")[1]);
    }
}

public class CompareDate {
    public static void main(String[] args) throws ParseException {
        CompareDate main = new CompareDate();
        HashSet<String> hashSet = new HashSet<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // 注意 hasNext 和 hasNextLine 的区别
        while (n>0) { // 注意 while 处理多个 case
            String b = in.next();
            hashSet.add(b);
            n--;
        }
        List<MyTime> myTimeList = new ArrayList<>();
        for (String myTime:hashSet){
            myTimeList.add(new MyTime(myTime));
        }
        Collections.sort(myTimeList, new Comparator<MyTime>() {
            @Override
            public int compare(MyTime o1, MyTime o2) {
                int result = 0;
                if (o1.date.compareTo(o2.date)!=0){
                    result = o1.date.compareTo(o2.date)<0?-1:1;
                }else if (o1.string.length()!=o2.string.length()){
                    result = o1.string.length()<o2.string.length()?-1:1;
                }else if (o1.string.compareTo(o2.string)!=0){
                    result = o1.string.compareTo(o2.string)<0?-1:1;
                }
                return result;
            }
        });
        myTimeList.forEach(e-> System.out.println(e.string));
    }
}