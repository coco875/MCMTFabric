package net.himeki.mcmt.fabric;


import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import net.himeki.mcmt.MCMT;
import net.himeki.mcmt.commands.ConfigCommand;
import net.himeki.mcmt.commands.StatsCommand;

public class MCMTFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MCMT.init();

        // Listener reg begin
        ServerLifecycleEvents.SERVER_STARTED.register(server -> StatsCommand.resetAll());
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> ConfigCommand.register(dispatcher));

    }
}