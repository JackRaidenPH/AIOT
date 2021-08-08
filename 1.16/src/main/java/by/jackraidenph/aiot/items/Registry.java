package by.jackraidenph.aiot.items;


import by.jackraidenph.aiot.Main;
import by.jackraidenph.aiot.util.AIOTRecipe;
import by.jackraidenph.aiot.util.NetheriteAIOTRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
    private static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MOD_ID);

    public static final RegistryObject<AIOTItem> WOODEN_AIOT = ITEMS.register("wooden_aiot", () -> new AIOTItem(4f, -2.7f, ItemTier.WOOD, new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<AIOTItem> STONE_AIOT = ITEMS.register("stone_aiot", () -> new AIOTItem(4f, -2.7f, ItemTier.STONE, new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<AIOTItem> IRON_AIOT = ITEMS.register("iron_aiot", () -> new AIOTItem(4f, -2.7f, ItemTier.IRON, new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<AIOTItem> GOLDEN_AIOT = ITEMS.register("golden_aiot", () -> new AIOTItem(4f, -2.7f, ItemTier.GOLD, new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<AIOTItem> DIAMOND_AIOT = ITEMS.register("diamond_aiot", () -> new AIOTItem(4f, -2.7f, ItemTier.DIAMOND, new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<AIOTItem> NETHERITE_AIOT = ITEMS.register("netherite_aiot", () -> new AIOTItem(4f, -2.7f, ItemTier.NETHERITE, new Item.Properties().tab(ItemGroup.TAB_TOOLS).fireResistant()));

    public static final RegistryObject<SpecialRecipeSerializer<AIOTRecipe>> AIOT_RECIPE = RECIPES.register("crafting_special_aiot_recipe", () -> new SpecialRecipeSerializer<>(AIOTRecipe::new));
    public static final RegistryObject<SpecialRecipeSerializer<NetheriteAIOTRecipe>> NETHERITE_AIOT_RECIPE = RECIPES.register("crafting_special_netherite_aiot_recipe", () -> new SpecialRecipeSerializer<>(NetheriteAIOTRecipe::new));

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RECIPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
