package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import elucent.albedo.lighting.ILightProvider;
import elucent.albedo.lighting.Light;
import kanethornwyrd.mods.norsecraft.common.lib.BaseRunes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface="elucent.albedo.lighting.ILightProvider", modid="albedo")
public class TileRune extends TileMod implements ILightProvider {

    public static final String TAG_RUNE_TYPE = "runeType";
    String type = BaseRunes.collection.keySet().toArray()[0].toString();

    @Override
    public void writePacketNBT(NBTTagCompound nbt) {
        nbt.setString(TAG_RUNE_TYPE, type.toString());
    }

    @Override
    public void readPacketNBT(NBTTagCompound nbt) {
        if(null != nbt && nbt.hasKey(TAG_RUNE_TYPE)) this.type = nbt.getString(TAG_RUNE_TYPE);
    }

    private final Float circleX(Float radius, Float angleDeg){
        return radius * (float)Math.cos(Math.toRadians(angleDeg)) + this.getPos().getX() + radius;
    }
    private final Float circleZ(Float radius, Float angleDeg){
        return radius * (float)Math.sin(Math.toRadians(angleDeg)) +  this.pos.getZ() + radius;
    }

    @Override
    @Optional.Method(modid="albedo")
    @SideOnly(Side.CLIENT)
    public Light provideLight() {
        return Light.builder()
                .pos(
                        this.pos.getX() +0.5,
                        this.pos.getY() +0.5,
                        this.pos.getZ() +0.5
                )
                .color(
                        BaseRunes.collection.get(this.getRuneName().toUpperCase()).getColor(),
                        false
                )
                .radius(1f)
                .build();
    }

    public String getRuneName() {
        return this.type;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void update() {
    }
}
