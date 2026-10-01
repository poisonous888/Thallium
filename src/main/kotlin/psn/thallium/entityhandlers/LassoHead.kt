package psn.thallium.entityhandlers

import com.destroystokyo.paper.event.server.ServerTickEndEvent
import org.bukkit.entity.Player
import org.bukkit.entity.Sheep
import psn.thallium.listeners.CustomEntity
import psn.thallium.utils.moveInFacingDirection
import psn.thallium.utils.moveTowardsEntity

class LassoHead:CustomEntity{
    constructor(plr:Player){
        player=plr
        mover=addEntity<Sheep>(plr.location,plr.world,true)
        mover.setLeashHolder(player)
    }
    var timer=0
    val mover:Sheep
    val player:Player
    override fun onTick(event:ServerTickEndEvent) {
        timer++
        if(timer<20){
            moveInFacingDirection(mover)
        }
        else if(timer<60){
            moveTowardsEntity(mover,player,1.5)
        }
        else{
            remove()
            return
        }
        val nearby=mover.getNearbyEntities(1.0,1.0,1.0)
        for(mob in nearby){
            if(mob == player){
                if(timer>20)remove()
            }
            else{
                mover.addPassenger(mob)
            }
        }
    }
}