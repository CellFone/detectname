package github.cellfone.namedetect;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;

public class NameDetectClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		registerCommand();
	}

	private void registerCommand() {
		ClientCommandManager.DISPATCHER.register(
			ClientCommandManager.literal("detectname")
				.then(ClientCommandManager.argument("word", StringArgumentType.string())
					.executes(context -> {
						String searchTerm = StringArgumentType.getString(context, "word").toLowerCase();
						detectName(searchTerm);
						return Command.SINGLE_SUCCESS;
					})
				)
		);
	}

	private void detectName(String searchTerm) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player == null || client.player.networkHandler == null) return;

		long count = client.player.networkHandler.getPlayerList().stream()
				.map(PlayerListEntry::getProfile)
				.map(profile -> profile.getName().toLowerCase())
				.filter(name -> name.contains(searchTerm))
				.count();

		Text message = Texts.bracketed(Text.of("Found ")
				.append(Text.of(String.valueOf(count)).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF5555))))
				.append(Text.of(" player(s) with '"))
				.append(Text.of(searchTerm).setStyle(Style.EMPTY.withItalic(true)))
				.append(Text.of("' in their name.")));

		client.player.sendMessage(message, false);
	}
}
