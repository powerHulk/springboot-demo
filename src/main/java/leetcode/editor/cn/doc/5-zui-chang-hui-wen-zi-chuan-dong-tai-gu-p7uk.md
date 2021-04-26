### 解题思路
学习weiwei思路，很快就理解了，这里写下我的总结


动态规划：填dp表、当前ij状态、过去ij状态、如何联合得到输出、边界条件

1. 定义状态：题目让我们求什么，就把什么设置为状态
题目求s中最长的回文子串，那就判断所有子串是否为回文子串，选出最长的
因此：dp[i][j]表示s[i:j+1]是否为回文子串（这里+1是为了构造闭区间）

2. 状态转移方程：对空间进行分类讨论（当前ij状态、过去ij状态 如何联合得到输出）
当前ij状态：头尾必须相等（s[i]==s[j]）
过去ij状态：去掉头尾之后还是一个回文（dp[i+1][j-1] is True）
边界条件：只要是找过去ij状态的时候，就会涉及边界条件（即超出边界情况处理）
    当i==j时一定是回文
    j-1-(i+1)<=0,即j-i<=2时，只要当s[i]==s[j]时就是回文，不用判断dp[i+1][j-1]
dp[i][j] 为截取的子串

3. 初始状态：这里已经直接判断j-i<=2的情况了，因此用不到初始状态，可以不设
4. 输出内容：每次发现新回文都比较一下长度，记录i与长度
5. 优化空间提速

例子
![image.png](https://pic.leetcode-cn.com/1615968357-TrNoqw-image.png)


### 代码

```python3
class Solution:
    def longestPalindrome(self, s: str) -> str:
        
        size = len(s)
        # 特殊处理
        if size == 1:
            return s
        # 创建动态规划dynamic programing表
        dp = [[False for _ in range(size)] for _ in range(size)]
        # 初始长度为1，这样万一不存在回文，就返回第一个值（初始条件设置的时候一定要考虑输出）
        max_len = 1
        start = 0
        for j in range(1,size):
            for i in range(j):
                # 边界条件：
                # 只要头尾相等（s[i]==s[j]）就能返回True
                if j-i<=2:
                    if s[i]==s[j]:
                        dp[i][j] = True
                        cur_len = j-i+1
                # 状态转移方程 
                # 当前dp[i][j]状态：头尾相等（s[i]==s[j]）
                # 过去dp[i][j]状态：去掉头尾之后还是一个回文（dp[i+1][j-1] is True）
                else:
                    if s[i]==s[j] and dp[i+1][j-1]:
                        dp[i][j] = True
                        cur_len = j-i+1
                # 出现回文更新输出
                if dp[i][j]:
                    if cur_len > max_len:
                        max_len = cur_len
                        start = i

        return s[start:start+max_len]
```