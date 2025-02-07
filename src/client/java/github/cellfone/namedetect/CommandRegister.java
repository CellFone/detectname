package cc.aabss.eventutils.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

public class CommandRegister {
    public static void register(@NotNull CommandDispatcher<FabricClientCommandSource> dispatcher) {
        // eventutils
        final LiteralCommandNode<FabricClientCommandSource> main = ClientCommandManager
                .literal("eventutils")
                .executes(context -> {
                    context.getSource().sendFeedback(Text.literal("EventUtils Commands: /detectname <word>").formatted(Formatting.GRAY));
                    return 0;
                }).build();

        // eventutils detectname
        final LiteralCommandNode<FabricClientCommandSource> detectName = ClientCommandManager
                .literal("detectname")
                .then(ClientCommandManager.argument("word", StringArgumentType.string())
                        .executes(context -> {
                            DetectNameCmd.detectName(context, StringArgumentType.getString(context, "word"));
                            return 0;
                        }))
                .executes(context -> {
                    context.getSource().sendFeedback(Text.literal("Usage: /detectname <word>").formatted(Formatting.RED));
                    return 0;
                }).build();

        // Build command tree
        dispatcher.getRoot().addChild(main);
        main.addChild(detectName);
    }
}
