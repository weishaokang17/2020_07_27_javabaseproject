package per.wsk.parc;

import org.junit.Test;

/**
 * @Author weishaokang
 * @date 2020-07-31 22:15
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class StringExer {

    /**
     * 1. 模拟一个trim方法，去除字符串两端的空格。
     * 2. 将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反
     * 转为”abfedcg”
     * 3. 获取一个字符串在另一个字符串中出现的次数。
     * 比如：获取“ ab”在 “abkkcadkabkebfkabkskab” 中出现的次数
     * 4. 获取两个字符串中最大相同子串。比如：
     * str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"
     * 提示：将短的那个串进行长度依次递减的子串与较长的串比较。
     * 5.  对字符串中字符进行自然顺序排序。
     *      提示：
     * 1）字符串变成字符数组。
     * 2）对数组排序，选择，冒泡，Arrays.sort();
     * 3）将排序后的数组变成字符串。
     */

    @Test
    public void test(){
        String result = customizeTrim(" 波士顿 凯尔特人 总冠军 ");
        System.out.println(result);
        System.out.println("返回结果的长度是"+result.length());
        //输出12，去掉首尾空格长度就是12
        int length = customizeTrim("   ").length();
        System.out.println(length);


        String s = reverseStr("0123456789", 3, 7);
        System.out.println(s);


        int times = getTimes("abcdabcergtyabyyabba", "ab");
        System.out.println(times);

        String str1 = "abcwerthelloyuiodef";
        String str2 = "abcwhellodef";
        String bigestSonStr = getBigestSonStr(str1, str2);
        System.out.println(bigestSonStr);


        String sortResult = sortString("eruidfjkdaskdskwiqwer");
        System.out.println(sortResult);
    }

    /*
    1. 模拟一个trim方法，去除字符串两端的空格。
     */
    public static String customizeTrim(String str){
        if (str == null){
            throw new RuntimeException("字符串不能为空");
        }
        if (str.length() == 0) {
            return "";
        }

        int length = str.length();
        int startIndex = 0;
        int lastIndex = str.length()-1;

        boolean allSpace = true;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) != ' ' ) {
                startIndex = i;
                allSpace = false;
                break;
            }
        }
        if (allSpace) {
            return "";
        }


        for (int i = length-1; i >= 0; i--) {
            if (str.charAt(i) != ' ' ) {
                lastIndex = i;
                break;
            }
        }

        char[] charArray = new char[lastIndex-startIndex+1];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = str.charAt(startIndex+i);
        }


        return new String(charArray);
    }


    /*
    2. 将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反
     * 转为”abfedcg”
     */
   public static String reverseStr(String str,int startIndex,int lastIndex){
       if (str == null) {
           throw new RuntimeException("字符串不能为空");
       }
       if ( startIndex < 0 || lastIndex < startIndex || lastIndex >(str.length()-1)) {
           throw new RuntimeException("指定部分的下标有问题");
       }
       if (str.length() == 0|| str.length() ==1) {
           return str;
       }
       char[] resultChar = new char[str.length()];

       //开始反转的位置前面的元素一一赋值
       for (int i = 0; i < startIndex; i++) {
           resultChar[i] = str.charAt(i);
       }
       //结束反转的位置后面的元素一一赋值
       for (int i = lastIndex+1; i < resultChar.length; i++) {
           resultChar[i] = str.charAt(i);
       }
       for (int i = startIndex; i <= lastIndex ; i++) {
           resultChar[i] = str.charAt(startIndex+lastIndex-i);
       }

       return new String(resultChar);
   }

   /*
   3. 获取一个字符串在另一个字符串中出现的次数。
      比如：获取“ ab”在 “abkkcadkabkebfkabkskab” 中出现的次数
    */
    public static int getTimes(String str,String arg){
        int times = 0;
        if (str == null || arg == null) {
            throw new RuntimeException("参数有问题");
        }
        if (arg.length() > str.length()) {
            return 0;
        }
        //
        outer:
        for (int i = 0; i < str.length()-arg.length(); i++) {

            for (int j = 0; j < arg.length(); j++) {
                if (str.charAt(i+j) != arg.charAt(j)) {
                    continue outer;
                }
            }

            times++;
        }
        return times;
    }

    /*
    4. 获取两个字符串中最大相同子串。比如：
        str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"
        提示：将短的那个串进行长度依次递减的子串与较长的串比较。
     */
    public static String getBigestSonStr(String str1,String str2){
        if (str1 == null || str2 == null) {
            throw new RuntimeException("字符串参数不能为空");
        }
        String longStr = str1.length() > str2.length() ? str1 : str2;
        String shortStr = str1.length() <= str2.length() ? str1:str2;


        String result = null;
        String temp = null;

        outer:
        for (int i = 0; i < shortStr.length(); i++) {
            for (int j = 0; j <= i ; j++) {
                temp = shortStr.substring(j,shortStr.length()-i+j);
                if (longStr.indexOf(temp) != -1) {
                    result = temp;
                    break outer;
                }
            }

        }

        return result;

    }

    /*
    5.  对字符串中字符进行自然顺序排序。
     *      提示：
     * 1）字符串变成字符数组。
     * 2）对数组排序，选择，冒泡，Arrays.sort();
     * 3）将排序后的数组变成字符串。
     */
    public static String sortString(String arg){
        if (arg == null || arg.length() ==0 || arg.length() ==1) {
            return arg;
        }
        char[] chars = arg.toCharArray();


        for (int i = 0; i < chars.length; i++) {
            char big = chars[0];
            int bigIndex = 0;
            for (int j = 0; j < chars.length - i; j++) {
                if (chars[j] > big) {
                    big = chars[j];
                    bigIndex = j;
                }
            }

            chars[bigIndex] = chars[chars.length-i-1] ;
            chars[chars.length-i-1] = big;
        }

        return new String(chars);
    }


}
