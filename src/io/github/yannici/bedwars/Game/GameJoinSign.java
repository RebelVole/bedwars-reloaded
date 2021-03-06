package io.github.yannici.bedwars.Game;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;

import io.github.yannici.bedwars.Main;

public class GameJoinSign {

	private Game game = null;
	private Sign sign = null;

	public GameJoinSign(Game game, Sign sign) {
		this.game = game;
		this.sign = sign;
	}

	public void updateSign() {
		String[] signLines = this.getSignLines();
		for (int i = 0; i < signLines.length; i++) {
			this.sign.setLine(i, signLines[i]);
		}

		this.sign.update(true);
	}

	private String[] getSignLines() {
		String[] sign = new String[4];
		sign[0] = Main._l("sign.firstline");
		sign[1] = this.game.getRegion().getName();

		int maxPlayers = this.game.getMaxPlayers();
		int currentPlayers = 0;
		if (this.game.getState() == GameState.RUNNING) {
			currentPlayers = this.game.getTeamPlayers().size();
		} else if (this.game.getState() == GameState.WAITING) {
			currentPlayers = this.game.getPlayers().size();
		}

		String current = "0";
		String max = String.valueOf(maxPlayers);

		if (currentPlayers >= maxPlayers) {
			current = ChatColor.RED + String.valueOf(currentPlayers);
			max = ChatColor.RED + max;
		} else {
			current = ChatColor.AQUA + String.valueOf(currentPlayers);
			max = ChatColor.AQUA + max;
		}

		String playerString = ChatColor.GRAY + "[" + current + ChatColor.GRAY
				+ "/" + max + ChatColor.GRAY + "]";
		sign[2] = Main._l("sign.players") + " " + playerString;
		if (this.game.getState() == GameState.WAITING && this.game.isFull()) {
			sign[3] = ChatColor.RED + Main._l("sign.gamestate.full");
		} else {
			sign[3] = Main._l("sign.gamestate."
					+ this.game.getState().toString().toLowerCase());
		}

		return sign;
	}

	public Sign getSign() {
		return this.sign;
	}

}
