### 解题思路
本题解 借鉴了 **题解区**大佬 的思想：
> 1. dp[i][j]表示 从i到j的子串 是否 满足“回文特性”
> 2. 遍历所有长度的字符子串，若 当前子串：
>> 1. 第一个字符 和 最后一个字符 相等
>> 2. 子串长度<3 或 减去首尾俩字符的子串也满足回文特性

### 代码

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 0) {
            return s;
        }
        
        int length = s.length();
        int maxStartIndex = 0;
        int maxEndIndex = 0;
        int maxLength = 1;
        
        boolean[][] dp = new boolean[length][length];    // dp[i][j]表示 从i到j的子串 是否 满足“回文特性”
        for (int rightIndex = 1; rightIndex < length; rightIndex++) {
            for (int leftIndex = 0; leftIndex < rightIndex; leftIndex++) {
                /*
                    若 当前子串：
                        1、第一个字符 和 最后一个字符 相等
                        2、子串长度<3 或 减去首尾俩字符的子串也满足回文特性
                */
                if (s.charAt(leftIndex) == s.charAt(rightIndex) && (rightIndex - leftIndex <= 2 || dp[leftIndex + 1][rightIndex - 1])) {    // 利用 dp存储的“之前结果”，进行优化
                    dp[leftIndex][rightIndex] = true;
                    if (rightIndex - leftIndex + 1 > maxLength) {
                        maxLength = rightIndex - leftIndex + 1;
                        maxStartIndex = leftIndex;
                        maxEndIndex = rightIndex;
                    }
                }
                
            }
        }
        
        return s.substring(maxStartIndex, maxEndIndex + 1);
    }
}
```
打卡188天，加油！！！
前一段时间因为某学校老师的“大义凛然”，和舍友同学的“和善”，搞得整个人有些难受
那伙人天天念叨着考研考研，我之前不在宿舍，不知道他们有没有用心，最近有课设，那伙人基本没人在学习，看到我在看教程学习，还一个劲在旁边yygq
更离谱的是，其中一个还找他爸，让他爸拿公司项目给他和其它几个拿去参加 **三创** 么 **互联网+**竞赛
一伙人啥都不会搁那干瞪眼，看我回去还专门有话没话往那话题上引，显摆自己多能多厉害，想让我酸
u1s1虽然上学期和工作室同学们一起参加三创，虽然没拿上啥好奖，但好歹自己做了两个多月，项目拿去面试公司也没多大问题，真就没见过市面还觉得别人更没见过世面呗
我就寻思着：你丫一群人学术造假还理直气壮？结果我自己凭自己面到的公司不让去实习，一伙人“靠爹”去参加竞赛学校给一路绿灯？
人生不得意！

后来最近在看的小说，一下点醒了我
![image.png](https://pic.leetcode-cn.com/1617700674-wOJOZs-image.png)
有兴趣、有时间的同学可以闲了康康😉
其实感觉自己跟这部小说蛮搭的，之前北漂，看到男主正好去了“北俱芦洲”，返乡准备东西开学时看到男主回到故乡“落魄山”
之后又是这里的故事。。。
虽然没男主那么心思细腻，天资聪颖，运气好，但从这部小说上学到了很多为人处世的道理
假的东西再怎么装，终有露馅的一天
真的东西再怎么躲藏，也终有显世的一天

我相信 有这经历、怀疑世道人心 的同学应该不止我一个，
因此，在恢复过来的第一篇题解中，借助某1月番主角老师的一句话来激励有次类经历的诸位：
> 你要偷偷变强，然后吓死所有人

加油，别看别人，别听别人，跑起来，不远处就是桃园！