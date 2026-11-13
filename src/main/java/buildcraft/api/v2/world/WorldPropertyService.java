package buildcraft.api.v2.world;

import java.util.Map;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

public interface WorldPropertyService {
    void register(Identifier id, WorldProperty property);
    Optional<WorldProperty> get(Identifier id);
    Map<Identifier, WorldProperty> properties();

    default boolean test(Identifier id, Level level, BlockPos pos) {
        return get(id).map(property -> property.test(level, pos)).orElse(false);
    }
}
