package net.himeki.mcmt.mixin;

import it.unimi.dsi.fastutil.longs.Long2ByteMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.server.level.Ticket;
import net.minecraft.util.SortedArraySet;
import net.minecraft.server.level.ChunkTracker;
import net.minecraft.server.level.TickingTracker;

import net.himeki.mcmt.parallelised.fastutil.Long2ByteConcurrentHashMap;
import net.himeki.mcmt.parallelised.fastutil.Long2ObjectOpenConcurrentHashMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TickingTracker.class)
public abstract class TickingTrackerMixin extends ChunkTracker {
    @Shadow
    @Final
    @Mutable
    protected Long2ByteMap levels = new Long2ByteConcurrentHashMap();

    @Shadow
    @Final
    @Mutable
    private Long2ObjectOpenHashMap<SortedArraySet<Ticket<?>>> tickets = new Long2ObjectOpenConcurrentHashMap<>();

    protected TickingTrackerMixin(int i, int j, int k) {
        super(i, j, k);
    }
}