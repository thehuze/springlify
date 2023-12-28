package dev.temez.springlify.commander.velocity.adapter.impl;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ServerArgumentAdapter implements ArgumentAdapter<RegisteredServer> {

  @NotNull ProxyServer proxyServer;

  @Override
  public @Unmodifiable @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return proxyServer.getAllServers()
        .stream().map(s -> s.getServerInfo().getName())
        .toList();
  }

  @Override
  public @NotNull Class<RegisteredServer> getTargetClass() {
    return RegisteredServer.class;
  }

  @Override
  public @NotNull RegisteredServer parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    return proxyServer.getServer(rawArgument)
        .orElseThrow(
            () -> new ConformableException("commander.arguments.no-such-server-registered")
        );
  }
}
