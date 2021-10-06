package me.candiesjar.helium.workloads;

import com.google.common.collect.Queues;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Objects;

@Getter
public class WorkloadThread implements Runnable {
    private final ArrayDeque<Workload> workloads;
    private static final int MAX_MS_PER_TICK = 5;

    public WorkloadThread() {
        workloads = Queues.newArrayDeque();
    }

    public void add(Workload workload) {
        workloads.add(workload);
    }

    @Override
    public void run() {
        long stopTime = System.currentTimeMillis() + MAX_MS_PER_TICK;
        if(!workloads.isEmpty() && System.currentTimeMillis() <= stopTime) {
            Objects.requireNonNull(workloads.poll()).compute();
        }
    }
}
