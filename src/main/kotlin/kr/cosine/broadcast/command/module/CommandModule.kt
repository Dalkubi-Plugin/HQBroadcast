package kr.cosine.broadcast.command.module

import kr.cosine.broadcast.command.BroadcastCommand
import kr.cosine.broadcast.command.HelpCommand
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.bukkit.core.component.module.Module
import kr.hqservice.framework.bukkit.core.component.module.Setup

@Module
class CommandModule(
    private val plugin: HQBukkitPlugin,
    private val broadcastCommand: BroadcastCommand,
    private val HelpCommand: HelpCommand
) {
    @Setup
    fun setup() {
        plugin.getCommand("공지")?.setExecutor(broadcastCommand)
        plugin.getCommand("도움요청")?.setExecutor(HelpCommand)
    }
}