### 解题思路
如代码所示。

### 代码

```java
class Solution {
    public String simplifyPath(String path) {
        LinkedList<String> stack = new LinkedList<String>();//用栈来模拟
        String[] strArr = path.split("/");//用/分割，多个/也视为/
        for(String str: strArr){//遍历
            //如果等于空或者等于.，那就没有影响
            if(str.equals("") || str.equals(".")){
                continue;
            }
            //如果等于..，那就要返回上一级目录，因此栈中弹出当前目录
            //此时可能栈是空
            if(str.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
                continue;
            }
            //否则，栈中压入当前目录
            stack.push(str);
        }
        StringBuffer sb = new StringBuffer();
        Collections.reverse(stack);
        while(!stack.isEmpty()){
            String tmp = stack.pop();
            sb.append("/").append(tmp);
        }
        if(sb.length() == 0){
            sb.append("/");
        }
        String res = sb.toString();
        return res;
    }
}
```