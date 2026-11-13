package buildcraft.api.v2.library;

import java.util.Objects;
import net.minecraft.resources.Identifier;

public record LibraryFormatRegistration(Identifier id, String extension, int priority, LibraryFormatHandler handler) {
    public LibraryFormatRegistration {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(extension, "extension");
        Objects.requireNonNull(handler, "handler");
        if (!extension.matches("[a-z0-9][a-z0-9_-]{0,15}") || extension.equals("bcnbt")) {
            throw new IllegalArgumentException("Invalid or reserved library file extension: " + extension);
        }
    }
}
