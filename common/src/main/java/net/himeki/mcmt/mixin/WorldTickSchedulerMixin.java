package net.himeki.mcmt.mixin;

import it.unimi.dsi.fastutil.longs.Long2LongMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import net.minecraft.world.tick.ChunkTickScheduler;
import net.minecraft.world.tick.OrderedTick;
import net.minecraft.world.tick.QueryableTickScheduler;
import net.minecraft.world.tick.WorldTickScheduler;

import net.himeki.mcmt.parallelised.ConcurrentCollections;
import net.himeki.mcmt.parallelised.fastutil.Long2LongConcurrentHashMap;
import net.himeki.mcmt.parallelised.fastutil.Long2ObjectOpenConcurrentHashMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

@Mixin(WorldTickScheduler.class)
public abstract class WorldTickSchedulerMixin<T> implements QueryableTickScheduler<T> {
    @Shadow
    @Final
    @Mutable
    private Long2ObjectMap<ChunkTickScheduler<T>> chunkTickSchedulers = new Long2ObjectOpenConcurrentHashMap<>();

//    @Shadow
//    @Final
//    private final Long2LongMap nextTriggerTickByChunkPos = new Long2LongConcurrentHashMap(9223372036854775807L);

    @Shadow
    @Final
    @Mutable
    private  Long2LongMap nextTriggerTickByChunkPos = new Long2LongConcurrentHashMap(Long.MAX_VALUE);//Util.make(new Long2LongConcurrentHashMap(Long.MAX_VALUE), map -> map.defaultReturnValue(Long.MAX_VALUE));;

    @Shadow
    @Final
    @Mutable
    private Queue<ChunkTickScheduler<T>> tickableChunkTickSchedulers = ConcurrentCollections.newArrayDeque();

    @Shadow
    @Final
    @Mutable
    private Queue<OrderedTick<T>> tickableTicks = ConcurrentCollections.newArrayDeque();

    @Shadow
    @Final
    @Mutable
    private List<OrderedTick<T>> tickedTicks = new CopyOnWriteArrayList<>();
}