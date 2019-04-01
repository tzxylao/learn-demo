package com.tzxylao.design.example;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author laoliangliang
 * @date 2019/3/26 10:52
 */
@Slf4j
public class WatchExample {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper("i.tzxylao.com:2181", 10000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                log.info("1:{}", JSON.toJSONString(watchedEvent));
            }
        });
        zk.create("/hello", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        Stat stat = zk.exists("/hello", true);
        log.info("2:{}", JSON.toJSONString(stat));

        Stat stat1 = zk.setData("/hello", "world".getBytes(), -1);
        log.info("3:{}", JSON.toJSONString(stat1));

        Stat stat2 = new Stat();
        byte[] data = zk.getData("/persistence", true, stat2);
        log.info("data1:{}", new String(data));
        log.info("4:{}", stat2);

    }
}
