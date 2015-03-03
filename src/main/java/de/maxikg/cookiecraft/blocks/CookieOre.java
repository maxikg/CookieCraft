package de.maxikg.cookiecraft.blocks;

import de.maxikg.cookiecraft.CookieCraft;
import de.maxikg.cookiecraft.common.block.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.Random;

/**
 * @author maxikg <me@maxikg.de>
 */
public class CookieOre extends BlockOre implements ModBlock {

    public static final CookieOre INSTANCE = new CookieOre();

    public CookieOre() {
        setHardness(1.75f);
        setStepSound(Block.soundTypeStone);
        setUnlocalizedName(getName());
        setCreativeTab(CookieCraft.CREATIVE_TAB);
        setHarvestLevel("pickaxe", 3);
    }

    @Override
    public String getName() {
        return "cookie_ore";
    }

    @Override
    public void postRegisterBlock(FMLInitializationEvent e) {
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemById(357);
    }

    @Override
    public int quantityDropped(Random random) {
        return 2 + random.nextInt(4);
    }
}
