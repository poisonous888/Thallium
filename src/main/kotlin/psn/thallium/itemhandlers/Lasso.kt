package psn.thallium.itemhandlers

import psn.thallium.listeners.CustomItem
import psn.thallium.listeners.PlayerAttack
import psn.thallium.listeners.PlayerUse
import psn.thallium.utils.getForwardsBlock
import psn.thallium.utils.teleportStraight

object Lasso:CustomItem("lasso") {
    override fun onAttack(event:PlayerAttack):Boolean {
        event.player.sendMessage("attacked")
        return true
    }
    
    override fun onUse(event:PlayerUse):Boolean {
        event.player.teleport(getForwardsBlock(event.player.location,12))
        return true
    }
}