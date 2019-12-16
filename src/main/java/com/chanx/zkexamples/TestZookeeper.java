package com.chanx.zkexamples;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestZookeeper {

    private String connectString = "192.168.65.128:2181,192.168.65.129:2181,192.168.65.130:2181,192.168.65.131:2181";
    private int sessionTimeout = 40000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {

                List<String> children = null;
                try {
                    children = zkClient.getChildren("/sanguo" ,true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(String child : children) {
                    System.out.println(child);
                }
            }
        });
    }

    // 1. 创建子节点
    @Test
    public void createNode() throws KeeperException, InterruptedException {

        String path = zkClient.create("/sanguo", "liubei".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }

    // 2. 获取子节点
    @Test
    public void getChildren() throws KeeperException, InterruptedException {

        List<String> children = zkClient.getChildren("/sanguo" ,true);
        for(String child : children) {
            System.out.println(child);
        }

        // 延时阻塞
        Thread.sleep(Long.MAX_VALUE);
    }

    // 3. 判断节点是否存在
    @Test
    public void exist() throws KeeperException, InterruptedException {

        Stat stat = zkClient.exists("/sanguo", false);
        System.out.println(stat==null ? "not exist" : "exist");
    }
}
