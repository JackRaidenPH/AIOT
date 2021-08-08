package by.jackraidenph.aiot.items;

import by.jackraidenph.aiot.Main;
import by.jackraidenph.aiot.util.AIOTRecipe;
import by.jackraidenph.aiot.util.NetheriteAIOTRecipe;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
    private static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MOD_ID);

    public static final RegistryObject<AIOTItem> WOODEN_AIOT = ITEMS.register("wooden_aiot", () -> new AIOTItem(4f, -2.7f, Tiers.WOOD, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<AIOTItem> STONE_AIOT = ITEMS.register("stone_aiot", () -> new AIOTItem(4f, -2.7f, Tiers.STONE, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<AIOTItem> IRON_AIOT = ITEMS.register("iron_aiot", () -> new AIOTItem(4f, -2.7f, Tiers.IRON, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<AIOTItem> GOLDEN_AIOT = ITEMS.register("golden_aiot", () -> new AIOTItem(4f, -2.7f, Tiers.GOLD, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<AIOTItem> DIAMOND_AIOT = ITEMS.register("diamond_aiot", () -> new AIOTItem(4f, -2.7f, Tiers.DIAMOND, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<AIOTItem> NETHERITE_AIOT = ITEMS.register("netherite_aiot", () -> new AIOTItem(4f, -2.7f, Tiers.NETHERITE, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).fireResistant()));

    public static final RegistryObject<RecipeSerializer<AIOTRecipe>> AIOT_RECIPE = RECIPES.register("crafting_special_aiot_recipe", () -> new SimpleRecipeSerializer<>(AIOTRecipe::new));
    public static final RegistryObject<RecipeSerializer<NetheriteAIOTRecipe>> NETHERITE_AIOT_RECIPE = RECIPES.register("crafting_special_netherite_aiot_recipe", () -> new SimpleRecipeSerializer<>(NetheriteAIOTRecipe::new));

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RECIPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
