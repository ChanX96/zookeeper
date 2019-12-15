package com.chanx.zkexamples;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;

public class TestZookeeper {

    private String connectString = "192.168.65.128:2181,192.168.65.129:2181,192.168.65.130:2181,192.168.65.131:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    @Test
    public void init() throws IOException {

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
