package psn.thallium.listeners

import net.kyori.adventure.text.TextComponent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import psn.thallium.ThalliumEntry
import psn.thallium.itemhandlers.AOTV
import psn.thallium.itemhandlers.Lasso

object CustomItemListener:Listener{
    val items=listOf(
        Lasso,
        AOTV,
    )
    val itemMap=items.associateBy{it.itemId}
    
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    
    @EventHandler
    fun onAttack(event:PlayerAttack){
        if(mapToCustomItem(event.player.inventory.itemInMainHand)?.onAttack(event)?:return)event.canceled=true
    }
    @EventHandler
    fun onUse(event:PlayerUse){
        if(mapToCustomItem(event.player.inventory.itemInMainHand)?.onUse(event)?:return)event.canceled=true
        if(mapToCustomItem(event.player.inventory.itemInOffHand)?.onUse(event)?:return)event.canceled=true
    }
    fun mapToCustomItem(item:ItemStack):CustomItem?{
        if(item.itemMeta?.hasItemName()?:return null)
        return itemMap[(item.itemMeta.itemName() as TextComponent).content()]
        return null
    }
}
abstract class CustomItem{
    constructor(id:String){
        itemId=id
    }
    val itemId:String
    
    open fun onAttack(event:PlayerAttack):Boolean{return false}
    open fun onUse(event:PlayerUse):Boolean{return false}
    
    protected inline val sv get()=ThalliumEntry.mcServer
}