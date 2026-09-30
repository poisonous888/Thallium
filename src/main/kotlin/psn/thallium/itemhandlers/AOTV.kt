package psn.thallium.itemhandlers

import org.bukkit.Sound
import psn.thallium.listeners.CustomItem
import psn.thallium.listeners.PlayerUse
import psn.thallium.utils.playSound
import psn.thallium.utils.teleportStraight

object AOTV:CustomItem("aotv") {
    override fun onUse(event:PlayerUse):Boolean {
        val loc=teleportStraight(event.player,12)
        playSound(event.player,Sound.ENTITY_ENDERMAN_TELEPORT)
        return true
    }
}