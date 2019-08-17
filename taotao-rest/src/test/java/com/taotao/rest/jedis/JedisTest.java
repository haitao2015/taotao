package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	@Test
	public void testJedisSingle() { //单机版redis
	
		Jedis jedis=new Jedis("192.168.232.202",6379);
		jedis.set("key1", "jedis test 6379");
		String str=jedis.get("key1");
		System.out.println("test1 key1="+str);
		jedis.close();//关闭
	}
	/**
	 * 使用连接池
	 */
	@Test 
	public void testJedisPool() { //单机版redis
		
		JedisPool pool=new JedisPool("192.168.232.202",6379);
		Jedis jedis=pool.getResource();
		jedis.set("key2", "jedis test 6379");
		String str=jedis.get("key2");
		System.out.println("test2 key2="+str);

		jedis.close();//关闭jdeis
		pool.close();//关闭POOL连接池
	}
	/***
	 * 集群连接
	 */
	@Test
	public void testJedisCluster() {
		HashSet<HostAndPort> nodes=new HashSet<>();
		nodes.add(new HostAndPort("192.168.232.202", 7001));
		nodes.add(new HostAndPort("192.168.232.202", 7002));
		nodes.add(new HostAndPort("192.168.232.202", 7003));
		nodes.add(new HostAndPort("192.168.232.202", 7004));
		nodes.add(new HostAndPort("192.168.232.202", 7005));
		nodes.add(new HostAndPort("192.168.232.202", 7005));

		JedisCluster jc=new JedisCluster(nodes);
		jc.set("key1", "jedis cluster test ");
		System.out.println(jc.get("key1"));
		
		
	}
	@Test
	public void testSpringJedis() {
		ApplicationContext at=new ClassPathXmlApplicationContext("classpath:spring/applicationContext*.xml");
		JedisPool pool=(JedisPool)at.getBean("redisClient");
		Jedis jedis=pool.getResource();
		String str=jedis.get("key1");
		System.out.println("testSpringJedis(): str="+str);
		jedis.close();
		pool.close();
		
		
	}
	@Test
	public void testSpringJedisCluster() {
		ApplicationContext at=new ClassPathXmlApplicationContext("classpath:spring/applicationContext*.xml");
		JedisCluster jedisc=(JedisCluster)at.getBean("redisCluster");
		
		String str=jedisc.get("key1");
		System.out.println("testSpringJedisCluster(): str="+str);
		
		
	}
}
