package com.lenis0012.bukkit.loginsecurity.modules.general;

import com.lenis0012.bukkit.loginsecurity.LoginSecurity;
import com.lenis0012.bukkit.loginsecurity.commands.*;
import com.lenis0012.pluginutils.Module;
import org.bukkit.Bukkit;
import java.util.logging.Level;

public class GeneralModule extends Module<LoginSecurity> {
    private LocationMode locationMode;

    public GeneralModule(LoginSecurity plugin) {
        super(plugin);
    }

    @Override
    public void enable() {
        registerCommands();
        registerListeners();

        // This line is so alone :(  I feel bad for him
        this.locationMode = LocationMode.valueOf(LoginSecurity.getConfiguration().getLocation().toUpperCase());
    }

    @Override
    public void disable() {
    }

    public LocationMode getLocationMode() {
        return locationMode;
    }

    private void registerCommands() {
        logger().log(Level.INFO, "Registering commands...");
        register(new CommandLogin(plugin), "login");
        register(new CommandRegister(plugin), "register");
        register(new CommandChangePass(plugin), "changepassword");
        register(new CommandLogout(plugin), "logout");
        register(new CommandUnregister(plugin), "unregister");
        register(new CommandAdmin(plugin), "lac");
    }

    private void registerListeners() {
        logger().log(Level.INFO, "Registering listeners...");
        register(new PlayerListener(this));

        if(Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
            InventoryPacketListener.register(plugin);
        }
    }
}
