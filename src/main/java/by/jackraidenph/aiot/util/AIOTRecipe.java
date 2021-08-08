package by.jackraidenph.aiot.util;

import by.jackraidenph.aiot.items.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Map;

public class AIOTRecipe extends CustomRecipe {

    private static final Map<Tier, Item> map = Map.of(
            Tiers.WOOD, Registry.WOODEN_AIOT.get(),
            Tiers.STONE, Registry.STONE_AIOT.get(),
            Tiers.GOLD, Registry.GOLDEN_AIOT.get(),
            Tiers.IRON, Registry.IRON_AIOT.get(),
            Tiers.DIAMOND, Registry.DIAMOND_AIOT.get(),
            Tiers.NETHERITE, Registry.NETHERITE_AIOT.get()
    );

    public AIOTRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
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
    public ItemStack assemble(CraftingContainer container) {
        ArrayList<Integer> durability = new ArrayList<>();
        Tier tier = null;

        for (int j = 0; j < container.getContainerSize(); ++j) {
            ItemStack itemstack = container.getItem(j);

            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof TieredItem tieredItem) {
                    if (tier != null && (tier != tieredItem.getTier()))
                        return ItemStack.EMPTY;
                    tier = tieredItem.getTier();
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

        if ((durability.size() < 5) | (tier == null) | (tier == Tiers.NETHERITE))
            return ItemStack.EMPTY;


        ItemStack aiot = new ItemStack(map.get(tier));
        int average = (int) durability.stream().mapToDouble(Integer::doubleValue).average().getAsDouble();
        aiot.setDamageValue((int) (average * 2 * 1.25));
        return aiot;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 5;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Registry.AIOT_RECIPE.get();
    }
}
