### 解题思路
![QQ图片20210401090251.png](https://pic.leetcode-cn.com/1617239025-wEzWWl-QQ%E5%9B%BE%E7%89%8720210401090251.png)
此处撰写解题思路

### 代码

```cpp
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int n=nums.size();
        if(n==0)return NULL;
        int max=INT_MIN;
        for(int i=1;i<n;++i){
            if(nums[i-1]>0)nums[i]=nums[i-1]+nums[i];//状态转移方程
        }
        //遍历数组找到最大值
        for(int a=0;a<n;++a){
            if(nums[a]>max){
                max=nums[a];
            }
        }
        return max;
    }
};
```