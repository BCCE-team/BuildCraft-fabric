package buildcraft.api.v2.filler;

import java.util.Collection;
import java.util.Optional;
import net.minecraft.resources.Identifier;

public interface FillerPatternService {
    Optional<FillerPatternType> type(Identifier id);
    Collection<FillerPatternType> types();
}
