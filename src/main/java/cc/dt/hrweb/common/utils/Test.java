package cc.dt.hrweb.common.utils;

import com.iceyyy.workday.WorkUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public static int findMaxLenght(String s) {
//        Map<Character, Integer> map = new HashMap();
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            map.put(c, i);//i表示索引 c表示字符
//        }

        List tmp = new ArrayList();
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);//p
            int index1 = s.indexOf(c);//0
            int index2 = s.indexOf(c, index1 + 1);//查第二次出现的位置  //-1
            if (index2 != -1) {
                int lenght = index2 - index1;
                tmp.add(lenght);
            }
        }

        int max = (int) tmp.get(0);
        for (int i = 0; i < tmp.size(); i++) {
            if ((int)tmp.get(i) > max) {
                max = (int) tmp.get(i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String str = "pwwkew";
//        System.out.println(find1(str));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        Calendar   calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,21); //把日期往后增加一天,整数  往后推,负数往前移动
        date=calendar.getTime();

        boolean workendDay = WorkUtils.isWorkendDay(formatter.format(new Date()));
        boolean workendDay1 = WorkUtils.isWorkendDay(formatter.format(date));
        System.out.println(formatter.format(date)+ "sss" + workendDay1);
    }


    public static int find1(String s) {
        if (s.length() <= 1)
            return s.length();

        int res = 0, left = -1;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c));
            }

            map.put(c, i);
            res = Math.max(res, i - left);
        }

        return res;
    }

    public static int find2(String s) {
        int tmp = 0;
        int tmp1 = 0;
        if (s.length()==1){
            tmp = 1;
        }

//        Map<Character, Integer> map = new HashMap<>();
        List list = new ArrayList();
        List list1 = new ArrayList();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (list.contains(c)&&i!=s.length()-1){
                list.remove(0);//移除第一个 保证永远是从最
//                map.put(c, i);
            }else if (i==s.length()-1 && list.contains(c)){
                break;
            }else {
                list.add(c);
            }
            tmp = Math.max(tmp, list.size());
        }

        for (int i = s.length() - 1; i >=0; i--) {
            char c = s.charAt(i);

            if (list1.contains(c)&&i!=0){
                list1.remove(list1.size()-1);//移除第一个 保证永远是从最
//                map.put(c, i);
            }else if (i==0 && list.contains(c)){
                break;
            }else {
                list1.add(c);
            }
            tmp1 = Math.max(tmp1, list1.size());
        }

        return Math.max(tmp, tmp1);
    }

    public static int find3(String s) {
        int res = 0;
        Set<Character> set = new HashSet<>();
        for(int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            while(set.contains(c)) {
                set.remove(s.charAt(l++));
            }
            set.add(c);
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

}
