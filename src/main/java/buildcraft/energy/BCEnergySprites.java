package buildcraft.energy;

import net.minecraft.resources.Identifier;

/** Energy-module sprites are declared in assets/minecraft/atlases/blocks.json on 1.20.1. */
public final class BCEnergySprites {
    public static final Identifier IRON_BACK_R = Identifier.fromNamespaceAndPath(BCEnergy.MODID, "blocks/engine/iron/back");
    public static final Identifier IRON_SIDE_R = Identifier.fromNamespaceAndPath(BCEnergy.MODID, "blocks/engine/iron/side");
    public static final Identifier STONE_BACK_R = Identifier.fromNamespaceAndPath(BCEnergy.MODID, "blocks/engine/stone/back");
    public static final Identifier STONE_SIDE_R = Identifier.fromNamespaceAndPath(BCEnergy.MODID, "blocks/engine/stone/side");
    public static final Identifier FE_BACK_R = Identifier.fromNamespaceAndPath(BCEnergy.MODID, "blocks/engine/fe/back");
    public static final Identifier FE_SIDE_R = Identifier.fromNamespaceAndPath(BCEnergy.MODID, "blocks/engine/fe/side");

    public static final Identifier ENGINE_IRON_GUI = Identifier.fromNamespaceAndPath(BCEnergy.MODID, "textures/gui/combustion_engine_gui.png");
    public static final Identifier ENGINE_STONE_GUI = Identifier.fromNamespaceAndPath(BCEnergy.MODID, "textures/gui/steam_engine_gui.png");
    public static final Identifier ENGINE_FE_GUI = Identifier.fromNamespaceAndPath(BCEnergy.MODID, "textures/gui/fe_engine_gui.png");
    public static final Identifier DYNAMO_MJ_GUI = Identifier.fromNamespaceAndPath(BCEnergy.MODID, "textures/gui/mj_dynamo_gui.png");

    private BCEnergySprites() {
    }

    public static void init() {
        // Sprite loading is driven by the block-atlas JSON in 1.20.1.
    }
}
