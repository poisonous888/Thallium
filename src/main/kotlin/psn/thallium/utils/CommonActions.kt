package psn.thallium.utils

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.util.Vector

fun setLocation(entity:Entity,loc:Location){
    entity.teleport(loc)
}
fun moveLocation(entity:Entity,loc:Location){
    entity.teleport(entity.location.add(loc))
}
fun moveVelocity(entity:Entity,vel:Vector){
    entity.teleport(entity.location.add(vel))
}

//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//

fun moveInFacingDirection(entity:Entity,multiplier:Double=1.0){
    velocityInFacingDirection(entity, multiplier)
    moveVelocity(entity,entity.velocity)
}
fun velocityInFacingDirection(entity:Entity,multiplier:Double=1.0){
    entity.velocity=getViewVector(entity.pitch,entity.yaw).multiply(multiplier)
}

//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//

fun moveTowardsEntity(entity:Entity,towards:Entity,multiplier:Double=1.0){
    velocityTowardsEntity(entity, towards,multiplier)
    moveVelocity(entity,entity.velocity)
}
fun velocityTowardsEntity(entity:Entity,towards:Entity,multiplier:Double=1.0){
    val diff=towards.location.subtract(entity.location).toVector()
    val len=diff.length()
    if(len!=0.0) {
        diff.multiply(multiplier/diff.length())
    }
    else{
        diff.multiply(multiplier)
    }
    entity.velocity=diff
}
