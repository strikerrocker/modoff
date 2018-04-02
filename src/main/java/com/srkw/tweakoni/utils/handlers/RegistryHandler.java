package com.srkw.tweakoni.utils.handlers;

import com.srkw.tweakoni.capabilities.DefaultShiftHandler;
import com.srkw.tweakoni.capabilities.ShiftHandler;
import com.srkw.tweakoni.capabilities.Storage;
import com.srkw.tweakoni.events.InteractEvent;
import com.srkw.tweakoni.init.BlockInit;
import com.srkw.tweakoni.init.ItemInit;
import com.srkw.tweakoni.network.PacketHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {

    @CapabilityInject(ShiftHandler.class)
    public static final Capability<ShiftHandler> SHIFT_HANDLER_CAPABILITY = null;

    public static void preInitRegistries() {
    }

    public static void initRegistries() {
        MinecraftForge.EVENT_BUS.register(new InteractEvent());
        PacketHandler.registerMessages("tweakoni");
        CapabilityManager.INSTANCE.register(ShiftHandler.class, new Storage(), DefaultShiftHandler.class);
    }

    public static void postInitRegistries() {
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        ItemInit.register(event.getRegistry());
        BlockInit.registerItemBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        BlockInit.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        ItemInit.registerModels();
        BlockInit.registerModels();
    }
}
