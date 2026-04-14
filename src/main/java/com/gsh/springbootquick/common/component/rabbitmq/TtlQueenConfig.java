package com.gsh.springbootquick.common.component.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:wanyan
 * @create 2024- 02 -20 -10:59
 * TTL队列， 配置文件类代码
 */
@Configuration
public class TtlQueenConfig {

	//普通交换机的名称
	public static final String X_EXCHANGE = "X";
	//死信交换机的mingc
	public static final String Y_DEAD_LETTER_EXCHANGE = "X";
	//普通队列的名称
	public static final String QUEEN_A = "QA";
	public static final String QUEEN_B = "QB";
	//死信队列的命称
	public static final String DEAD_LETTER_QUEEN = "QD";
	//声明普通交换机的名称
	@Bean("xExchange")
	public DirectExchange xExchange(){
		return new DirectExchange(X_EXCHANGE);
	}

	@Bean("yExchange")
	public DirectExchange yExchange(){
		return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
	}
	//声明队列
	@Bean("queueA")
	public Queue QueueA(){
		Map<String,Object> arguments = new HashMap<>();
		//设置死信交换机
		arguments.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
		//设置死信routingKey
		arguments.put("x-dead-letter-routing-key","YD");
		//设置TTL过期时间  (ms)
		arguments.put("x-message-ttl",10000);
		return QueueBuilder.durable(QUEEN_A).withArguments(arguments).build();
	}
	@Bean("queueB")
	public Queue QueueB(){
		Map<String,Object> arguments = new HashMap<>();
		//设置死信交换机
		arguments.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
		//设置死信routingKey
		arguments.put("x-dead-letter-routing-key","YD");
		//设置TTL过期时间  (ms)
		arguments.put("x-message-ttl",40000);
		return QueueBuilder.durable(QUEEN_B).withArguments(arguments).build();
	}
	@Bean("queueC")
	public Queue QueueC(){
		Map<String,Object> arguments = new HashMap<>();
		//设置死信交换机
		arguments.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
		//设置死信routingKey
		arguments.put("x-dead-letter-routing-key","YD");
		return QueueBuilder.durable(QUEEN_B).withArguments(arguments).build();
	}
	//死信队列
	@Bean("queueD")
	public Queue QueueD(){
		return QueueBuilder.durable(DEAD_LETTER_QUEEN).build();
	}

	//绑定
	@Bean
	public Binding QueueABindingX(@Qualifier("queueA") Queue queueA,@Qualifier("xExchange") DirectExchange xExchange){
		return BindingBuilder.bind(queueA).to(xExchange).with("XA");
	}
	@Bean
	public Binding QueueBBindingX(@Qualifier("queueB") Queue queueB,@Qualifier("xExchange") DirectExchange xExchange){
		return BindingBuilder.bind(queueB).to(xExchange).with("XB");
	}
	@Bean
	public Binding QueueDBindingY(@Qualifier("queueD") Queue queueD,@Qualifier("yExchange") DirectExchange yExchange){
		return BindingBuilder.bind(queueD).to(yExchange).with("YD");
	}
	@Bean
	public Binding QueueCBindingX(@Qualifier("queueC") Queue queueC,@Qualifier("xExchange") DirectExchange xExchange){
		return BindingBuilder.bind(queueC).to(xExchange).with("YD");
	}
}
