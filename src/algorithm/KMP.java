package algorithm;

/**
 * 暴力匹配移动i，KMP的i不动，j动
 */

import java.util.ArrayList;
import java.util.List;

public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println("暴力匹配结果：" + violenceMatch(str1, str2));
        System.out.println("KMP匹配结果：" + kmpSearch(str1, str2));
    }

    // 暴力匹配
    public static int violenceMatch(String str1, String str2) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            } else {
                i = i - j;
                j = 0;
            }
            // 输出结果
            if (j == str2.length()) { // 找到了
                return i - j + 1; // 返回第一次出现的位置
            }
        }
        return -1;
    }

    // KMP算法
    public static int kmpSearch(String str1, String str2) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 当不等时 对j进行调整（KMP的精髓） i不动，j变化即可
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = matchValue(str2.substring(0, j));
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            // 输出结果
            if (j == str2.length()) { // 找到了
                return i - j + 1; // 返回第一次出现的位置
            }
        }
        return -1;
    }

    // 获取匹配值 输入一个字符串 返回其匹配值
    public static int matchValue(String str) {
        char[] ch = str.toCharArray();
        List<String> prefix = new ArrayList<>();
        List<String> suffix = new ArrayList<>();
        String temp = "";
        // 求前缀并保存在链表中
        for (int i = 0; i < ch.length - 1; i++) {
            temp = temp + ch[i];
            prefix.add(temp);
        }

        // 求后缀并保存在链表中
        temp = "";  // 重置temp
        for (int i = ch.length - 1; i > 0; i--) {
            temp = ch[i] + temp; // 注意顺序
            suffix.add(temp);
        }

        temp = ""; // 再次重置temp
        // 寻找共有元素 前缀和后缀拥有的元素个数是一样的，按照特定顺序排列，找相等的应该是同一位置
        // eg: 字符串"ABCDABD"，前缀为[A, AB, ABC, ABCD, ABCDA, ABCDAB]；后缀为[D, BD, ABD, DABD, CDABD, BCDABD]
        for (int i = 0; i < prefix.size(); i++) {
            if (prefix.get(i).equals(suffix.get(i))) {
                temp = prefix.get(i);
            }
        }
        return temp.length();
    }
}
