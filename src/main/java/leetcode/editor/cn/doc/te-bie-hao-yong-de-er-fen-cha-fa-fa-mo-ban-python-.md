这里强烈推荐一下，如果您觉得二分查找算法一直写不对，对于边界情况和细节弄不太清楚，这篇题解介绍的 **二分查找思路 2**（不断排除不存在解的区间，直至最后剩下一个）是绝对有用的，我看很多大神都用这个思路做二分的问题。

**重点**：初学的时候可能觉得不太习惯，因此 **一定需要配合练习（题解最后有练习题）和适当的思考、调试**，才能做到思路清晰、融会贯通（几乎所有的基础算法问题都是这样）。

---

在这里为大家归纳出最重要的部分：

+ 分析题意，挖掘题目中隐含的 **单调性**；
+ `while (left < right)` 退出循环的时候有 `left == right` 成立，因此无需考虑返回 `left` 还是 `right`；
+ 始终思考下一轮搜索区间是什么，如果是 `[mid, right]` 就对应 `left = mid` ，如果是 `[left, mid - 1]` 就对应 `right = mid - 1`，是保留 `mid` 还是 *+1*、*-1* 就在这样的思考中完成；
+ **从一个元素什么时候不是解开始考虑下一轮搜索区间是什么** ，把区间分为 *2* 个部分（一个部分肯定不存在目标元素，另一个部分有可能存在目标元素），问题会变得简单很多，这是一条 **非常有用** 的经验；
+ 每一轮区间被划分成 *2* 部分，理解 **区间划分** 决定中间数取法（ **无需记忆，需要练习 + 理解** ），在调试的过程中理解 **区间和中间数划分的配对关系**：
  + 划分 `[left, mid]` 与 `[mid + 1, right]` ，`mid` 被分到左边，对应 `int mid = left + (right - left) / 2;`；
  + 划分 `[left, mid - 1]` 与 `[mid, right]` ，`mid` 被分到右边，对应 `int mid = left + (right - left + 1) / 2;`。
>至于为什么划分是这种对应关系，原因在于区间只有 *2* 个数的时候，如果中间数的取法不对，**一旦进入的分支不能使得区间缩小**，会出现 **死循环**。暂时不理解问题不大，需要在练习中进行调试；
+ 退出循环的时候有 `left == right` 成立，此时如果能确定问题一定有解，返回 `left` 即可，如果不能确定，需要单独判断一次。 

提示：上面的文字看着内容多，但是 **经过了必要的练习、思考和调试的基础上，再回头看这一套逻辑就会感觉非常简单，完全不用记忆，二分查找没有必要套模板，也不能套模板**。

