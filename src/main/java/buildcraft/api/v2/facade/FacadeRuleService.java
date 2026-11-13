package buildcraft.api.v2.facade;

import buildcraft.api.v2.reload.DefinitionProvenance;
import java.util.Optional;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/** Authoritative rule layer used while discovering facade materials. */
public interface FacadeRuleService {
    void disable(Identifier ruleId, Block block, DefinitionProvenance provenance);
    void mapState(Identifier ruleId, BlockState state, ItemStack stack, DefinitionProvenance provenance);
    Optional<DefinitionProvenance> disabledBy(Block block);
    Optional<ItemStack> mappedStack(BlockState state);
}
