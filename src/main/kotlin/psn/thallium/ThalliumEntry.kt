package psn.thallium

import org.bukkit.plugin.java.JavaPlugin
import psn.thallium.listeners.Test
import psn.thallium.utils.RegCustomEvents

class ThalliumEntry:JavaPlugin() {
    override fun onDisable(){}
    val listeners=listOf(
        RegCustomEvents,
        Test,
    )
    
    override fun onEnable() {
        for(listener in listeners) server.pluginManager.registerEvents(listener,this)
    }
}