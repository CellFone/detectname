package github.cellfone.namedetect;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class NameDetectClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Register the client-side command
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> registerCommand(dispatcher));
	}

	private void registerCommand(CommandDispatcher<FabricClientCommandSource> dispatcher) {
		dispatcher.register(literal("detectname")
				.then(argument("word", StringArgumentType.string())
						.executes(context -> {
							String searchTerm = StringArgumentType.getString(context, "word").toLowerCase();
							detectName(searchTerm);
							return Command.SINGLE_SUCCESS;
						}))
		);
	}

	private void detectName(String searchTerm) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player == null || client.player.networkHandler == null) return;

		// Get the list of online players and count those whose names contain the search term
		long count = client.player.networkHandler.getPlayerList().stream()
				.map(PlayerListEntry::getProfile)
				.map(profile -> profile.getName().toLowerCase())
				.filter(name -> name.contains(searchTerm))
				.count();

		// Send the result message to the player
		client.player.sendMessage(Text.of("§7Found §4§o§l" + count + "§r§7 player(s) with '" + searchTerm + "' in their name."), false);
	}
}
