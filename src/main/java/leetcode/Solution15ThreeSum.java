package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dihua.wu
 * @description 三数之和
 * @create 2020/6/24
 */
public class Solution15ThreeSum {

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     *  
     *
     * 示例：
     *
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     *
     * 思路：
     * 排序 双指针
     *
     * 先排序
     * i 数组nums初始位置0
     * left=i+1
     * right=length-1
     * sum = num[i]+num[left]+num[right]
     * for(i=0 i++)
     *  if(sum < 0) left ++
     *      if(left==right) break
     *      if(sum>0) break
     *  if(sum > 0) right --
     *
     *
     *
     */



    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }
}
