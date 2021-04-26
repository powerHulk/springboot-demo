
要判断括号的有效性，左括号必须和右括号相对应。如果是有效括号，并且他们中间还有括号，那么他们必须也是有效的，所以最简单的一种方式就是使用栈来解决。

<br>

我们遍历字符串中的所有字符

**1，如果遇到了左括号，就把对应的右括号压栈（比如遇到了字符'('，就把字符')'压栈）。**

<br>

**2，如果遇到了右括号**

    1）查看栈是否为空，如果为空，说明不能构成有效的括号，直接返回false。

    2）如果栈不为空，栈顶元素出栈，然后判断出栈的这个元素是否等于这个右括号，如果不等于，说明不匹配，直接返回false。如果匹配，就继续判断字符串的下一个字符。

<br>

**3，最后如果栈为空，说明是完全匹配，是有效的括号，否则如果栈不为空，说明不完全匹配，不是有效的括号。**

<br>

这里随便举个例子比如字符串是“```{([]())}```”，画个图来看一下。

![image.png](https://pic.leetcode-cn.com/1610861314-hFmkuZ-image.png)


![image.png](https://pic.leetcode-cn.com/1610861319-suopRi-image.png)

代码如下

```java
public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    char[] chars = s.toCharArray();
    //遍历所有的元素
    for (char c : chars) {
        //如果是左括号，就把他们对应的右括号压栈
        if (c == '(') {
            stack.push(')');
        } else if (c == '{') {
            stack.push('}');
        } else if (c == '[') {
            stack.push(']');
        } else if (stack.isEmpty() || stack.pop() != c) {
            //否则就只能是右括号。
            //1，如果栈为空，说明括号无法匹配。
            //2，如果栈不为空，栈顶元素就要出栈，和这个右括号比较。
            //如果栈顶元素不等于这个右括号，说明无法匹配，
            //直接返回false。
            return false;
        }
    }
    //最后如果栈为空，说明完全匹配，是有效的括号。
    //否则不完全匹配，就不是有效的括号
    return stack.isEmpty();
}
```

<br>

我把部分算法题整理成了PDF文档，截止目前总共有**900多页**，大家可以下载阅读
**链接**：https://pan.baidu.com/s/1hjwK0ZeRxYGB8lIkbKuQgQ 
**提取码**：6666 

#### 如果觉得有用就给个赞吧，还可以关注我的[LeetCode主页](https://leetcode-cn.com/u/sdwwld/)查看更多的详细题解





