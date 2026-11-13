package buildcraft.transport.api2;

import buildcraft.api.v2.BuildCraftApi;
import buildcraft.api.v2.BuildCraftRegistries;
import buildcraft.api.v2.persistence.ApiCodec;
import buildcraft.api.v2.persistence.CodecResult;
import buildcraft.api.v2.persistence.OpaqueData;
import buildcraft.api.v2.signal.BuildCraftSignalChannels;
import buildcraft.api.v2.signal.SignalChannelType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;

/** Registers the boolean signal channel types used by classic BuildCraft pipe wire. */
final class ClassicSignalChannels {
    private static final Identifier BOOL_FORMAT = Identifier.tryParse("buildcraft:boolean_signal");
    private static final ApiCodec<Boolean, OpaqueData> BOOL_CODEC = new ApiCodec<>() {
        public CodecResult<Boolean> decode(OpaqueData payload) {
            byte[] bytes = payload.bytes();
            return bytes.length == 1 ? CodecResult.success(bytes[0] != 0) : CodecResult.failure("Expected one boolean byte");
        }
        public CodecResult<OpaqueData> encode(Boolean value) {
            return CodecResult.success(new OpaqueData(BOOL_FORMAT, new byte[] { (byte) (Boolean.TRUE.equals(value) ? 1 : 0) }));
        }
    };

    private ClassicSignalChannels() {}

    static void register() {
        var registry = BuildCraftApi.registry(BuildCraftRegistries.SIGNAL_CHANNEL_TYPES);
        for (DyeColor color : DyeColor.values()) {
            Identifier id = BuildCraftSignalChannels.id(color);
            registry.register(
                id,
                new SignalChannelType<>(id, Boolean.FALSE, BOOL_CODEC, (a, b) -> a || b),
                () -> "buildcraft"
            );
        }
    }
}
