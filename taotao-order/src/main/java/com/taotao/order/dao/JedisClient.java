package com.taotao.order.dao;
/***
 * Jedis 接口
 * @author Administrator
 *
 */
public interface JedisClient {

	String set(String key, String value);
	String get(String key);
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
	long expire(String key, int second);
	long incr(String key);
	long ttl(String key);
	long del(String key);
	long hdel(String hkey, String key);
}
