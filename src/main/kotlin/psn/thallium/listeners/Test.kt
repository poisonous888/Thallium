package psn.thallium.listeners

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInputEvent
import org.bukkit.inventory.ItemStack
import psn.thallium.utils.PlayerAttack
import psn.thallium.utils.PlayerUse

object Test:Listener{
//    @EventHandler
//    private fun playerMove(event:PlayerInputEvent){
//        event.player.sendMessage("player move")
//    }
//    @EventHandler
//    private fun playerAttack(event:PlayerAttack){
//        event.player.sendMessage("player attack")
//    }
    @EventHandler
    private fun playerUse(event:PlayerUse){
        val mainItem=event.player.inventory.itemInMainHand
        //event.player.chat(mainItem.toString())
        if(mainItem.type==Material.DIAMOND_SWORD){
            val location=event.player.location
            location.y+=3
            event.player.teleport(location)
        }
    }
}