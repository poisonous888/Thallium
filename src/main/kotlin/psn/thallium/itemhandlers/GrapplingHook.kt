package psn.thallium.itemhandlers

import psn.thallium.listeners.CustomItem
import psn.thallium.listeners.PlayerUse
import psn.thallium.utils.velocityTowardsEntity

object GrapplingHook:CustomItem("grapplinghook") {
    override fun onUse(event:PlayerUse):Boolean {
        if(event.player.fishHook!=null){
            velocityTowardsEntity(event.player,event.player.fishHook?:return false,4.0)
            event.player.velocity=event.player.velocity.setY(1)
        }
        return true
    }
}