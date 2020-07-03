import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dihua.wu
 * @description 两数之和
 * @create 2020/5/26
 */
public class Solution1TwoSum {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     *  
     *
     * 示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     *
     *
     * 思路：
     * 1循环
     * 2用target-nums[i]=result
     * 3判断result 是不是在数组中
     * 4可以把nums[i]放到map中，<nums[i],i>  i是位置
     * 5有，返回result的位置
     *
     *
     *
     *
     * @param nums 数组
     * @param target 目标数字
     * @return int[]
     */

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num2 = target - nums[i];
            if (map.containsKey(num2)) {
                return new int[]{map.get(num2), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 13;
        int[] returnNums = twoSum(nums, target);
        System.out.println(Arrays.toString(returnNums));
    }
}
