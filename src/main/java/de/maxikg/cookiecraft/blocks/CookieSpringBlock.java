package de.maxikg.cookiecraft.blocks;

import de.maxikg.cookiecraft.CookieCraft;
import de.maxikg.cookiecraft.blocks.tileentity.CookieSpringTileEntity;
import de.maxikg.cookiecraft.common.model.ModdedContent;
import de.maxikg.cookiecraft.common.model.PostRegistrationListener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author maxikg <me@maxikg.de>
 */
public class CookieSpringBlock extends BlockContainer implements ModdedContent, PostRegistrationListener {

    public CookieSpringBlock() {
        super(Material.rock);

        setUnlocalizedName(getName());
        setTickRandomly(true);
        setHardness(1.3f);
        setStepSound(Block.soundTypeStone);
        setCreativeTab(CookieCraft.CREATIVE_TAB);
        setHarvestLevel("pickaxe", 3);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 3;
    }

    @Override
    public String getName() {
        return "cookie_spring";
    }

    @Override
    public void postRegistration(FMLInitializationEvent e) {
        GameRegistry.registerTileEntity(CookieSpringTileEntity.class, "cookie_spring_te");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new CookieSpringTileEntity();
    }
}
