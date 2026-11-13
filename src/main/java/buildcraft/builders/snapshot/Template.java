/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.builders.snapshot;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import buildcraft.lib.internal.core.InvalidInputDataException;
import buildcraft.lib.internal.enums.EnumSnapshotType;
import buildcraft.builders.internal.filler.legacy.IFilledTemplate;
import buildcraft.lib.misc.VecUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Rotation;
import buildcraft.lib.compat.NbtCompat;

public class Template extends Snapshot {
    public BitSet data;

    public Template copy() {
        Template template = new Template();
        template.size = size;
        template.facing = facing;
        template.offset = offset;
        template.data = (BitSet) data.clone();
        template.computeKey();
        // The content hash is recomputed, but the user-visible blueprint metadata
        // (name, author and creation date) must survive a copy.
        template.key = new Snapshot.Key(template.key, key.header);
        return template;
    }

    public FilledTemplate getFilledTemplate() {
        return new FilledTemplate();
    }

    public void invert() {
        data.flip(0, getDataSize());
    }

    public CompoundTag serializeNBT() {
        CompoundTag nbt = super.serializeNBT();
        nbt.putByteArray("data", data.toByteArray());
        return nbt;
    }

    public void deserializeNBT(CompoundTag nbt) throws InvalidInputDataException {
        super.deserializeNBT(nbt);
        data = BitSet.valueOf(NbtCompat.getByteArray(nbt, "data"));
        if (data.length() > getDataSize()) {
            throw new InvalidInputDataException(
                "Serialized data has length of " + data.length() +
                    ", but we expected at most " +
                    getDataSize() + " (" + size.toString() + ")"
            );
        }
    }

    public EnumSnapshotType getType() {
        return EnumSnapshotType.TEMPLATE;
    }

    public class BuildingInfo extends Snapshot.BuildingInfo {
        public BuildingInfo(BlockPos basePos, Rotation rotation) {
            super(basePos, rotation);
        }

        public Template getSnapshot() {
            return Template.this;
        }
    }

    public class FilledTemplate implements IFilledTemplate {
        private final BlockPos max = size.subtract(VecUtil.POS_ONE);

        public Template getTemplate() {
            return Template.this;
        }

        private void checkPos(int x, int y, int z) {
            if (x < 0 || y < 0 || z < 0 || x >= size.getX() || y >= size.getY() || z >= size.getZ()) {
                throw new IllegalArgumentException("Size: " + size + ", pos: " + new BlockPos(x, y, z));
            }
        }

        public BlockPos getSize() {
            return size;
        }

        public BlockPos getMax() {
            return max;
        }

        public void set(int x, int y, int z, boolean value) {
            checkPos(x, y, z);
            data.set(posToIndex(x, y, z), value);
        }

        public boolean get(int x, int y, int z) {
            checkPos(x, y, z);
            return data.get(posToIndex(x, y, z));
        }

        public void setLineX(int fromX, int toX, int y, int z, boolean value) {
            checkPos(fromX, y, z);
            checkPos(toX, y, z);
            data.set(posToIndex(fromX, y, z), posToIndex(toX, y, z) + 1, value);
        }

        public void setLineY(int x, int fromY, int toY, int z, boolean value) {
            checkPos(x, fromY, z);
            checkPos(x, toY, z);
            for (int y = fromY; y <= toY; y++) {
                set(x, y, z, value);
            }
        }

        public void setLineZ(int x, int y, int fromZ, int toZ, boolean value) {
            checkPos(x, y, fromZ);
            checkPos(x, y, toZ);
            for (int z = fromZ; z <= toZ; z++) {
                set(x, y, z, value);
            }
        }

        public void setAreaYZ(int x, int fromY, int toY, int fromZ, int toZ, boolean value) {
            checkPos(x, fromY, fromZ);
            checkPos(x, toY, toZ);
            for (int z = fromZ; z <= toZ; z++) {
                setLineY(x, fromY, toY, z, value);
            }
        }

        public void setAreaXZ(int fromX, int toX, int y, int fromZ, int toZ, boolean value) {
            checkPos(fromX, y, fromZ);
            checkPos(toX, y, toZ);
            for (int z = fromZ; z <= toZ; z++) {
                setLineX(fromX, toX, y, z, value);
            }
        }

        public void setAreaXY(int fromX, int toX, int fromY, int toY, int z, boolean value) {
            checkPos(fromX, fromY, z);
            checkPos(toX, toY, z);
            for (int y = fromY; y <= toY; y++) {
                setLineX(fromX, toX, y, z, value);
            }
        }

        public void setPlaneYZ(int x, boolean value) {
            checkPos(x, 0, 0);
            setAreaYZ(x, 0, max.getY(), 0, max.getZ(), value);
        }

        public void setPlaneXZ(int y, boolean value) {
            checkPos(0, y, 0);
            setAreaXZ(0, max.getX(), y, 0, max.getZ(), value);
        }

        public void setPlaneXY(int z, boolean value) {
            checkPos(0, 0, z);
            data.set(posToIndex(0, 0, z), posToIndex(max.getX(), max.getY(), z) + 1, value);
        }

        public void setAll(boolean value) {
            data.set(0, getDataSize(), value);
        }

        public String toString() {
            List<String> zParts = new ArrayList<>();
            for (int z = 0; z < getSize().getZ(); z++) {
                List<String> yParts = new ArrayList<>();
                for (int y = 0; y < getSize().getY(); y++) {
                    List<String> xParts = new ArrayList<>();
                    for (int x = 0; x < getSize().getX(); x++) {
                        xParts.add(get(x, y, z) ? "#" : " ");
                    }
                    yParts.add(String.join("", xParts));
                }
                zParts.add(String.join("\n", yParts));
            }
            return String.join(
                "\n" + String.join("", Collections.nCopies(getSize().getX(), "-")) + "\n",
                zParts
            );
        }
    }
}
