package de.maxikg.cookiecraft.blocks.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * @author maxikg <me@maxikg.de>
 */
public class CookieSpringTileEntity extends TileEntity implements IUpdatePlayerListBox {

    private static final int TICKS = 20 * 30;

    private final Item cookie = Item.getItemById(357);
    private int counter = 0;

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("counter", counter);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        counter = compound.hasKey("counter") ? compound.getInteger("counter") : counter;
    }

    @Override
    public void update() {
        World world = getWorld();
        BlockPos pos = getPos();

        if (!world.isRemote && !world.isBlockPowered(pos) && ++counter > TICKS) {
            counter = 0;

            world.spawnEntityInWorld(new EntityItem(
                    world,
                    pos.getX() + 0.5,
                    pos.getY() + 1,
                    pos.getZ() + 0.5,
                    new ItemStack(cookie)
            ));
        }
    }
}
