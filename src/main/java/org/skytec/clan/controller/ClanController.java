package org.skytec.clan.controller;

import org.skytec.clan.context.ctx;
import org.skytec.clan.core.controller.Controller;
import org.skytec.clan.core.endpoint.Endpoint;
import org.skytec.clan.dto.AddUserGoldDto;
import org.skytec.clan.dto.CompleteTaskDto;
import org.skytec.clan.dto.TakeGoldFromClanDto;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 9:53 AM
 */

public class ClanController extends Controller {
  {
    bind("takeGoldFromClan", Endpoint.byClosure(this::takeGoldFromClan)
      .withDto(new TakeGoldFromClanDto()));

    bind("addUserGold", Endpoint.byClosure(this::addUserGold)
      .withDto(new AddUserGoldDto()));

    bind("completeTask", Endpoint.byClosure(this::completeTask)
      .withDto(new CompleteTaskDto()));
  }

  public void takeGoldFromClan(TakeGoldFromClanDto dto) {
    ctx.takeGoldFromClanService.takeGoldFromClan(dto.userId, dto.clanId, dto.gold);
  }

  public void addUserGold(AddUserGoldDto dto) {
    ctx.addUserGoldService.addGoldToClan(dto.userId, dto.clanId, dto.gold);
  }

  public void completeTask(CompleteTaskDto dto) {
    ctx.taskService.complete(dto.clanId, dto.taskId);
  }
}