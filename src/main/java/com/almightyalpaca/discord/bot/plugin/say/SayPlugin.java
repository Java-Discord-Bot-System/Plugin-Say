package com.almightyalpaca.discord.bot.plugin.say;

import com.almightyalpaca.discord.bot.system.command.Command;
import com.almightyalpaca.discord.bot.system.command.CommandHandler;
import com.almightyalpaca.discord.bot.system.command.arguments.special.Rest;
import com.almightyalpaca.discord.bot.system.events.commands.CommandEvent;
import com.almightyalpaca.discord.bot.system.exception.PluginLoadingException;
import com.almightyalpaca.discord.bot.system.exception.PluginUnloadingException;
import com.almightyalpaca.discord.bot.system.plugins.Plugin;
import com.almightyalpaca.discord.bot.system.plugins.PluginInfo;

import net.dv8tion.jda.Permission;
import net.dv8tion.jda.utils.PermissionUtil;

public class SayPlugin extends Plugin {

	class SayCommand extends Command {

		public SayCommand() {
			super("say", "Let me say something", "");
		}

		@CommandHandler(dm = true, guild = true, async = true)
		private void onCommand(final CommandEvent event, final Rest rest) {
			String message = "\u180E" + rest.getString();
			if (event.isGuild()) {
				if (!PermissionUtil.checkPermission(event.getAuthor(), Permission.MESSAGE_MENTION_EVERYONE, event.getTextChannel())) {
					message = message.replaceAll("@everyone", "@\u180Eeveryone");
				}
			}
			event.sendMessage(message);
		}
	}

	private static final PluginInfo INFO = new PluginInfo("com.almightyalpaca.discord.bot.plugin.say", "1.0.0", "Almighty Alpaca", "Say Plugin", "Just to Say");

	public SayPlugin() {
		super(SayPlugin.INFO);
	}

	@Override
	public void load() throws PluginLoadingException {
		this.registerCommand(new SayCommand());
	}

	@Override
	public void unload() throws PluginUnloadingException {}
}
