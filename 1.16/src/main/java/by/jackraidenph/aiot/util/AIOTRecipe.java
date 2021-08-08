package by.jackraidenph.aiot.util;

import by.jackraidenph.aiot.items.Registry;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AIOTRecipe extends SpecialRecipe {

    public AIOTRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    private static final Map<ItemTier, Item> map() {
        Map<ItemTier, Item> map = new HashMap<>();
        map.put(ItemTier.WOOD, Registry.WOODEN_AIOT.get());
        map.put(ItemTier.STONE, Registry.STONE_AIOT.get());
        map.put(ItemTier.GOLD, Registry.GOLDEN_AIOT.get());
        map.put(ItemTier.IRON, Registry.IRON_AIOT.get());
        map.put(ItemTier.DIAMOND, Registry.DIAMOND_AIOT.get());
        map.put(ItemTier.NETHERITE, Registry.NETHERITE_AIOT.get());
        return map;
    }

    @Override
    public boolean matches(CraftingInventory container, World level) {
        boolean[] tools = new boolean[]{false, false, false, false, false};

        for (int j = 0; j < container.getContainerSize(); ++j) {
            ItemStack itemstack = container.getItem(j);

            if (!itemstack.isEmpty()) {

                if (!(itemstack.getItem() instanceof TieredItem))
                    return false;

                if (itemstack.getItem() instanceof SwordItem)
                    tools[0] = true;
                else if (itemstack.getItem() instanceof PickaxeItem)
                    tools[1] = true;
                else if (itemstack.getItem() instanceof AxeItem)
                    tools[2] = true;
                else if (itemstack.getItem() instanceof ShovelItem)
                    tools[3] = true;
                else if (itemstack.getItem() instanceof HoeItem)
                    tools[4] = true;
            }
        }

        return (tools[0] & tools[1] & tools[2] & tools[3] & tools[4]);
    }

    @Override
    public ItemStack assemble(CraftingInventory container) {
        ArrayList<Integer> durability = new ArrayList<>();
        ItemTier tier = null;

        for (int j = 0; j < container.getContainerSize(); ++j) {
            ItemStack itemstack = container.getItem(j);

            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof TieredItem) {
                    if (tier != null && (tier != ((TieredItem) itemstack.getItem()).getTier()))
                        return ItemStack.EMPTY;
                    tier = (ItemTier) ((TieredItem) itemstack.getItem()).getTier();
                } else
                    return ItemStack.EMPTY;

                if (itemstack.getItem() instanceof SwordItem)
                    durability.add(itemstack.getDamageValue());
                else if (itemstack.getItem() instanceof PickaxeItem)
                    durability.add(itemstack.getDamageValue());
                else if (itemstack.getItem() instanceof AxeItem)
                    durability.add(itemstack.getDamageValue());
                else if (itemstack.getItem() instanceof ShovelItem)
                    durability.add(itemstack.getDamageValue());
                else if (itemstack.getItem() instanceof HoeItem)
                    durability.add(itemstack.getDamageValue());
            }
        }

        if ((durability.size() < 5) | (tier == null) | (tier == ItemTier.NETHERITE))
            return ItemStack.EMPTY;


        ItemStack aiot = new ItemStack(map().get(tier));
        int average = (int) durability.stream().mapToDouble(Integer::doubleValue).average().getAsDouble();
        aiot.setDamageValue((int) (average * 2 * 1.25));
        return aiot;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 5;
    }

    @Override
    public SpecialRecipeSerializer<?> getSerializer() {
        return Registry.AIOT_RECIPE.get();
    }
}
