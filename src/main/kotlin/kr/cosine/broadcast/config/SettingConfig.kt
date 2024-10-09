package kr.cosine.broadcast.config

import kr.hqservice.framework.bukkit.core.extension.colorize
import kr.hqservice.framework.global.core.component.Bean
import kr.hqservice.framework.yaml.config.HQYamlConfiguration

@Bean
class SettingConfig(
    private val config: HQYamlConfiguration
) {
    private var broadcastFormat = emptyList<String>()
    private var helpFormat = emptyList<String>()

    fun load() {
        broadcastFormat = config.getStringList("broadcast-format").map(String::colorize)
        helpFormat = config.getStringList("help-format").map(String::colorize)
    }

    fun reload() {
        broadcastFormat = emptyList()
        helpFormat = emptyList()
        config.reload()
        load()
    }

    fun getHelpFormat(replace: (String) -> String): List<String> {
        return helpFormat.map(replace)
    }

    fun getBroadcastFormat(replace: (String) -> String): List<String> {
        return broadcastFormat.map(replace)
    }
}