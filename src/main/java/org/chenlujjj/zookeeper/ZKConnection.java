package org.chenlujjj.zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ZKConnection {

    private ZooKeeper zk;

    final CountDownLatch connLatch = new CountDownLatch(1);

    public ZKConnection() {}


    public ZooKeeper connect(String host) throws Exception {
        zk = new ZooKeeper(host, 2000, watchedEvent -> {
            if (watchedEvent.getState() == KeeperState.SyncConnected) {
                connLatch.countDown();
            }
        });
        connLatch.await();
        return zk;
    }

    public void close() throws InterruptedException {
        zk.close();
    }
}