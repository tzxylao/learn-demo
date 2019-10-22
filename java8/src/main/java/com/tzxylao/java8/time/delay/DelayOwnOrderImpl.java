package com.tzxylao.java8.time.delay;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * 判断延迟过期 main
 */
@Slf4j
public class DelayOwnOrderImpl implements DelayOrder<Order> {

    private ExecutorService delayOrderExecutor;

    private final static DelayQueue<ItemDelayed<Order>> DELAY_QUEUE = new DelayQueue<>();

    public static void main(String[] args) {
        DelayOwnOrderImpl delayOwnOrder = new DelayOwnOrderImpl();
        delayOwnOrder.init();
    }
    /**
     * 初始化时加载数据库中需处理超时的订单
     * 系统启动:扫描数据库中未支付(要在更新时:加上已支付就不用更新了),未过期的的订单
     */
    public void init() {
        delayOrderExecutor = Executors.newSingleThreadExecutor();
        System.out.println("系统启动:扫描数据库中未支付,未过期的的订单");
        List<Order> orderList = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        instance.add(Calendar.MINUTE, -30);
        for (long i = 0; i < 10; i++) {
            int amount = 20 - random.nextInt(30);
            instance.add(Calendar.SECOND, amount);
            System.out.println(sdf.format(instance.getTime()) + " 订单："+i);
            orderList.add(new Order(i, instance.getTime()));
        }

        for (Order order : orderList) {
            ItemDelayed<Order> orderDelayed = new ItemDelayed<>(order.getId(), order.getCreateDate().getTime());
            this.addToOrderDelayQueue(orderDelayed);
        }
        System.out.println("系统启动:扫描数据库中未支付的订单,总共扫描了" + orderList.size() + "个订单,推入检查队列,准备到期检查...");

        /*启动一个线程，去取延迟订单*/
        delayOrderExecutor.execute(() -> {
            System.out.println("启动处理的订单线程:" + Thread.currentThread().getName());
            ItemDelayed<Order> orderDelayed;
            while (true) {
                try {
                    orderDelayed = DELAY_QUEUE.take();
                    //处理超时订单
                    System.out.println("订单超时：" + orderDelayed.getDataId() + " 时间："+sdf.format(new Date()));
                } catch (Exception e) {
                    log.error("执行自营超时订单的_延迟队列_异常:" + e);
                }
            }
        });
    }

    /**
     * 加入延迟消息队列
     **/
    @Override
    public boolean addToOrderDelayQueue(ItemDelayed<Order> orderDelayed) {
        return DELAY_QUEUE.add(orderDelayed);
    }

    /**
     * 加入延迟消息队列
     **/
    @Override
    public boolean addToDelayQueue(Order order) {
        ItemDelayed<Order> orderDelayed = new ItemDelayed<>(order.getId(), order.getCreateDate().getTime());
        return DELAY_QUEUE.add(orderDelayed);
    }

    /**
     * 从延迟队列中移除 主动取消就主动从队列中取出
     **/
    @Override
    public void removeToOrderDelayQueue(Order order) {
        if (order == null) {
            return;
        }
        for (Iterator<ItemDelayed<Order>> iterator = DELAY_QUEUE.iterator(); iterator.hasNext(); ) {
            ItemDelayed<Order> queue = iterator.next();
            if (queue.getDataId().equals(order.getId())) {
                DELAY_QUEUE.remove(queue);
            }
        }
    }
}
