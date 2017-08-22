package kanethornwyrd.mods.norsecraft.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;

public class CommandHelloWorld extends CommandBase {
    @Nonnull
    @Override
    public String getName() {
        return "norsecraft-helloworld";
    }

    @Nonnull
    @Override
    public String getUsage(@Nonnull ICommandSender sender) {
        return "<entry>";
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) {
        if(sender instanceof EntityPlayer) {
            sender.sendMessage(new TextComponentTranslation("norsecraftmisc.helloworld").setStyle(new Style().setColor(TextFormatting.GREEN)));
        }
    }


    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer;
    }
}
