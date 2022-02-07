package org.chenlujjj.zookeeper;

public class Main {

    private static ZKManager zkManager;

    public static void main(String[] args) throws Exception {

        zkManager = new ZKManagerImpl();

        zkManager.create("/test", "test123".getBytes());

        zkManager.getZNodeData("/test", false);

        zkManager.update("/test", "test456".getBytes());

        zkManager.getZNodeData("/test", false);

        zkManager.delete("/test");

    }
}
