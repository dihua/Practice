/**
 * @author dihua.wu
 * @description 二分查找
 * @create 2020/7/3
 */
public class BinarySearch {

    /**
     * 二分查找
     * 缺陷：1数组 2有序
     * 时间复杂度：O(log n) O(log2 n) 每次查的长度是之前的一半
     *
     * 用((right - left) >> 1) + left 而不用 （right + left) >> 1
     * 后者存在溢出可能，int是16位，最大的值为65535，
     * 假设你定义的数组是60000,low和high分别为30000，40000，
     * 两者相加就会溢出，变成一个负数 【留意溢出现象】
     *
     * @param arr 数组
     * @param target 目标值
     * @return int 返回位置
     */

    public static int binarySearchMethod(int[] arr, int target) {
        int right = arr.length -1 ;
        int left = 0;
        int c = 0;
        while (left <= right) {
            System.out.println("第"+ ++c +"次循环");
            //这里注意一下优先级，否则会造成死循环。
            //如果((right - left) >> 1)  不加（），会先算  1 + left
            int mid = ((right - left) >> 1) + left;
            System.out.println("mid： " + mid);
            if (arr[mid] > target) {
                System.out.println(arr[mid] +" > " +  target);
                right = mid - 1;
            } else if (arr[mid] == target) {
                return mid;
            } else {
                System.out.println(arr[mid] +" < " +  target);
                left = mid + 1;
            }

        }
        return -1;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8};
        System.out.println(binarySearchMethod(arr, 7));
    }
}
