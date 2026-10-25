package psn.thallium

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import psn.thallium.listeners.CustomEntityListener
import psn.thallium.listeners.CustomItemListener
import psn.thallium.listeners.RegCustomEvents

//TODO this crashes the server when an error occurs
private const val DEVELOPMENT=false
fun <T>processError(e:Exception,msg:String=""):T{
    if(DEVELOPMENT){
        ThalliumEntry.mcServer.dispatchCommand(Bukkit.getConsoleSender(),"stop")
    }
    IO.println(e)
    IO.println(msg)
    @Suppress("unchecked_cast")
    return Any() as T
}
class ThalliumEntry:JavaPlugin() {
    override fun onDisable(){}
    val listeners=listOf(
        RegCustomEvents,
        CustomItemListener,
        CustomEntityListener,
    )
    override fun onEnable() {
        for(listener in listeners) server.pluginManager.registerEvents(listener,this)
        plugin=this
    }
    companion object{
        val mcServer=Bukkit.getServer()
        lateinit var plugin:Plugin
    }
}