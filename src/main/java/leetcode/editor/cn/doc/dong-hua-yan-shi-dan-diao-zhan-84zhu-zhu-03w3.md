# 题目描述

给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

 
![栈84图1.png](https://pic.leetcode-cn.com/1614496002-nKOhmB-%E6%A0%8884%E5%9B%BE1.png)


以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。

 
![栈84图2.png](https://pic.leetcode-cn.com/1614496014-cRnhwX-%E6%A0%8884%E5%9B%BE2.png)



图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

## 示例:

输入: [2,1,5,6,2,3]
输出: 10

<br>
# 题目分析

柱子彼此相邻，宽度都为1，高度各不相同。对于当前考察的柱子来说，它所能勾勒出的矩形面积是由其左边柱子和右边柱子的高度共同决定的。

如果其左边柱子的高度大于等于当前柱子的高度，则可以向左侧扩张，直到左边柱子高度小于当前柱子高度或左边再无其它柱子；同样的，如果其右边柱子的高度大于等于当前柱子的高度，则可以向右侧扩张，直到右边柱子高度小于等于当前柱子高度或右边再无其它柱子。

这时，就可以计算出以当前柱子高度为高，左右柱子间距离为宽的矩形面积。

<br>
# 解法一：暴力解法

暴力解法的基本思路是，对于当前考察的矩形来说，分别向左和向右拓展其边界，直到边界索引对应的矩形高度小于当前考察的矩形的高度，详细注释参考如下代码实现：

```java
    public int largestRectangleArea(int[] heights) {
        // 定义最终结果初始值为0
        int res = 0;

        for(int i = 0; i < heights.length; i++) {
            // 当前所考察矩形的高度
            int curHeight = heights[i];

            // 当前考察矩形的左边界索引，初始值为当前索引
            int leftIndex = i;
            // 如果左边界索引存在且左边界索引对应的矩形高度大于等于当前索引对应的矩形高度
            // 表明左侧面积还不能确定，因此左边界索引左移一位
            while (leftIndex -1 >= 0 && heights[leftIndex - 1] >= curHeight) {
                leftIndex--;
            }

            // 当前考察矩形的右边界索引，初始值为当前索引值
            int rightIndex = i;
            // 如果右边界索引存在且右边界索引对应的矩形高度大于等于当前索引对应的矩形高度
            // 表明右侧面积还不能确定，因此右边界索引右移一位
            while (rightIndex + 1 < heights.length && heights[rightIndex + 1] >= curHeight) {
                rightIndex++;
            }

            // 计算以当前矩形高度为高所能确定的矩形面积，和已记录面积比较，取最大值
            res = Math.max(res, (rightIndex - leftIndex +1)*curHeight);
        }
        
        return res;
    }
```

将暴力解法提交后，显示超时。

<br>
# 解法二：单调栈

# 思路分析

暴力解法中，对于当前考察的柱子，需要分别向左侧和右侧遍历，已确定其是否可以向左右扩张。那么，可不可以在一次遍历中就确定其左右边界呢？

对于输入[2,1,5,6,2,3]来说，在考察第一个柱子时，由于不知道其是否可以继续向右侧扩张，所以可将其先记录下来，然后考察下一个柱子。

在考察到下一个柱子时，发现其值1小于之前记录的柱子高度2。因此，这时可以确定第一个柱子所能勾勒出的矩形的面积。在确定其面积后，之前的记录中就可以将第一个柱子删除了。动画演示如下：

 [栈84动画1.gif](https://pic.leetcode-cn.com/1614500454-sMxHAu-%E6%A0%8884%E5%8A%A8%E7%94%BB1.gif)

<br>

接着，对于第二个柱子1来说，同样需要先将其记录下来。

![84图解步骤.001.jpeg](https://pic.leetcode-cn.com/1615113681-kWpLOL-84%E5%9B%BE%E8%A7%A3%E6%AD%A5%E9%AA%A4.001.jpeg)


然后考察第三个柱子5，这时柱子5的高度大于柱子1的高度。因此，它们所勾勒出的矩形是什么样依旧不能确定，将第三个柱子先记录下来。

![84图解步骤.003.jpeg](https://pic.leetcode-cn.com/1615113697-NPXMDe-84%E5%9B%BE%E8%A7%A3%E6%AD%A5%E9%AA%A4.003.jpeg)


继续考察第四个柱子。

同样的，对于第四个柱子6来说，它的高度大于已经记录的二、三两个柱子的高度1和5。因此，这三个柱子所能勾勒出的矩形面积依旧不能确定，将第四个柱子先做记录。

![84图解步骤.005.jpeg](https://pic.leetcode-cn.com/1615113708-uQWUub-84%E5%9B%BE%E8%A7%A3%E6%AD%A5%E9%AA%A4.005.jpeg)


继续考察第五个柱子。

这时第五个柱子的高度为2，小于其前一个柱子的高度6。因此，到这里，我们可以确定以6为高的柱子所能勾勒出的矩形面积。

![84图解步骤.006.jpeg](https://pic.leetcode-cn.com/1615113880-jVgkru-84%E5%9B%BE%E8%A7%A3%E6%AD%A5%E9%AA%A4.006.jpeg)


由于，之前记录的柱子高度是逐渐递增的，所以其左侧边界可以确定；同时，由于当前考察的柱子高度为2小于6，所以对于以6为高的柱子来说，其右侧边界也确定了。这样，它所能勾勒出的矩形面积就确定了。之后，将其从记录列表移除就可以。动画演示如下：

 [栈84动画演示2.gif](https://pic.leetcode-cn.com/1614500546-SgElus-%E6%A0%8884%E5%8A%A8%E7%94%BB%E6%BC%94%E7%A4%BA2.gif)


在以6为高的柱子从记录列表中移除之后，接着可以确定的就是以5为高的柱子所勾勒出的矩形面积。同样的，计算之后，需要将其从列表中移除。动画演示如下：

 [栈84动画演示3.gif](https://pic.leetcode-cn.com/1614500940-QQtHro-%E6%A0%8884%E5%8A%A8%E7%94%BB%E6%BC%94%E7%A4%BA3.gif)



分析到这里，我们发现在记录哪些柱子是暂时不能确定以其高为矩形的面积时，是从左到右放入记录列表的；当可以确定以其高为矩形的面积时，是从右到左移除列表的。这个特性不正符合栈的性质吗？先进后出，后进先出。

因此，该题目用栈求解的思路是：

1.先将题目给定的数组左右各添加一个元素0，为了方便确定原有数组中第一个元素和最后一个元素能不能继续扩张；

2.然后开始从左到右依次遍历数组中的元素：

3-1.如果栈为空或者当前考察的新元素值比栈顶元素值大，表明以栈顶元素值为高的矩形面积暂不能确定，所以就将当前考察的新元素入栈。在这个条件下，栈中的元素值从栈底到栈顶是依次递增的；

3-2.如果栈不为空且当前考察的新元素值比栈顶元素值小，表明以栈顶元素值为高的矩形的面积是可以确定的了。该矩形的高就是栈顶元素值，其右侧边界就是当前考察的新元素，左侧边界是栈顶元素的前一个元素，因为，在上一步中我们知道栈中元素值从栈底到栈顶是依次递增的。 因此，矩形的宽是当前考察的元素索引与栈顶元素前一个元素的索引的差值减一。

这里需要注意的是，当栈顶元素出栈后，需要继续看当前考察的新元素值是否大于新的栈顶元素值，如果是，就继续将栈顶元素弹出，然后计算以其值为高的矩形面积，直到当前考察的新元素值大于栈顶元素值时，当前考察元素入栈。


最后，由于最终计算矩形面积时，是用两个柱子的索引来确定矩形宽度的。因此，栈中存储的应该是给定数组的索引。

<br>

# 代码实现
```java
public int largestRectangleArea(int[] heights) {
        // 初始化最终结果为0
        int res = 0;
        Stack<Integer> stack = new Stack<>();

        // 将给定的原数组左右各添加一个元素0
        int[] newHeights = new int[heights.length + 2];
        newHeights[0] = 0;
        newHeights[newHeights.length-1] = 0;
        for (int i = 1; i < heights.length + 1; i++) {
            newHeights[i] = heights[i - 1];
        }

        // 开始遍历
        for (int i = 0; i < newHeights.length; i++) {
            // 如果栈不为空且当前考察的元素值小于栈顶元素值，
            // 则表示以栈顶元素值为高的矩形面积可以确定
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                // 弹出栈顶元素
                int cur = stack.pop();
                // 获取栈顶元素对应的高
                int curHeight = newHeights[cur];

                // 栈顶元素弹出后，新的栈顶元素就是其左侧边界
                int leftIndex = stack.peek();
                // 右侧边界是当前考察的索引
                int rightIndex = i;
                // 计算矩形宽度
                int curWidth = rightIndex - leftIndex - 1;

                // 计算面积
                res = Math.max(res, curWidth * curHeight);
            }
            
            // 当前考察索引入栈
            stack.push(i);
        }

        return res;
    }
```

<br>

# 动画演示

![栈84完整动画.mov](14a41333-74fc-421a-b358-8acebbe8b02b)
