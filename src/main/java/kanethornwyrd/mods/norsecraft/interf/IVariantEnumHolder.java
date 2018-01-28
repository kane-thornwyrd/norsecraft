package kanethornwyrd.mods.norsecraft.interf;

import net.minecraft.util.IStringSerializable;


public interface IVariantEnumHolder<T extends Enum<T> & IStringSerializable> {
String HEADER = "variant";

Class<T> getVariantEnum();
}