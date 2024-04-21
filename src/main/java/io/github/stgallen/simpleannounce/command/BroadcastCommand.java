package io.github.stgallen.simpleannounce.command;

import com.google.inject.Inject;
import com.mojang.brigadier.context.CommandContext;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class BroadcastCommand {

  @Inject
  private ProxyServer proxyServer;

  public int execute(CommandContext<CommandSource> ctx) {
    String message = ctx.getArgument("message", String.class);
    Component messageComponent = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
    Component prefix = Component.text()
            .append(Component.text("[", NamedTextColor.GRAY))
            .append(Component.text("Vanilla", TextColor.fromHexString("#02acfa")))
            .append(Component.text("+", TextColor.fromHexString("#E000E0")))
            .append(Component.text("] ", NamedTextColor.GRAY))
            .build();


    Component broadcastMessage = Component.text()
            .append(prefix)
            .append(messageComponent)
            .build();

    proxyServer.getAllPlayers().forEach(player -> player.sendMessage(broadcastMessage));
    return 1;
  }
}
