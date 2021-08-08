package by.jackraidenph.aiot.util;

import by.jackraidenph.aiot.items.AIOTItem;
import by.jackraidenph.aiot.items.Registry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.TieredItem;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Map;

public class NetheriteAIOTRecipe extends SpecialRecipe {

    public NetheriteAIOTRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    @Override
    public boolean matches(CraftingInventory container, World level) {
        int netherite = 0;
        int diamond_aiot = 0;

        for (int j = 0; j < container.getContainerSize(); ++j) {
            ItemStack itemstack = container.getItem(j);
            if (!itemstack.isEmpty()) {
                netherite += itemstack.getItem() == Items.NETHERITE_INGOT ? itemstack.getCount() : 0;
                diamond_aiot += itemstack.getItem() == Registry.DIAMOND_AIOT.get() ? 1 : 0;

                if (itemstack.getItem() != Registry.DIAMOND_AIOT.get())
                    if ((itemstack.getItem() != Items.NETHERITE_INGOT))
                        return false;
            }
        }

        return (diamond_aiot == 1) & (netherite >= 5);
    }

    @Override
    public ItemStack assemble(CraftingInventory container) {
        Map<Enchantment, Integer> map = null;
        int damage = 0;

        for (int j = 0; j < container.getContainerSize(); ++j) {
            ItemStack itemstack = container.getItem(j);

            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof AIOTItem)
                    if (((TieredItem) itemstack.getItem()).getTier() == ItemTier.DIAMOND) {
                        map = EnchantmentHelper.getEnchantments(itemstack);
                        damage = itemstack.getDamageValue();
                    }
            }
        }

        ItemStack netheriteAIOT = new ItemStack(Registry.NETHERITE_AIOT.get());
        if ((map != null) && !map.isEmpty())
            EnchantmentHelper.setEnchantments(map, netheriteAIOT);
        netheriteAIOT.setDamageValue(Math.min((int) (damage * 1.3 * 1.5), netheriteAIOT.getMaxDamage()));

        return netheriteAIOT;
    }


    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory container) {
        int netherite = 0;
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = container.getItem(i);
            netherite += itemstack.getItem() == Items.NETHERITE_INGOT ? itemstack.getCount() : 0;
        }
        nonnulllist.set(0, new ItemStack(Items.NETHERITE_INGOT, netherite - 5));
        container.clearContent();
        return nonnulllist;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public SpecialRecipeSerializer<?> getSerializer() {
        return Registry.NETHERITE_AIOT_RECIPE.get();
    }
}