liuyubobobo 老师的公众号「是不是很酷」里有一篇推文 [《模板不重要》](https://mp.weixin.qq.com/s/d5Af7YwwrtdV_OqYzcWGSw) ，这里也推荐给大家。模板不重要，深刻理解二分查找的思想，加上**必要的练习**和总结，才能轻松应对二分查找的问题。核心思想帮大家归纳一下：**二分查找就几行代码，还远远达不到要套模板的地步，模板思维是学好算法的误区** 。

2020 年 8 月 24 日（星期一）补充

---

<br>

## 生活中的二分查找

以前央视有一档节目叫《幸运 52》，里面有个游戏「猜价格」，主持人说猜高了，观众就往低了猜，主持人说猜低了，观众就往高了猜，直至猜中为止。

程序员定位 bug，经常是在一些逻辑关键点做一些变量的打印输出，以**逐渐缩小查找范围**，最终定位出问题的代码行（或者块）。

## 二分查找的核心思想

二分查找的核心思想是「减而治之」，即「不断缩小问题规模」。

## 二分查找的两种思路（请特别留意第 2 种思路，掌握它能思路清晰地解决「力扣」上的所有二分查找问题）

### 思路 1：在循环体内部查找元素

+ `while(left <= right)` 这种写法表示在循环体内部直接查找元素；
+ 退出循环的时候 `left` 和 `right` 不重合，区间 `[left, right]` 是空区间。

![](https://pic.leetcode-cn.com/1598340841-leuSyu-file_1598340837977)

### 思路 2：在循环体内部排除元素（重点）

+ `while(left < right)` 这种写法表示在循环体内部排除元素；
+ 退出循环的时候 `left` 和 `right` 重合，区间 `[left, right]` 只剩下成 *1* 个元素，这个元素 **有可能** 就是我们要找的元素。

![](https://pic.leetcode-cn.com/1598340841-tpXMfu-file_1598340837988)

第 2 种思路可以归纳为「左右边界向中间走，两边夹」，这种思路在解决复杂问题的时候，可以使得思考的过程变得简单。

## 使用思路 2 完成「力扣」第 35 题

**思路分析**：

+ 首先，插入位置有可能在数组的末尾（题目中的示例 3），需要单独判断。如果在数组的末尾，插入位置的下标就是数组的长度；
+ 否则，根据示例和暴力解法的分析，插入位置的下标是 **大于等于** `target` 的第 *1* 个元素的位置。

因此，**严格小于 `target` 的元素一定不是解**，在循环体中将左右边界 `left` 和 `right` 逐渐向中间靠拢，最后 `left` 和 `right` 相遇，则找到了插入元素的位置。根据这个思路，可以写出如下代码。

**参考代码 1**：

```Java []
public class Solution {

    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        // 特判
        if (nums[len - 1] < target) {
            return len;
        }
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 严格小于 target 的元素一定不是解
            if (nums[mid] < target) {
                // 下一轮搜索区间是 [mid + 1, right]
                left = mid + 1;
            } else {
                // 下一轮搜索区间是 [left, mid]
                right = mid;
            }
        }
        return left;
    }
}
```
```Python []
from typing import List


class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        size = len(nums)
        if size == 0:
            return 0

        # 特判
        if nums[size - 1] < target:
            return size

        left = 0
        right = size - 1

        while left < right:
            # left + right 在 Python 中如果发生整型溢出，Python 会自动转成长整形
            mid = (left + right) // 2
            # 严格小于 target 的元素一定不是解
            if nums[mid] < target:
                # 下一轮搜索区间是 [mid + 1, right]
                left = mid + 1
            else:
                # 下一轮搜索区间是 [left, mid]
                right = mid
        return left
```
```C++ []
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int searchInsert(vector<int> &nums, int target) {
        int size = nums.size();
        if (size == 0) {
            return 0;
        }

        // 特判
        if (nums[size - 1] < target) {
            return size;
        }
        int left = 0;
        int right = size - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 严格小于 target 的元素一定不是解
            if (nums[mid] < target) {
                // 下一轮搜索区间是 [mid + 1, right]
                left = mid + 1;
            } else {
                // 下一轮搜索区间是 [left, mid] 
                right = mid;
            }
        }
        return left;
    }
};
```

**复杂度分析**：

+ 时间复杂度：![O(\logN) ](./p__O_log_N__.png) ，这里 *N* 是数组的长度，每一次都将问题的规模缩减为原来的一半，因此时间复杂度是对数级别的；
+ 空间复杂度：*O(1)*，使用到常数个临时变量。

由于插入元素的位置可能在数组的末尾下标的下一个（见例 3），因此在初始化右边界 `right` 的时候，可以设置成为数组的长度 `len`。代码还可以这样写：

**参考代码 2**：

```Java []
public class Solution {

    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        
        int left = 0;
        // 因为有可能数组的最后一个元素的位置的下一个是我们要找的，故右边界是 len
        int right = len;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 小于 target 的元素一定不是解
            if (nums[mid] < target) {
                // 下一轮搜索的区间是 [mid + 1, right]
                left = mid + 1;
            } else {
              	// 下一轮搜索的区间是 [left, mid]
                right = mid;
            }
        }
        return left;
    }
}
```
```Python []
from typing import List


class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        size = len(nums)
        if size == 0:
            return 0

        left = 0
        # 因为有可能数组的最后一个元素的位置的下一个是我们要找的，故右边界是 len
        right = size
        while left < right:
            # left + right 在 Python 中如果发生整型溢出，Python 会自动转成长整形
            mid = (left + right) // 2
            # 严格小于 target 的元素一定不是解
            if nums[mid] < target:
                # 下一轮搜索区间是 [mid + 1, right]
                left = mid + 1
            else:
                right = mid
        return left
```
```C++ []
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int searchInsert(vector<int> &nums, int target) {
        int size = nums.size();
        if (size == 0) {
            return 0;
        }

        int left = 0;
        // 因为有可能数组的最后一个元素的位置的下一个是我们要找的，故右边界是 len
        int right = size;

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 小于 target 的元素一定不是解
            if (nums[mid] < target) {
                // 下一轮搜索的区间是 [mid + 1, right]
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
};
```

**复杂度分析**：（同上）

---

（很抱歉，先进一段广告，我保证这个广告在我的题解里占比非常非常少，我会控制在 ![3\% ](./p__3_%_.png)  以下。）

欢迎各位阅读我的 LeetBook：[**使用「力扣」学习算法与数据结构**](https://leetcode-cn.com/leetbook/detail/learning-algorithms-with-leetcode/) 里 《二分查找》 这一章节。我在这一部分里 **详细讲解了二分查找两种思路的所有细节，包括什么时候取中间数需要上取整，为什么是根据分支逻辑确定中间数的取法等细节** ，因此在这篇题解里就不再赘述了。

该 LeetBook 的前两章（ [时间复杂度](https://leetcode-cn.com/leetbook/read/learning-algorithms-with-leetcode/553v4h/)、[二分查找（推荐看一下，免费）](https://leetcode-cn.com/leetbook/read/learning-algorithms-with-leetcode/xsq0b7/)）是免费阅读的，后面的章节 **需要付费** 观看，**中、高阶用户请谨慎购买**。

[![使用「力扣」学习算法与数据结构](https://pic.leetcode-cn.com/061653aa002f6e95a58cc182080b645e9437a7bf9ab1bb779f29f1a6e17c56c7-image.png)

**说明**：
+ 可以在站内或者是我的其他社交账号向我咨询课程内容。不管是否购买课程，我都会尽量回答我所知道的问题（时间允许，能力范围之内）。感谢大家一直以来，一如既往对我的支持。有建议和意见也欢迎大家与我交流。

（广告结束）

---


## 为什么总是写不对「二分查找」

做了很多二分查找的问题以后，我的感受是：二分查找不难，更多时候在考察我们是否细心。如果面试中遇到，是一定要拿下的。弄不清楚边界条件，**有可能是在背模板**，没有搞清楚二分查找的思想和里面非常细节的地方。

+ 我的经验是把区间定义成为：左闭右闭区间，两个变量向中间走，定义的左右边界理应是无差别的，**定义成左闭右开，反而增加了解决问题的的复杂程度**，因为还要去考虑右边界是否能够取到，即使定义成左闭右开区间 `[left, right)` 也一定对应一个等价的左闭右闭区间 `[left, right - 1]`；

> 友情提示：这里所说的二分查找的左闭右闭区间的具体定义如下：**在区间 `[left, right]` 里可能存在目标元素**，在二分搜索的过程中需要保持这个定义。而「滑动窗口」问题，习惯性定义成左闭右开 `[left, right)`，这是因为滑动窗口问题的特点是，右指针先向右移动，左指针再向右移动，右指针主动，左指针被动，因此地位是不一样的，滑动窗口问题定义成左闭右开的好处有 2 点。第一，初始化的时候 `left = 0` 和 `right = 0` 这是相对自然的，第二，滑动窗口的长度为 `right - left` 不用加 *1*。

+ 明确 `int = left + (right - left) / 2` 这里除以 *2* 是下取整（想一想上取整可以吗，什么时候需要上取整，为什么是除以 *2* ，其它整数行不行）；
+ 明确 `while(left <= right)` 和 `while(left < right)` 这两种写法在思路上有本质差别，里面的逻辑该怎么写，应该做到彻底理解； 
+ 始终在思考下一轮搜索区间是什么，这一点对应上面所说的：「始终将区间认为是 **左闭右闭** 区间」，这样就能帮助我们搞清楚边界是不是能取到，等于、*+1* 、*-1* 之类的细节；
+ 思考清楚每一行代码背后的语义是什么，保证语义上清晰，也是写对代码，减少 bug 的一个非常有效的方法；
+ 不能忽视一个很重要的方法是 **调试**。它不是什么很高超的技巧，就是把变量的值打印出来看一下，相信就能解决为什么写出的代码会造成死循环的问题，并得出解决方案。

写对二分查找算法是基于我们的思考，并且认真对待遇到的二分查找问题，**通过挖掘单调性，使得问题规模不断缩小** 。在下面的视频题解里，我非常具体地讲解了我是怎么分析和做二分查找问题的，希望能够对大家有所帮助。

+ 第 1095 题：[官方题解](https://leetcode-cn.com/problems/find-in-mountain-array/solution/shan-mai-shu-zu-zhong-cha-zhao-mu-biao-zhi-by-leet/)；
+ 第 4 题（非常重要、典型的二分查找问题）：[官方题解](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/)。

## 二分查找的三种题型（下面给出的问题，一定要有自己的思考和调试，在面对一个新的问题的时候，才会有正确的思路）

+ 在一个数组里查找一个数，简称为「二分下标」；
+ **查找一个有范围的整数**，简称为「二分答案」，这部分题目还挺常见；
+ 通过一个变量与另一个变量的相关关系，进而确定目标变量的值，统称为「复杂问题」，通常这一类问题的判别函数需要遍历整个数组。

## 练习

友情提示：这些问题都不应该当做模板问题来看待，逻辑严密的分析与思考是更关键的。

### 题型一：在数组中查找符合条件的元素的下标

一般而言这个数组是有序的，也可能是半有序的（旋转有序数组或者山脉数组）。


| 题目                                                         | 提示与题解                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [704. 二分查找（简单）](https://leetcode-cn.com/problems/binary-search/) | 二分查找的最原始问题，使用本题解介绍的方法就要注意，需要后处理。 |
| [34. 在排序数组中查找元素的第一个和最后一个位置（中等）](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | 查找边界问题，[题解（有视频讲解）](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/si-lu-hen-jian-dan-xi-jie-fei-mo-gui-de-er-fen-cha/)。 |
| [33. 搜索旋转排序数组（中等）](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/) | [题解](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/er-fen-fa-python-dai-ma-java-dai-ma-by-liweiwei141/)，利用局部单调性，逐步缩小搜索区间（其它问题类似）。 |
| [81. 搜索旋转排序数组 II（中等）](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/) | [题解](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/solution/er-fen-cha-zhao-by-liweiwei1419/) |
| [153. 寻找旋转排序数组中的最小值（中等）](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/) | [题解](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/er-fen-fa-fen-zhi-fa-python-dai-ma-java-dai-ma-by-/) |
| [154. 寻找旋转排序数组中的最小值 II（中等）](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/) | [题解](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/er-fen-fa-fen-zhi-fa-python-dai-ma-by-liweiwei1419/) |
| [300. 最长上升子序列（中等）](https://leetcode-cn.com/problems/longest-increasing-subsequence/) | 特别经典的一道「动态规划」，二分查找的思路是基于「动态规划」的状态定义得到，代码很像第 35 题，[题解](https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/)。 |
| [275. H指数 II（中等）](https://leetcode-cn.com/problems/h-index-ii/) | [题解](https://leetcode-cn.com/problems/h-index-ii/solution/jian-er-zhi-zhi-er-fen-cha-zhao-by-liweiwei1419-2/) ，这个问题题目描述比较让人迷惑，可以跳过不做|
| [852. 山脉数组的峰顶索引（简单）](https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/) | 利用局部单调性，逐步缩小搜索区间。                                                             |
| [1095. 山脉数组中查找目标值（中等）](https://leetcode-cn.com/problems/find-in-mountain-array/) | [官方题解（有视频讲解）](https://leetcode-cn.com/problems/find-in-mountain-array/solution/shan-mai-shu-zu-zhong-cha-zhao-mu-biao-zhi-by-leet/)，[题解](https://leetcode-cn.com/problems/find-in-mountain-array/solution/shi-yong-chao-hao-yong-de-er-fen-fa-mo-ban-python-/) |
| [4. 寻找两个有序数组的中位数（困难）](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/) | [官方题解（有视频讲解）](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/)，[题解](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/he-bing-yi-hou-zhao-gui-bing-guo-cheng-zhong-zhao-/) |
| [658. 找到 K 个最接近的元素（中等）](https://leetcode-cn.com/problems/find-k-closest-elements/) | [题解](https://leetcode-cn.com/problems/find-k-closest-elements/solution/pai-chu-fa-shuang-zhi-zhen-er-fen-fa-python-dai-ma/)，这个问题二分的写法需要做复杂的分类讨论，可以放在以后做。 |

### 题型二：在一个有范围的区间里搜索一个整数

定位一个有范围的整数，这件事情也叫「二分答案」或者叫「二分结果」。如果题目要求的是一个整数，这个整数有明确的范围，可以考虑使用二分查找。

| 题目                                                         | 提示与题解                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [69. 平方根（简单）](https://leetcode-cn.com/problems/sqrtx/) | [题解](https://leetcode-cn.com/problems/sqrtx/solution/er-fen-cha-zhao-niu-dun-fa-python-dai-ma-by-liweiw/)，在一个整数范围里查找一个整数，也是二分查找法的应用场景。 |
| [287. 寻找重复数（中等）](https://leetcode-cn.com/problems/find-the-duplicate-number/) | [题解](https://leetcode-cn.com/problems/find-the-duplicate-number/solution/er-fen-fa-si-lu-ji-dai-ma-python-by-liweiwei1419/)，在一个整数范围里查找一个整数。这个问题二分查找的解法很反常规，知道即可。 |
| [374. 猜数字大小（简单）](https://leetcode-cn.com/problems/guess-number-higher-or-lower/) | [题解](https://leetcode-cn.com/problems/guess-number-higher-or-lower/solution/shi-fen-hao-yong-de-er-fen-cha-zhao-fa-mo-ban-pyth/) |
| [1300. 转变数组后最接近目标值的数组和](https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target/) | [题解](https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target/solution/er-fen-cha-zhao-by-liweiwei1419-2/) |

### 题型三：复杂的二分查找问题（判别条件需要遍历数组）

「力扣」上还有这样一类问题：**目标变量和另一个变量有相关关系（一般而言是线性关系），目标变量的性质不好推测，但是另一个变量的性质相对容易推测（满足某种意义上的单调性）**。这样的问题的判别函数通常会写成一个函数的形式。

这一类问题可以统称为「 **最大值极小化** 」问题，最原始的问题场景是木棍切割问题，这道题的原始问题是「力扣」第 410 题。

解题的思路是这样的：

+ 分析出题目要我们找一个整数，这个整数有范围，所以可以用二分查找；
+ 分析出**单调性**，一般来说是一个变量 `a` 的值大了，另一个变量 `b` 的值就变小，而「另一个变量的值」 `b` 有限制，因此可以通过调整 `a` 的值达到控制 `b` 的效果；
+ 这一类问题的题目条件一定会给出 **连续**、**正整数** 这样的关键字，如果没有，问题场景也一定蕴含了这两个关键信息。

以下给出的问题无一例外。

| 题目                                                         | 提示与题解                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [410. 分割数组的最大值（困难）](https://leetcode-cn.com/problems/split-array-largest-sum/) | [题解](https://leetcode-cn.com/problems/split-array-largest-sum/solution/er-fen-cha-zhao-by-liweiwei1419-4/) |
| [875. 爱吃香蕉的珂珂（中等）](https://leetcode-cn.com/problems/koko-eating-bananas/) | [题解](https://leetcode-cn.com/problems/koko-eating-bananas/solution/er-fen-cha-zhao-ding-wei-su-du-by-liweiwei1419/) |
| [LCP 12. 小张刷题计划（中等）](https://leetcode-cn.com/problems/xiao-zhang-shua-ti-ji-hua/) | （题解在第 410 题题解里）                                    |
| [1482. 制作 m 束花所需的最少天数（中等）](https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/) | （题解在第 1300 题题解里）                                   |
| [1552. 两球之间的磁力（中等）](https://leetcode-cn.com/problems/magnetic-force-between-two-balls/) |                                                              |

## 参考资料

+ 李煜东 著《算法竞赛进阶指南》（河南电子音像出版社）第 0x04 章《二分》
+ 胡凡、曾磊 主编 《算法笔记》（机械工业出版社）第 4.5 节《二分》

