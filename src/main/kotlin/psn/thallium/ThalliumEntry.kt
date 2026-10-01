package psn.thallium

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import psn.thallium.listeners.CustomEntityListener
import psn.thallium.listeners.CustomItemListener
import psn.thallium.listeners.RegCustomEvents

class ThalliumEntry:JavaPlugin() {
    override fun onDisable(){}
    val listeners=listOf(
        RegCustomEvents,
        CustomItemListener,
        CustomEntityListener,
    )
    
    override fun onEnable() {
        for(listener in listeners) server.pluginManager.registerEvents(listener,this)
    }
    companion object{
        val mcServer=Bukkit.getServer()
    }
}