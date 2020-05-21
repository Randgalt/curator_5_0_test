package binary;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.test.TestingServer;

public class Curator50Test
        implements Runnable
{
    public static void main(String[] args)
    {
        new Curator50Test().run();
    }

    @Override
    public void run()
    {
        try {
            try (TestingServer server = new TestingServer()) {
                try (CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new RetryOneTime(1))) {
                    client.start();
                    try (PathChildrenCache cache = new PathChildrenCache(client, "/test", true)) {
                        cache.getListenable().addListener((client1, event) -> {});
                        cache.start();

                        client.create().creatingParentsIfNeeded().forPath("/test/foo", "hi".getBytes());
                        Thread.sleep(2000);
                        ChildData currentData = cache.getCurrentData("/test/foo");
                        System.out.println(new String(currentData.getData()));
                    }
                }
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
