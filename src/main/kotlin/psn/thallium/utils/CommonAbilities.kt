package psn.thallium.utils

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.util.Vector
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.sign
import kotlin.math.sin

fun playSound(entity:Player,sound:Sound,volume:Float=1f,pitch:Float=1f){
    entity.playSound(entity.location,sound,volume, pitch)
}
val validTeleportBlocks=listOf(
    Material.AIR
)
fun teleportStraight(entity:Entity,blocks:Int,ether:Boolean=false){
    val startLoc=entity.location.add(0.0,1.62,0.0)
    val loc=voxelWalk(startLoc, blocks, entity.world,ether)
    
    loc.x+=0.5
    loc.y+=if(ether) 1.05 else 0.05
    loc.z+=0.5
    
    entity.teleport(loc)
    entity.fallDistance=0f

}
fun getForwardsBlock(start:Location,blocks:Int):Location{
    val lookVec=getViewVector(start.pitch,start.yaw)
    val end=start.clone().add(
        lookVec.x*blocks,
        lookVec.y*blocks,
        lookVec.z*blocks
    )
    return end
}
fun voxelWalk(start:Location,blocks:Int,world:World,ether:Boolean=false):Location{
    return voxelWalk(start, getForwardsBlock(start, blocks), world, ether)
}
fun voxelWalk(start: Location,end: Location,world:World,ether: Boolean=false):Location{
    val x0=start.x
    val y0=start.y
    val z0=start.z
    val x1=end.x
    val y1=end.y
    val z1=end.z
    
    var x=floor(x0)
    var y=floor(y0)
    var z=floor(z0)
    val endX=floor(x1)
    val endY=floor(y1)
    val endZ=floor(z1)
    
    val dirX = x1 - x0
    val dirY = y1 - y0
    val dirZ = z1 - z0
    
    val stepX = sign(dirX).toInt()
    val stepY = sign(dirY).toInt()
    val stepZ = sign(dirZ).toInt()
    
    val invDirX = if (dirX != 0.0) 1.0 / dirX else Double.MAX_VALUE
    val invDirY = if (dirY != 0.0) 1.0 / dirY else Double.MAX_VALUE
    val invDirZ = if (dirZ != 0.0) 1.0 / dirZ else Double.MAX_VALUE
    
    val tDeltaX = abs(invDirX * stepX)
    val tDeltaY = abs(invDirY * stepY)
    val tDeltaZ = abs(invDirZ * stepZ)
    
    var tMaxX = abs((x + max(stepX, 0) - x0) * invDirX)
    var tMaxY = abs((y + max(stepY, 0) - y0) * invDirY)
    var tMaxZ = abs((z + max(stepZ, 0) - z0) * invDirZ)
    
    var lastValidBlock=start
    
    repeat(1000) {
        val blockPos=Location(world, x, y, z)
        val id=world.getBlockAt(blockPos)
        
        //val isPassable=validTeleportBlocks.contains(id.type)
        val isPassable=id.isPassable
        
        if(!isPassable) {
            //found block
            return (if(ether)blockPos else lastValidBlock).setRotation(start.yaw,start.pitch)
        }
        //no blocks in the way
        if (x == endX && y == endY && z == endZ) {
            return Location(world,x,y,z,start.yaw,start.pitch)
        }
        
        //next step logic
        when {
            tMaxX <= tMaxY && tMaxX <= tMaxZ -> {
                tMaxX += tDeltaX
                x += stepX
            }
            tMaxY <= tMaxZ -> {
                tMaxY += tDeltaY
                y += stepY
            }
            else -> {
                tMaxZ += tDeltaZ
                z += stepZ
            }
        }
        //for non-etherwarp
        lastValidBlock=blockPos
    }
    IO.println("[THALLIUM ERROR] bad voxel walk @ CommonAbilities.kt")
    return start
}

fun getViewVector(pitch:Float,yaw:Float):Vector {
    val realXRot=pitch*(Math.PI.toFloat()/180f)
    val realYRot=-yaw*(Math.PI.toFloat()/180f)
    val yCos:Double=cos(realYRot.toDouble())
    val ySin:Double=sin(realYRot.toDouble())
    val xCos:Double=cos(realXRot.toDouble())
    val xSin:Double=sin(realXRot.toDouble())
    return Vector((ySin*xCos),(-xSin),(yCos*xCos))
}