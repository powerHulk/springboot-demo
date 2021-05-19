package circlehash;

import java.util.*;

/**
 * @author: zhangth
 * @CreateDate: 2021/5/19
 */
public class VirtualConsistencyHash {
    /**
     * 真实服务器节点信息
     */
    private static LinkedList<String> linkedList = new LinkedList<>();
    /**
     * 虚拟节点个数
     */
    private static int virtualNum = 3;
    /**
     * 存储虚拟节点、路由信息
     */
    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();
    /**
     * 服务器信息初始化
     */
    static {
        //初始化集群服务器节点
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        //按照虚拟规则添加虚拟节点
        for (int i = 0; i < linkedList.size(); i++) {
            for (int j = 0; j < virtualNum; j++) {
                String virtualKey = linkedList.get(i) + "_" + j;
                int index = getHashIndex(virtualKey);
                sortedMap.put(index, virtualKey);
            }
        }
    }

    /**
     * 按照请求key获取路由信息
     *
     * @param key
     * @return
     */
    public static String getRoute(String key) {
        int hashIndex = getHashIndex(key);
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hashIndex);
        if (subMap.isEmpty()) {
            int index = sortedMap.firstKey();
            String virtualKey = sortedMap.get(index);
            return doParse(virtualKey);
        } else {
            int index = subMap.firstKey();
            String virtualKey = subMap.get(index);
            return doParse(virtualKey);
        }
    }
    /**
     * 虚拟key解析
     *
     * @param virtualKey
     * @return
     */
    private static String doParse(String virtualKey) {
        int subIndex = virtualKey.indexOf("_");
        return virtualKey.substring(0, subIndex);
    }
    /**
     * 计算hash
     *
     * @param key
     * @return
     */
    private static int getHashIndex(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> keys = new ArrayList<>();
        keys.add("北京");
        keys.add("上海");
        keys.add("广州");
        keys.add("深圳");
        keys.add("杭州");
        keys.add("武汉");
        keys.add("重庆");
        keys.add("成都");
        keys.add("济南");
        keys.add("青岛");
        keys.add("西安");
        keys.add("厦门");

        for (int i = 0; i < keys.size(); i++) {
            String res = VirtualConsistencyHash.getRoute(keys.get(i));
            System.out.println(String.format("请求key[%s],获取路由信息[%s]", keys.get(i), res));
        }
    }
}