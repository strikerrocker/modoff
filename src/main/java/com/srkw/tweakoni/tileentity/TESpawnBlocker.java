package com.srkw.tweakoni.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class TESpawnBlocker extends TileEntity implements ITickable {

    @Override
    public void update() {
        int tick = 0;
        if (tick++ % 10 == 0) {
            //Sets the first corner of the box
            int x1 = world.getChunk(pos).getPos().getXStart() - 16;
            int y1 = 0;
            int z1 = world.getChunk(pos).getPos().getZStart() - 16;

            //Sets the second corner of the box
            int x2 = world.getChunk(pos).getPos().getXEnd() + 16;
            int y2 = 256;
            int z2 = world.getChunk(pos).getPos().getZEnd() + 16;

            //Stores the box in an AxisAlignedBB
            AxisAlignedBB AABB = new AxisAlignedBB(new BlockPos(x1, y1, z1), new BlockPos(x2, y2, z2));

            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, AABB);

            //Loops through the entity list and kills all the unwanted ones
            for (Entity entity : list) {
                if (entity instanceof EntityMob)
                    entity.setDead();
            }
        }
    }
}