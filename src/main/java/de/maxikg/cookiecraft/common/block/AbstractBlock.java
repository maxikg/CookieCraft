package de.maxikg.cookiecraft.common.block;

import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author maxikg <me@maxikg.de>
 */
public abstract class AbstractBlock extends Block implements ModBlock {

    private final String name;

    public AbstractBlock(String name, Material material) {
        super(material);

        this.name = Preconditions.checkNotNull(name);

        setUnlocalizedName(name);
    }

    public final String getName() {
        return name;
    }
}
