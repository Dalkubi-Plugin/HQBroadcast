package kr.cosine.broadcast.command

import kr.cosine.broadcast.config.SettingConfig
import kr.hqservice.framework.bukkit.core.extension.colorize
import kr.hqservice.framework.global.core.component.Bean
import kr.hqservice.framework.netty.api.PacketSender
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.*
import org.bukkit.entity.Player

@Bean
class HelpCommand(
    private val packetSender: PacketSender,
    private val settingConfig: SettingConfig
) : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("§c메시지를 입력해주세요.")
            return true
        }
        val message = args.copyOf().joinToString(" ").colorize()
        val helpFormat = settingConfig.getHelpFormat {
            it.replace("%message%", message).replace("%player%", sender.name).replace("%display%",(sender as Player).displayName ?: sender.name )
        }.joinToString("\n").run(::TextComponent)
        packetSender.broadcast(helpFormat, true)
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): List<String> {
        return emptyList()
    }
}


@Bean
class BroadcastCommand(
    private val packetSender: PacketSender,
    private val settingConfig: SettingConfig
) : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("§c메시지를 입력해주세요.")
            return true
        }
        val message = args.copyOf().joinToString(" ").colorize()
        val broadcastFormat = settingConfig.getBroadcastFormat {
            it.replace("%message%", message).replace("%player%", sender.name)
        }.joinToString("\n").run(::TextComponent)
        packetSender.broadcast(broadcastFormat, true)
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): List<String> {
        return emptyList()
    }
}