package de.maxikg.cookiecraft.blocks;

import com.google.common.collect.ImmutableSet;
import de.maxikg.cookiecraft.CookieCraft;
import de.maxikg.cookiecraft.blocks.tileentity.MortarBlockTileEntity;
import de.maxikg.cookiecraft.common.model.Craftable;
import de.maxikg.cookiecraft.common.model.ModdedContent;
import de.maxikg.cookiecraft.common.model.PostRegistrationListener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Collection;
import java.util.Random;

/**
 * @author maxikg <me@maxikg.de>
 */
public class MortarBlock extends BlockContainer implements ModdedContent, Craftable, PostRegistrationListener {

    public static PropertyBool HAS_PESTLE = PropertyBool.create("pestle");

    public MortarBlock() {
        super(Material.circuits);

        setCreativeTab(CookieCraft.CREATIVE_TAB);
        setDefaultState(blockState.getBaseState().withProperty(HAS_PESTLE, false));
        setBlockBoundsForItemRender();
    }

    @Override
    public String getName() {
        return "mortar";
    }

    @Override
    public void setBlockBoundsForItemRender() {
        float height = 0.1875f;
        this.setBlockBounds(0.5F - height, 0.0F, 0.5F - height, 0.5F + height, height, 0.5F + height);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 3;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, HAS_PESTLE);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(HAS_PESTLE, false);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((Boolean) state.getValue(HAS_PESTLE)) ? 1 : 0;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && World.doesBlockHaveSolidTopSurface(worldIn, pos.down());
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        if (!World.doesBlockHaveSolidTopSurface(worldIn, pos.down())) {
            dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new MortarBlockTileEntity();
    }

    @Override
    public void postRegistration(FMLInitializationEvent event) {
        GameRegistry.registerTileEntity(MortarBlockTileEntity.class, "mortar_te");
    }

    @Override
    public Collection<IRecipe> getRecipes() {
        return ImmutableSet.<IRecipe>of(
                new ShapedRecipes(3, 2, new ItemStack[] {
                        new ItemStack(Items.quartz),
                        null,
                        new ItemStack(Items.quartz),
                        null,
                        new ItemStack(Items.quartz),
                        null
                }, new ItemStack(this))
        );
    }
}
