package com.tabac.rickandmorty.utils

import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE

class AppLogger(level: Level = Level.DEBUG) : Logger(level) {
    override fun display(level: Level, msg: MESSAGE) {
        println("[${level.name}] $msg")
    }
}