### 解题思路
如注释所写。
十进制怎么加，二进制就怎么加。
注意字符的加减就是字符对应十进制数的加减，返回的也是int型数据
也要注意看最高位有没有进位，有的话要处理

### 代码

```java
class Solution {
    public String addBinary(String a, String b) {
        StringBuffer ssb = new StringBuffer();
        int i = a.length() - 1;
        int j = b.length() - 1;
        
        int carry = 0;  // 进位
        while(i >= 0 || j >= 0) {//从低位往高位开始计算，那么在String中就从后往前
            if(i >= 0) {
                carry += a.charAt(i) - '0'; //字符加减就是对应十进制数加减，返回int，'0'对应32，'1'对应33.因此'1'-'0' =                                              //33-32 = 1+0=1,'0'-'0' = 32-32 = 0+0=0,模拟的就是二进制加法
                i--;
            }
            if(j >= 0) {
                carry += b.charAt(j) - '0';
                j--;
            }
            ssb.append(carry % 2);//这么存放，那么索引由低到高就会是二进制的由低到高
            carry = carry/2;
        }
        String res = ssb.reverse().toString();//反过来才是二进制的由高到低
        if(carry == 1){
            return "1"+res;
        }
        else{
            return res;
        }
    }
}
```