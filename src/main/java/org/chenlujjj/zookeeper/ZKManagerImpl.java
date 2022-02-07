package org.chenlujjj.zookeeper;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;


public class ZKManagerImpl implements ZKManager {
    private static ZooKeeper zk;
    private static ZKConnection zkConnection;

    public ZKManagerImpl() {
        initialize();
    }

    /** * Initialize connection */
    private void initialize() {
        try {
            zkConnection = new ZKConnection();
            zk = zkConnection.connect("localhost");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            zkConnection.close();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public Object getZNodeData(String path, boolean watchFlag) {
        try {
            byte[] b = null;
            b = zk.getData(path, null, null);
            String data = new String(b, "UTF-8");
            System.out.println(data);
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void update(String path, byte[] data) throws KeeperException, InterruptedException {
        int version = zk.exists(path, true)
                .getVersion();
        zk.setData(path, data, version);
    }

    public void delete(String path) throws KeeperException, InterruptedException {
        zk.delete(path, -1);
    }
}