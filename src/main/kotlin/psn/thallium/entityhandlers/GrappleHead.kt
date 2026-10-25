package psn.thallium.entityhandlers

import com.destroystokyo.paper.event.server.ServerTickEndEvent
import org.bukkit.block.BlockType
import org.bukkit.entity.Breeze
import org.bukkit.entity.Player
import psn.thallium.listeners.CustomEntity
import psn.thallium.utils.moveInFacingDirection

class GrappleHead:CustomEntity{
    constructor(plr:Player){
        player=plr
        mover=addEntity<Breeze>(plr.location,plr.world,true)
        mover.setLeashHolder(player)
    }
    var timer=0
    var attached=false
    val mover:Breeze
    val player:Player
    override fun onTick(event:ServerTickEndEvent) {
        if(attached){
        
        }
        else{
            moveInFacingDirection(mover)
            
            if(mover.world.getBlockAt(mover.location)!=BlockType.AIR){
                attached=true
                return
            }
            
            
            if(++timer>80){
                remove()
            }
        }
    }
}