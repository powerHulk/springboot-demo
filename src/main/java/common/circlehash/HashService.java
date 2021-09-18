package common.circlehash;

/**
 * @author: zhangth
 * @CreateDate: 2021/4/1
 */
public interface HashService {
    /**
     * hash
     * @param key
     * @return
     */
    Long hash(String key);
}
