package com.taotao.rest.dao;
/***
 * Jedis 接口
 * @author Administrator
 *
 */
public interface JedisClient {
/***
 * redis中保存的数据都是键值对，键的类型都是字符串类型，值的类型有五种： 
String， 字符串 
Hashs, 哈希表(又称散列), 哈希表中的元素是字符串 
Lists, 列表 ,列表中的元素也是字符串 
Sets, 集合，元素也是字符串 
Sorted Sets, 有序集合，元素是字符串和一个对应的score

 * @param key
 * @param value
 * @return
 */
	//字符串
	String set(String key, String value);
	String get(String key);
	//哈希表 哈希表(又称散列)
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
	long expire(String key, int second);
	//增加  1
	long incr(String key);//
	//减少 1
	long decr(String key);
	long ttl(String key);
	long del(String key);
	long hdel(String hkey, String key);
}
