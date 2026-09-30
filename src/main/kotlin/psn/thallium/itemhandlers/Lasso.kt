package psn.thallium.itemhandlers

import psn.thallium.listeners.CustomItem
import psn.thallium.listeners.PlayerAttack

object Lasso:CustomItem("lasso") {
    override fun onAttack(event:PlayerAttack):Boolean {
        event.player.sendMessage("attacked")
        return true
    }
}