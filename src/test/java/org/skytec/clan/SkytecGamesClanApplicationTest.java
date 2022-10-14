package org.skytec.clan;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skytec.clan.context.ctx;
import org.skytec.clan.controller.ClanController;
import org.skytec.clan.core.controller.registrar.ControllerRegistrar;
import org.skytec.clan.core.server.SocketServer;
import org.skytec.clan.core.server.builder.ServerBuilder;
import org.skytec.clan.object.Clan;
import org.skytec.clan.object.Task;
import org.skytec.clan.object.User;
import org.skytec.clan.scheduler.manager.SchedulerManager;

public class SkytecGamesClanApplicationTest {
  private static final ExecutorService executorService = Executors.newFixedThreadPool(17);
  private static final List<Pair<String, Long>> tests = new ArrayList<>();
  private static final Clan clan = new Clan("Big Brothers", 0);
  private static final User user = new User("Alex", 100000);
  private static final AtomicLong expectedClanGold = new AtomicLong();
  private static final AtomicLong expectedUserGold = new AtomicLong(user.wallet().val());
  private static final AtomicInteger count = new AtomicInteger();
  private static final SocketServer socketServer = ServerBuilder.begin()
    .withPort(8000)
    .withControllerRegistrar(ControllerRegistrar.begin()
      .register(new ClanController())
      .commit())
    .build();

  @BeforeClass
  public static void setUp() throws InterruptedException {
    // starting server
    executorService.execute(socketServer::start);

    // starting scheduler manager
    SchedulerManager.begin()
      .withDelay(1)
      .start();

    // generate storage values
    ctx.clanStorage.save(clan);

    ctx.taskStorage.save(new Task("Kill boss", 1));
    tests.add(Pair.of("completeTask\n1;1", 1L));

    ctx.userStorage.save(user);
    tests.add(Pair.of("addUserGold\n1;1;1", 1L));
    tests.add(Pair.of("takeGoldFromClan\n1;1;1", -1L));
    tests.add(Pair.of("takeGoldFromClan\n1;1;1", -1L));

    // waiting while server starting
    Thread.sleep(1000);
  }

  @AfterClass
  public static void tearDown() {
    socketServer.stop();
  }

  @Test
  public void simpleTest() throws InterruptedException {
    for (int i = 0; i < tests.size(); i++) {
      sendPacketWithTest(tests.get(i % tests.size()));
    }
    // wait for logs
    Thread.sleep(2000);
  }

  @Test
  public void stressTest() {
    final int numberOfRequests = 100000;
    final long start = System.currentTimeMillis();
    for (int i = 0; i < numberOfRequests; i++) {
      AtomicInteger atomicInteger = new AtomicInteger(i);
      executorService.execute(() -> sendPacketWithTest(tests.get(atomicInteger.get() % tests.size())));
    }

    // wait until all threads have completed their work
    while (count.get() != numberOfRequests) {
    }

    final long diff = System.currentTimeMillis() - start;
    System.out.println(diff + " ms");

    assertEquals(expectedClanGold.get(), clan.wallet().val());
    assertEquals(expectedUserGold.get(), user.wallet().val());
  }

  private void sendPacketWithTest(Pair<String, Long> test) {
    int port = 8000;
    try (final Socket socket = new Socket("localhost", port);
         final OutputStream outputStream = socket.getOutputStream();
         final InputStream inputStream = socket.getInputStream()) {

      final String body = test.getLeft();

      outputStream.write((body.length() + "\n" + body).getBytes());

      final byte[] buffer = new byte[1024];
      final int received = inputStream.read(buffer);
      final String res = new String(buffer, 0, received, StandardCharsets.UTF_8);

      if (res.equals("not enough gold")) {
        count.addAndGet(1);
        return;
      }

      assertNotEquals(-1, received);
      assertEquals("ok", res);

      final Long goldChange = test.getRight();

      expectedClanGold.addAndGet(goldChange);
      count.addAndGet(1);

      if (test.getLeft().contains("User")) {
        expectedUserGold.addAndGet(-1);
      }

      if (test.getLeft().contains("take")) {
        expectedUserGold.addAndGet(1);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
