package org.skytec.clan.context;

import org.skytec.clan.logger.queue.LogQueue;
import org.skytec.clan.service.AddUserGoldService;
import org.skytec.clan.service.ClanService;
import org.skytec.clan.service.LoggingService;
import org.skytec.clan.service.TakeGoldFromClanService;
import org.skytec.clan.service.TaskService;
import org.skytec.clan.service.TransactionService;
import org.skytec.clan.service.UserService;
import org.skytec.clan.storage.ClanStorage;
import org.skytec.clan.storage.TaskStorage;
import org.skytec.clan.storage.UserStorage;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 3:33 PM
 */

public interface ctx {
  LogQueue logQueue = new LogQueue();
  ClanStorage clanStorage = new ClanStorage();
  TaskStorage taskStorage = new TaskStorage();
  UserStorage userStorage = new UserStorage();
  TransactionService transactionService = new TransactionService();
  LoggingService loggingService = new LoggingService();
  UserService userService = new UserService();
  ClanService clanService = new ClanService();
  TaskService taskService = new TaskService();
  AddUserGoldService addUserGoldService = new AddUserGoldService();
  TakeGoldFromClanService takeGoldFromClanService = new TakeGoldFromClanService();
}
