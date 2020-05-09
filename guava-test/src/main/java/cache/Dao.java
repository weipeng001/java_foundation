package cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Dao {
    private Cache<String, List<String>> poiCache = CacheBuilder.newBuilder().build();

    @SuppressWarnings("unchecked")
    public List<String> getList(final String cityId) {
        List returnList = null;
        try {
            returnList = poiCache.get(cityId, () -> getListFromDb(cityId));
        } catch (ExecutionException e) {
            // 记日志
        }
        return returnList;
    }

    @SuppressWarnings("unchecked")
    private List<String> getListFromDb(String cityId){
//      多线程变更数据的时候   需要自己实现缓存的同步。
        System.out.println("getting from DB, please wait...");
        List<String> returnList = Lists.newArrayList();
        // 模拟从数据库中取数据
        try {
            Thread.sleep(1000);
            switch (cityId){
                case "0101" :
                    returnList = ImmutableList.of("北京", "上海", "广州", "深圳");
                    break;
                case "0102" :
                    returnList = ImmutableList.of("a", "b", "c", "d");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            // 记日志
        }
        return returnList;
    }
}
