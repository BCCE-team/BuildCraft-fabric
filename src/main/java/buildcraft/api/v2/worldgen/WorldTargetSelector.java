package buildcraft.api.v2.worldgen;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.resources.Identifier;

/**
 * Data-like world selection used by BuildCraft world-generation extension rules.
 *
 * <p>Empty inclusion sets mean "all". Exclusions always win. Tag membership is supplied by
 * the runtime so this contract stays independent from loader-specific tag lookup APIs.
 */
public final class WorldTargetSelector {
    public static final WorldTargetSelector ALL = builder().build();

    private final Set<Identifier> dimensions;
    private final Set<Identifier> dimensionTags;
    private final Set<Identifier> excludedDimensions;
    private final Set<Identifier> biomes;
    private final Set<Identifier> biomeTags;
    private final Set<Identifier> excludedBiomes;

    private WorldTargetSelector(Builder builder) {
        dimensions = immutable(builder.dimensions);
        dimensionTags = immutable(builder.dimensionTags);
        excludedDimensions = immutable(builder.excludedDimensions);
        biomes = immutable(builder.biomes);
        biomeTags = immutable(builder.biomeTags);
        excludedBiomes = immutable(builder.excludedBiomes);
    }

    public static Builder builder() { return new Builder(); }

    public Set<Identifier> dimensions() { return dimensions; }
    public Set<Identifier> dimensionTags() { return dimensionTags; }
    public Set<Identifier> excludedDimensions() { return excludedDimensions; }
    public Set<Identifier> biomes() { return biomes; }
    public Set<Identifier> biomeTags() { return biomeTags; }
    public Set<Identifier> excludedBiomes() { return excludedBiomes; }

    public boolean matches(
        Identifier dimension,
        Identifier biome,
        Predicate<Identifier> dimensionTagMembership,
        Predicate<Identifier> biomeTagMembership
    ) {
        Objects.requireNonNull(dimension, "dimension");
        Objects.requireNonNull(biome, "biome");
        Objects.requireNonNull(dimensionTagMembership, "dimensionTagMembership");
        Objects.requireNonNull(biomeTagMembership, "biomeTagMembership");

        if (excludedDimensions.contains(dimension) || excludedBiomes.contains(biome)) return false;
        boolean dimensionIncluded = dimensions.isEmpty() && dimensionTags.isEmpty()
            || dimensions.contains(dimension)
            || dimensionTags.stream().anyMatch(dimensionTagMembership);
        if (!dimensionIncluded) return false;
        return biomes.isEmpty() && biomeTags.isEmpty()
            || biomes.contains(biome)
            || biomeTags.stream().anyMatch(biomeTagMembership);
    }

    private static Set<Identifier> immutable(Set<Identifier> source) {
        return Collections.unmodifiableSet(new LinkedHashSet<>(source));
    }

    public static final class Builder {
        private final Set<Identifier> dimensions = new LinkedHashSet<>();
        private final Set<Identifier> dimensionTags = new LinkedHashSet<>();
        private final Set<Identifier> excludedDimensions = new LinkedHashSet<>();
        private final Set<Identifier> biomes = new LinkedHashSet<>();
        private final Set<Identifier> biomeTags = new LinkedHashSet<>();
        private final Set<Identifier> excludedBiomes = new LinkedHashSet<>();

        public Builder dimension(Identifier id) { dimensions.add(Objects.requireNonNull(id, "id")); return this; }
        public Builder dimensionTag(Identifier id) { dimensionTags.add(Objects.requireNonNull(id, "id")); return this; }
        public Builder excludeDimension(Identifier id) { excludedDimensions.add(Objects.requireNonNull(id, "id")); return this; }
        public Builder biome(Identifier id) { biomes.add(Objects.requireNonNull(id, "id")); return this; }
        public Builder biomeTag(Identifier id) { biomeTags.add(Objects.requireNonNull(id, "id")); return this; }
        public Builder excludeBiome(Identifier id) { excludedBiomes.add(Objects.requireNonNull(id, "id")); return this; }
        public WorldTargetSelector build() { return new WorldTargetSelector(this); }
    }
}
