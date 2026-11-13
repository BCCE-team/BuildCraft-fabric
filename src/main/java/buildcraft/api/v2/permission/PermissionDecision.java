package buildcraft.api.v2.permission;

import java.util.Objects;
import java.util.Optional;
import net.minecraft.resources.Identifier;

/** Structured permission result with provenance suitable for diagnostics. */
public final class PermissionDecision {
    private static final PermissionDecision PASS = new PermissionDecision(PermissionVerdict.PASS, null, null);

    private final PermissionVerdict verdict;
    private final Identifier authority;
    private final String reason;

    private PermissionDecision(PermissionVerdict verdict, Identifier authority, String reason) {
        this.verdict = Objects.requireNonNull(verdict, "verdict");
        this.authority = authority;
        this.reason = reason == null || reason.isBlank() ? null : reason;
        if (verdict != PermissionVerdict.PASS && authority == null) {
            throw new IllegalArgumentException(verdict + " decision requires an authority id");
        }
    }

    public static PermissionDecision allow(Identifier authority, String reason) {
        return new PermissionDecision(PermissionVerdict.ALLOW, Objects.requireNonNull(authority, "authority"), reason);
    }

    public static PermissionDecision deny(Identifier authority, String reason) {
        return new PermissionDecision(PermissionVerdict.DENY, Objects.requireNonNull(authority, "authority"), reason);
    }

    public static PermissionDecision pass() {
        return PASS;
    }

    public PermissionVerdict verdict() { return verdict; }
    public Optional<Identifier> authority() { return Optional.ofNullable(authority); }
    public Optional<String> reason() { return Optional.ofNullable(reason); }
    public boolean isTerminalDeny() { return verdict == PermissionVerdict.DENY; }
}
