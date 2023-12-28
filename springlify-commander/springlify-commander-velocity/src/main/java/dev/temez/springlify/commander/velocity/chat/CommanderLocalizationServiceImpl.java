package dev.temez.springlify.commander.velocity.chat;

import com.velocitypowered.api.command.CommandSource;
import dev.temez.springlify.commander.commons.chat.CommanderLocalizationService;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.platform.velocity.service.localization.LocalizationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommanderLocalizationServiceImpl implements CommanderLocalizationService {

  @NotNull LocalizationService localizationService;

  @Override
  public @NotNull Component localizeComponent(
      @NotNull Sender<?> sender,
      @NotNull String messageKey,
      Object @NotNull ... replacers
  ) {
    return localizationService.getLocalizedMessage(
        (CommandSource) sender.getPlatformSender(),
        messageKey,
        replacers
    );
  }

  @Override
  public @NotNull String localizeString(
      @NotNull Sender<?> sender,
      @NotNull String messageKey,
      Object @NotNull ... replacers
  ) {
    return localizationService.getLocalizedMessageString(
        (CommandSource) sender.getPlatformSender(),
        messageKey,
        replacers
    );
  }
}
