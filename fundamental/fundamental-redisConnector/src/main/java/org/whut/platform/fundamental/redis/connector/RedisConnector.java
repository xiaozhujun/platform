package org.whut.platform.fundamental.redis.connector;


import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnector {

    private static PlatformLogger logger = PlatformLogger.getLogger(RedisConnector.class);

    private static int expire;
    private static JedisPool pool;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(Integer.valueOf(FundamentalConfigProvider.get("redis.pool.maxActive")));
        config.setMaxIdle(Integer.valueOf(FundamentalConfigProvider.get("redis.pool.maxIdle")));
        config.setMaxWait(Long.valueOf(FundamentalConfigProvider.get("redis.pool.maxWait")));
        config.setTestOnBorrow(Boolean.valueOf(FundamentalConfigProvider.get("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(FundamentalConfigProvider.get("redis.pool.testOnReturn")));
        pool = new JedisPool(config, FundamentalConfigProvider.get("redis.ip"), Integer.valueOf(FundamentalConfigProvider.get("redis.port")));
        logger.info("redis.ip: "+FundamentalConfigProvider.get("redis.ip"));
        expire = Integer.valueOf(FundamentalConfigProvider.get("redis.expire"));
    }

    public RedisConnector() {}

    public int getExpire() {
        return expire;
    }

    public boolean set(String key,String value){
        Jedis jedis = pool.getResource();
        try{
            String result = jedis.set(key,value);
            if (result.equals("OK")){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pool.returnResource(jedis);
        }
        return false;
    }

    public boolean set(String key,int expire,String value){
        Jedis jedis = pool.getResource();
        try{
            String result = jedis.setex(key,expire,value);
            if (result.equals("OK")){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pool.returnResource(jedis);
        }
        return false;
    }

    public String get(String key){
        Jedis jedis = pool.getResource();
        String result = null;
        try{
            result = jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pool.returnResource(jedis);
        }
        return result;
    }

}
