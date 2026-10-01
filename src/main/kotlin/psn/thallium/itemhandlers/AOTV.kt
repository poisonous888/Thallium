package psn.thallium.itemhandlers

import org.bukkit.Sound
import psn.thallium.listeners.CustomItem
import psn.thallium.listeners.PlayerAttack
import psn.thallium.listeners.PlayerUse
import psn.thallium.utils.playSound
import psn.thallium.utils.teleportStraight

object AOTV:CustomItem("aotv") {
    override fun onUse(event:PlayerUse):Boolean {
        if(event.player.isSneaking){
            teleportStraight(event.player,61)
            playSound(event.player,Sound.BLOCK_NOTE_BLOCK_PLING)
            return true
        }
        teleportStraight(event.player,12)
        playSound(event.player,Sound.ENTITY_ENDERMAN_TELEPORT)
        return true
    }
}