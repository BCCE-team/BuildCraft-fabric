package buildcraft.fabric;

import buildcraft.lib.client.model.ModelHolderRegistry;
import buildcraft.lib.client.sprite.SpriteHolderRegistry;
import buildcraft.lib.platform.client.ClientAtlas;
import buildcraft.silicon.BCSiliconModels;
import buildcraft.transport.BCTransportModels;
import buildcraft.transport.client.model.PipeBaseModelGenStandard;
import buildcraft.transport.client.model.PipeModelCacheAll;
import buildcraft.transport.client.model.PipeModelCacheBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.data.AtlasIds;

/**
 * 26.3 atlas generation bridge. ModelLoadingPlugin installs model modifiers before the reload;
 * vanilla publishes its new atlas only once all reload work completes. At that point we may
 * safely resolve its sprites and BCCE variable geometry. F3+T replaces the TextureAtlas instance.
 */
public final class FabricModelLifecycle263 {
    private static TextureAtlas currentAtlas;

    private FabricModelLifecycle263() { }

    public static void refreshIfAtlasChanged(Minecraft client) {
        if (client == null || client.getAtlasManager() == null) return;
        final TextureAtlas atlas;
        try {
            atlas = client.getAtlasManager().getAtlasOrThrow(AtlasIds.BLOCKS);
        } catch (IllegalArgumentException | IllegalStateException notReady) {
            return;
        }
        if (atlas == currentAtlas) return;

        // When a resource pack is swapped, no cached quad may keep UVs from the old atlas.
        SpriteHolderRegistry.onTextureStitchPost(new ClientAtlas.After(atlas));
        PipeBaseModelGenStandard.loadSpritesCache(atlas);
        ModelHolderRegistry.reloadVariableModels();
        PipeModelCacheBase.clearCaches();
        PipeModelCacheAll.clearModels();
        BCTransportModels.onModelBakeComplete();
        BCSiliconModels.clearAtlasDependentCaches();
        currentAtlas = atlas;
    }
}
