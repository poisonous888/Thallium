package psn.thallium.listeners

import com.destroystokyo.paper.event.server.ServerTickEndEvent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityUnleashEvent
import org.bukkit.scoreboard.Team
import psn.thallium.ThalliumEntry
import java.util.concurrent.CopyOnWriteArrayList

object CustomEntityListener:Listener{
    val active=CopyOnWriteArrayList<CustomEntity>()
    val noCollision:Team get(){
        val scoreboard=Bukkit.getScoreboardManager().mainScoreboard
        val name="NOCOLLISION"
        val team=scoreboard.getTeam(name)?:
            scoreboard.registerNewTeam(name).let{
                it.setOption(Team.Option.COLLISION_RULE,Team.OptionStatus.NEVER)
                it
            }
        return team
    }
    
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    
    @EventHandler
    fun onTick(event:ServerTickEndEvent){
        for(entity in active)entity.onTick(event)
    }
    @EventHandler
    fun onLeashBreak(event:EntityUnleashEvent){
        for(entity in active)if(entity.entities.contains(event.entity))event.isCancelled=true
    }
}
abstract class CustomEntity{
    constructor(){
        CustomEntityListener.active.add(this)
    }
    fun remove(){
        CustomEntityListener.active.remove(this)
        for(entity in entities) {
            entity.remove()
        }
    }
    val entities=mutableListOf<Entity>()
    inline fun <reified T:Entity> addEntity(location:Location,world:World,visualOnly:Boolean=false):T{
        val newEntity=world.spawn(location,T::class.java)
        entities.add(newEntity)
        if(visualOnly){
            newEntity.isInvulnerable=true
            newEntity.setNoPhysics(true)
            (newEntity as? LivingEntity)?.setAI(false)
            CustomEntityListener.noCollision.addEntity(newEntity)
        }
        return newEntity
    }
    open fun onTick(event:ServerTickEndEvent){}
    protected inline val sv get()=ThalliumEntry.mcServer
}