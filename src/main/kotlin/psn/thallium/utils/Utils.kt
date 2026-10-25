package psn.thallium.utils

import psn.thallium.ThalliumEntry

fun schedule(ticks: Int, task: () -> Unit) {
    ThalliumEntry.mcServer.scheduler.runTaskLater(ThalliumEntry.plugin,task,ticks.toLong())
}