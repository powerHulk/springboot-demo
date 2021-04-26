package circlehash;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: zhangth
 * @CreateDate: 2021/4/1
 */
public class ConsistentHash<T> {
    /**
     * Hash函数接口
     */
    private final HashService hashService;
    /**
     * 每个机器节点关联的虚拟节点数量
     */
    private final int numberOfReplicas;
    /**
     * 环形虚拟节点
     */
    private final SortedMap<Long, T> circle = new TreeMap<Long, T>();

    public ConsistentHash(HashService hashService, int numberOfReplicas, Collection<T> nodes) {
        this.hashService = hashService;
        this.numberOfReplicas = numberOfReplicas;
        for (T node : nodes) {
            add(node);
        }
    }

    /**
     * 增加真实机器节点
     *
     * @param node T
     */
    public void add(T node) {
        for (int i = 0; i < this.numberOfReplicas; i++) {
            circle.put(this.hashService.hash(node.toString() + i), node);
        }
    }

    /**
     * 删除真实机器节点
     *
     * @param node T
     */
    public void remove(T node) {
        for (int i = 0; i < this.numberOfReplicas; i++) {
            circle.remove(this.hashService.hash(node.toString() + i));
        }
    }

    public T get(String key) {
        if (circle.isEmpty()) {
            return null;
        }

        long hash = hashService.hash(key);

        // 沿环的顺时针找到一个虚拟节点
        if (!circle.containsKey(hash)) {
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }
}