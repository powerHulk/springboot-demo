### 解题思路
分割字符串之后根据每种情况进行判定
`.`和``就不用管，直接跳过
`..`就代表着返回上一级，即弹出队尾元素（要注意判空）
其他情况直接压入队列就行。

**如果对linux比较熟悉的话这道题应该是秒杀的，Linux的目录层级就是用栈实现的**
只不过为了拼接方便这里使用了双端队列

### 代码

```java
class Solution {
    public String simplifyPath(String path) {
        // 双端队列
        Deque<String> queue = new LinkedList<>();
        // 分割字符
        String[] res = path.split("/");
        for(int i = 0; i < res.length; i++){
            String s = res[i];
            if(s.equals(".") || s.equals("")) continue;
            else if (s.equals("..")){
                if(!queue.isEmpty()){
                    queue.pollLast();
                }
            }else{
                queue.offer(s);
            }
        }
        // 拼接
        StringBuilder sb = new StringBuilder("/");
        while(!queue.isEmpty()){
            sb.append(queue.poll());
            if(!queue.isEmpty()){
                sb.append("/");
            }
        }
        // 判空
        return sb.toString().equals("") ? "/" : sb.toString();
    }
}
```