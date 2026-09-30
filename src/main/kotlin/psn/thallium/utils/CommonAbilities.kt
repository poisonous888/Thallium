package psn.thallium.utils

import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin

fun playSound(entity:Player,sound:Sound,volume:Float=1f,pitch:Float=1f){
    entity.playSound(entity.location,sound,volume, pitch)
}
fun teleportStraight(entity:Entity,blocks:Int):Location{
    val loc=entity.location
    
    val lookVec=calculateViewVector(loc.pitch,loc.yaw)
    
    loc.x+=lookVec.first*blocks
    loc.y+=lookVec.second*blocks
    loc.z+=lookVec.third*blocks
    
    loc.x=floor(loc.x)+0.5
    loc.y=floor(loc.y)+0.1
    loc.z=floor(loc.z)+0.5
    
    entity.teleport(loc)
    entity.fallDistance=0f
    return loc
}
fun calculateViewVector(xRot:Float,yRot:Float):Triple<Double,Double,Double> {
    val realXRot=xRot*(Math.PI.toFloat()/180f)
    val realYRot=-yRot*(Math.PI.toFloat()/180f)
    val yCos:Double=cos(realYRot.toDouble())
    val ySin:Double=sin(realYRot.toDouble())
    val xCos:Double=cos(realXRot.toDouble())
    val xSin:Double=sin(realXRot.toDouble())
    return Triple((ySin*xCos),(-xSin),(yCos*xCos))
}