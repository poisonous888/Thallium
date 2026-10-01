package psn.thallium.itemhandlers

import org.bukkit.entity.EntityType
import org.bukkit.entity.Sheep
import psn.thallium.entityhandlers.LassoHead
import psn.thallium.listeners.CustomItem
import psn.thallium.listeners.PlayerAttack
import psn.thallium.listeners.PlayerUse
import psn.thallium.utils.voxelWalk

object Lasso:CustomItem("lasso") {
    override fun onAttack(event:PlayerAttack):Boolean {
        val loc=voxelWalk(event.player.location,10,event.player.world,true)
        val entity=event.player.world.spawnEntity(loc.add(0.0,1.0,0.0),EntityType.SHEEP) as Sheep
        entity.setLeashHolder(event.player)
        return true
    }
    
    override fun onUse(event:PlayerUse):Boolean {
        LassoHead(event.player)
        return true
    }
}