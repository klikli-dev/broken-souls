package tao.brokensouls.data;

import mcjty.lostcities.varia.ChunkCoord;
import tao.brokensouls.config.Config;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

public class LostChunkData {

    private boolean haunted;
    private int maxMobs;
    private int numberKilled;

    public LostChunkData(ChunkCoord cc) {
        Random random = new Random(cc.getDimension()*899812591L + cc.getChunkX()*916023653L + cc.getChunkZ()*797003437L);
        random.nextFloat();
        random.nextFloat();
        haunted = random.nextFloat() < Config.HAUNTED_CHANCE;
        maxMobs = random.nextInt(Config.MAX_MOBS - Config.MIN_MOBS+1) + Config.MIN_MOBS;
        numberKilled = 0;
    }

    public boolean isHaunted() {
        return haunted;
    }

    public int getMaxMobs() {
        return maxMobs;
    }

    public int getNumberKilled() {
        return numberKilled;
    }

    public void newKill() {
        numberKilled++;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        haunted = nbt.getBoolean("haunted");
        maxMobs = nbt.getInteger("max");
        numberKilled = nbt.getInteger("killed");
    }

    public void writeToNBT(NBTTagCompound compound) {
        compound.setBoolean("haunted", haunted);
        compound.setInteger("max", maxMobs);
        compound.setInteger("killed", numberKilled);
    }
}
