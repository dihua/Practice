package leetcode;

/**
 * @author dihua.wu
 * @description
 * @create 2020/11/10
 */
public class Solution43TwoStringMutiply {

    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     *
     * 示例 1:
     *
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * 示例 2:
     *
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     * 说明：
     *
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/multiply-strings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        Solution43TwoStringMutiply s = new Solution43TwoStringMutiply();
//        s.multiply("1234", "567");
        s.multiply("1230", "567");
    }

    /**
     * 两数相乘，相当于第二个数的每一位都乘以第一个数，再累加
     * 1230 * 567 = 1230 * 7 + 1230 * 60 + 1230 * 500
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        //ans 结果
        String ans = "0";
        int m = num1.length(), n = num2.length();

        //从右到左遍历num2
        //num2每一位 乘以 num1的每一位

        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            //进位
            int add = 0;
            //补0
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            //遍历num1，让num2的某一位 乘以 num1的每一位
            for (int j = m - 1; j >= 0; j--) {
                //如果该位为0，x=0
                int x = num1.charAt(j) - '0';
                //某一位相乘的结果
                int product = x * y + add;
                //结果的余数，（由于append是往后添加，所以最后结果要反过来）
                curr.append(product % 10);
                //进位
                add = product / 10;
            }
            //最高位进位
            if (add != 0) {
                curr.append(add % 10);
            }
            //将结果反过来，然后把几次结果累加
            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        //ans 结果
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            //结果
            ans.append(result % 10);
            //进位
            add = result / 10;
            i--;
            j--;
        }
        //字符串反转
        ans.reverse();
        return ans.toString();
    }

}
