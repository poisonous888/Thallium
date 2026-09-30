package psn.thallium.listeners

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.*
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent

object RegCustomEvents:Listener {
    @EventHandler fun attackEntity(event:PrePlayerAttackEntityEvent) {
        PlayerAttack(event.player,event.attacked,null).callEvent()
    }
    
    @EventHandler fun useEntity(event:PlayerInteractEntityEvent) {
        PlayerUse(event.player,event.rightClicked,null).callEvent()
    }
    
    @EventHandler fun attackUseAirBlock(event:PlayerInteractEvent) {
        when(event.action) {
            Action.LEFT_CLICK_BLOCK,Action.LEFT_CLICK_AIR->PlayerAttack(event.player,null,event.clickedBlock).callEvent()
            Action.RIGHT_CLICK_BLOCK,Action.RIGHT_CLICK_AIR->PlayerUse(event.player,null,event.clickedBlock).callEvent()
            else-> {}
        }
    }
}

class PlayerAttack : Cancellable,Event {
    constructor(plr:Player,trg:Entity?,blk:Block?){
        player=plr
        target=trg
        block=blk
    }
    val player:Player
    val target:Entity?
    val block:Block?
    
    var canceled=false
    override fun isCancelled()=canceled
    override fun setCancelled(cancel:Boolean)=run{canceled=cancel}
    
    override fun getHandlers()=HANDLER_LIST
    companion object{
        private val HANDLER_LIST=HandlerList()
        @JvmStatic fun getHandlerList()=HANDLER_LIST
    }
}

class PlayerUse : Cancellable,Event {
    constructor(plr:Player,trg:Entity?,blk:Block?){
        player=plr
        target=trg
        block=blk
    }
    val player:Player
    val target:Entity?
    val block:Block?
    
    var canceled=false
    override fun isCancelled()=canceled
    override fun setCancelled(cancel:Boolean)=run{canceled=cancel}
    
    override fun getHandlers()=HANDLER_LIST
    companion object{
        private val HANDLER_LIST=HandlerList()
        @JvmStatic fun getHandlerList()=HANDLER_LIST
    }
}
