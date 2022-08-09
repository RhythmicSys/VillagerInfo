package adhdmc.villagerinfo.Config;

import adhdmc.villagerinfo.VillagerInfo;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ConfigValidator {
    public enum Message {
        // General
        PREFIX, TOGGLE_ON, TOGGLE_OFF, NO_PERMISSION,
        NO_COMMAND, CONFIG_RELOADED, HELP_MAIN, HELP_TOGGLE,
        HELP_RELOAD, NOT_A_PLAYER,

        // Villager Info
        VILLAGER_PROFESSION, VILLAGER_JOBSITE, VILLAGER_LAST_WORKED,
        VILLAGER_RESTOCKS, VILLAGER_HOME, VILLAGER_SLEPT,
        VILLAGER_INVENTORY, INVENTORY_CONTENTS, PLAYER_REPUTATION,

        // Fillers
        NONE, NEVER, EMPTY,

        // Time
        HOUR, MINUTE, SECOND, AGO,

        // Location
        LOCATION_X, LOCATION_Y, LOCATION_Z
    }

    public enum ToggleSetting {
        // Information Toggles
        PROFESSION, JOB_SITE, LAST_WORKED, BED_LOCATION, LAST_SLEPT,
        INVENTORY, RESTOCKS, REPUTATION, HIGHLIGHT_WORKSTATION, SOUND_TOGGLE,
    }

    private static final HashMap<Message, String> localeMap = new HashMap<>();
    private static final HashMap<ToggleSetting, Boolean> toggleSettings = new HashMap<>();
    public static Sound configSound = null;
    public static int configTime = 0;


    public static void configValidator() {
        configSound = null;
        configTime = 0;
        FileConfiguration config = VillagerInfo.plugin.getConfig();
        try {
            configSound = Sound.valueOf(config.getString("sound", "BLOCK_AMETHYST_BLOCK_BREAK").toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException | NullPointerException e) {
            VillagerInfo.plugin.getLogger().warning("Configuration Error: " + configSound + " is not a valid sound! Please supply a valid sound");
            configSound = Sound.BLOCK_AMETHYST_BLOCK_BREAK;
        }
        if (config.getInt("highlight-time", 10) <= 0) {
            VillagerInfo.plugin.getLogger().warning("Configuration Error: Invalid highlight time. If you would like to disable this feature, please set 'highlight-workstation' to 'false'. Otherwise please use an integer greater than zero. Setting value to 10s");
            configTime = 10;
        } else {
            configTime = config.getInt("highlight-time");
        }
        loadToggleMap();
        loadLocaleMap();
    }

    private static void loadToggleMap() {
        toggleSettings.clear();
        FileConfiguration config = VillagerInfo.plugin.getConfig();
        toggleSettings.put(ToggleSetting.PROFESSION,
                config.getBoolean("profession", true));
        toggleSettings.put(ToggleSetting.JOB_SITE,
                config.getBoolean("job-site", true));
        toggleSettings.put(ToggleSetting.LAST_WORKED,
                config.getBoolean("last-worked", true));
        toggleSettings.put(ToggleSetting.BED_LOCATION,
                config.getBoolean("bed-location", true));
        toggleSettings.put(ToggleSetting.LAST_SLEPT,
                config.getBoolean("last-slept", true));
        toggleSettings.put(ToggleSetting.INVENTORY,
                config.getBoolean("inventory", true));
        toggleSettings.put(ToggleSetting.RESTOCKS,
                config.getBoolean("restocks", true));
        toggleSettings.put(ToggleSetting.REPUTATION,
                config.getBoolean("reputation", true));
        toggleSettings.put(ToggleSetting.HIGHLIGHT_WORKSTATION,
                config.getBoolean("highlight-workstation", true));
        toggleSettings.put(ToggleSetting.SOUND_TOGGLE,
                config.getBoolean("sound-toggle", true));
    }

    private static void loadLocaleMap() {
        localeMap.clear();
        FileConfiguration locale = VillagerInfo.localeConfig.getlocaleConfig();
        // General
        localeMap.put(Message.PREFIX,
                locale.getString("prefix", "<#3256a8><bold>[</bold><#4dd5ff>Villager Info<#3256a8><bold>]<reset>"));
        localeMap.put(Message.TOGGLE_ON,
                locale.getString("toggle-on", "<green> Villager Info Toggled <u>ON"));
        localeMap.put(Message.TOGGLE_OFF,
                locale.getString("toggle-off", "<red> Villager Info Toggled <u>OFF"));
        localeMap.put(Message.NO_PERMISSION,
                locale.getString("no-permission", "<red>You don't have permission to use this command!"));

        // Commands
        localeMap.put(Message.NO_COMMAND,
                locale.getString("no-command", "<red>No subcommand by that name!"));
        localeMap.put(Message.CONFIG_RELOADED,
                locale.getString("config-reloaded", "<gold>VillagerInfo Config Reloaded!"));
        localeMap.put(Message.HELP_MAIN,
                locale.getString("help-main", "<#4dd5ff> • How to use Villager Info\n<grey>Shift-right-click a villager while toggle is on to have a villager's information displayed"));
        localeMap.put(Message.HELP_TOGGLE,
                locale.getString("help-toggle", "<#4dd5ff> • /vill toggle\n<grey>Toggles the ability to receive villager information on or off."));
        localeMap.put(Message.HELP_RELOAD,
                locale.getString("help-reload", "<#4dd5ff> • /vill reload\n<grey>Reloads the plugin, applies config values"));
        localeMap.put(Message.NOT_A_PLAYER,
                locale.getString("not-a-player", "<red>Sorry, you must be a player to use this command"));

        // Villager Info
        localeMap.put(Message.VILLAGER_PROFESSION,
                locale.getString("villager-profession", "<green>PROFESSION:\n<aqua> • <profession>"));
        localeMap.put(Message.VILLAGER_JOBSITE,
                locale.getString("villager-jobsite-msg", "<green>JOB SITE:\n<aqua> • <jobsitelocation>"));
        localeMap.put(Message.VILLAGER_LAST_WORKED,
                locale.getString("villager-last-worked-msg", "<green>LAST WORKED AT WORKSTATION:\n<aqua> • <worktime>"));
        localeMap.put(Message.VILLAGER_RESTOCKS,
                locale.getString("villager-num-restocks-msg", "<green>RESTOCKS TODAY:\n<aqua> • <restockcount>"));
        localeMap.put(Message.VILLAGER_HOME,
                locale.getString("villager-home-msg", "<green>HOME:\n<aqua> • <homelocation>"));
        localeMap.put(Message.VILLAGER_SLEPT,
                locale.getString("villager-slept-msg", "<green>LAST SLEPT:\n<aqua> • <sleeptime>"));
        localeMap.put(Message.VILLAGER_INVENTORY,
                locale.getString("villager-inventory-msg", "<green>VILLAGER INVENTORY:<aqua> <contents>"));
        localeMap.put(Message.INVENTORY_CONTENTS,
                locale.getString("inventory-contents-msg", "\n • <item> (<amount>)"));
        localeMap.put(Message.PLAYER_REPUTATION,
                locale.getString("player-reputation-msg", "<green>PLAYER REPUTATION:\n<reputation>"));

        // Fillers
        localeMap.put(Message.NONE,
                locale.getString("none-msg", "<grey>NONE"));
        localeMap.put(Message.NEVER,
                locale.getString("never-msg", "<grey>NEVER"));
        localeMap.put(Message.EMPTY,
                locale.getString("empty-msg", "\n • <grey>EMPTY"));

        // Time
        localeMap.put(Message.HOUR,
                locale.getString("hour", "h, "));
        localeMap.put(Message.MINUTE,
                locale.getString("minute", "m, "));
        localeMap.put(Message.SECOND,
                locale.getString("second", "s"));
        localeMap.put(Message.AGO,
                locale.getString("ago", " Ago"));

        // Location
        localeMap.put(Message.LOCATION_X,
                locale.getString("location-x", "<int>x, "));
        localeMap.put(Message.LOCATION_Y,
                locale.getString("location-y", "<int>y, "));
        localeMap.put(Message.LOCATION_Z,
                locale.getString("location-z", "<int>z"));
    }

    public static Map<Message, String> getLocaleMap() {
        return Collections.unmodifiableMap(localeMap);
    }

    public static Map<ToggleSetting, Boolean> getToggleSettings() {
        return Collections.unmodifiableMap(toggleSettings);
    }

}
