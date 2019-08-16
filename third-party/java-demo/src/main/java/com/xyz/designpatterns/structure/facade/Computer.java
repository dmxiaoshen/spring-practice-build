package com.xyz.designpatterns.structure.facade;

/**
 * Created by hzhsg on 2018/5/4.
 */
public class Computer {

    private CPU cpu;

    private Disk disk;

    private Memory memory;

    public Computer(CPU cpu, Disk disk, Memory memory) {
        this.cpu = cpu;
        this.disk = disk;
        this.memory = memory;
    }

    public void start(){
        System.out.println("Computer start");
        cpu.start();
        memory.start();
        disk.start();
    }

    public  void shutdown(){
        System.out.println("Computer shutdown");
        disk.shutdown();
        memory.shutdown();
        cpu.shutdown();
    }
}
