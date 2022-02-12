package me.goodestenglish.streamer.util;

import java.util.*;

public class CC {

	public static final String BLUE;
	public static final String AQUA;
	public static final String YELLOW;
	public static final String RED;
	public static final String GRAY;
	public static final String GOLD;
	public static final String GREEN;
	public static final String WHITE;
	public static final String BLACK;
	public static final String BOLD;
	public static final String ITALIC;
	public static final String UNDER_LINE;
	public static final String STRIKE_THROUGH;
	public static final String RESET;
	public static final String MAGIC;
	public static final String DARK_BLUE;
	public static final String DARK_AQUA;
	public static final String DARK_GRAY;
	public static final String DARK_GREEN;
	public static final String DARK_PURPLE;
	public static final String DARK_RED;
	public static final String PINK;
	public static final String MENU_BAR;
	public static final String CHAT_BAR;
	public static final String CHAT_BAR_CUSTOM_COLOR;
	public static final String SB_BAR;

	static {
		BLUE = "§9";
		AQUA = "§b";
		YELLOW = "§e";
		RED = "§c";
		GRAY = "§7";
		GOLD = "§6";
		GREEN = "§a";
		WHITE = "§f";
		BLACK = "§0";
		BOLD = "§l";
		ITALIC = "§o";
		UNDER_LINE = "§n";
		STRIKE_THROUGH = "§m";
		RESET = "§r";
		MAGIC = "§k";
		DARK_BLUE = "§1";
		DARK_AQUA = "§3";
		DARK_GRAY = "§8";
		DARK_GREEN = "§2";
		DARK_PURPLE = "§5";
		DARK_RED = "§4";
		PINK = "§d";
		MENU_BAR = GRAY + STRIKE_THROUGH + "------------------------";
		CHAT_BAR = GRAY + STRIKE_THROUGH + "---------------------------------";
		CHAT_BAR_CUSTOM_COLOR = STRIKE_THROUGH + "---------------------------------";
		SB_BAR = GRAY + STRIKE_THROUGH + "----------------------";
	}

	public static String translate(String in) {
		return in.replace("&","§");
	}

	public static List<String> translate(List<String> lines) {
		List<String> toReturn = new ArrayList<>();

		for (String line : lines) {
			toReturn.add(line.replace("&","§"));
		}

		return toReturn;
	}

	public static List<String> translate(String[] lines) {
		List<String> toReturn = new ArrayList<>();

		for (String line : lines) {
			if (line != null) {
				toReturn.add(line.replace("&","§"));
			}
		}

		return toReturn;
	}

}
