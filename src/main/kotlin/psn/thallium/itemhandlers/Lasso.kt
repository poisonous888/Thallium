package psn.thallium.itemhandlers

import psn.thallium.entityhandlers.GrappleHead
import psn.thallium.entityhandlers.LassoHead
import psn.thallium.listeners.CustomItem
import psn.thallium.listeners.PlayerAttack
import psn.thallium.listeners.PlayerUse

object Lasso:CustomItem("lasso") {
    override fun onAttack(event:PlayerAttack):Boolean {
        GrappleHead(event.player)
        return true
    }
    override fun onUse(event:PlayerUse):Boolean {
        LassoHead(event.player)
        return true
    }
}