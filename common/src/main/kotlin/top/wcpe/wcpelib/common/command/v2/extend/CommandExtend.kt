package top.wcpe.wcpelib.common.command.v2.extend

import top.wcpe.wcpelib.common.Message
import top.wcpe.wcpelib.common.command.v2.*

/**
 * 由 WCPE 在 2023/7/22 19:22 创建
 * <p>
 * Created by WCPE on 2023/7/22 19:22
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 * @since  : v1.2.0-alpha-dev-1
 */
inline fun singleCommand(
    name: String,
    description: String = "",
    aliases: List<String> = listOf(),
    arguments: List<Argument> = listOf(),
    playerOnly: Boolean = false,
    playerOnlyMessage: String = "",
    opOnly: Boolean = false,
    opOnlyMessage: String = "",
    usageMessage: String = "",
    permission: String = "",
    permissionMessage: String = "",
    commandExecutor: CommandExecutor? = null,
    tabCompleter: TabCompleter? = null,
    crossinline runnable: SingleCommand.() -> Unit = {},
): SingleCommand {
    return object : SingleCommand(
        name,
        description,
        aliases,
        arguments,
        playerOnly,
        playerOnlyMessage,
        opOnly,
        opOnlyMessage,
        usageMessage,
        permission,
        permissionMessage
    ) {
        init {
            this.commandExecutor = commandExecutor
            this.tabCompleter = tabCompleter
        }
    }.also(runnable)
}

inline fun singleCommand(
    singleCommandBuilder: SingleCommandBuilder, crossinline runnable: SingleCommand.() -> Unit = {},
): SingleCommand {
    return singleCommand(
        name = singleCommandBuilder.name,
        description = singleCommandBuilder.description,
        aliases = singleCommandBuilder.aliases,
        arguments = singleCommandBuilder.arguments,
        playerOnly = singleCommandBuilder.playerOnly,
        playerOnlyMessage = singleCommandBuilder.playerOnlyMessage,
        usageMessage = singleCommandBuilder.usageMessage,
        permission = singleCommandBuilder.permission,
        permissionMessage = singleCommandBuilder.permissionMessage,
        commandExecutor = singleCommandBuilder.commandExecutor,
        tabCompleter = singleCommandBuilder.tabCompleter,
        runnable = runnable
    )
}


inline fun parentCommand(
    name: String,
    description: String = "",
    aliases: List<String> = listOf(),
    playerOnly: Boolean = false,
    playerOnlyMessage: String = "",
    opOnly: Boolean = false,
    opOnlyMessage: String = "",
    usageMessage: String = "",
    permission: String = "",
    permissionMessage: String = "",
    opVisibleHelp: Boolean = false,
    crossinline runnable: ParentCommand.() -> Unit = {},
): ParentCommand {
    return object : ParentCommand(
        name,
        description,
        aliases,
        playerOnly,
        playerOnlyMessage,
        opOnly,
        opOnlyMessage,
        usageMessage,
        permission,
        permissionMessage,
        opVisibleHelp
    ) {}.also(runnable)
}

inline fun parentCommand(
    parentCommandBuilder: ParentCommandBuilder, crossinline runnable: ParentCommand.() -> Unit = {},
): ParentCommand {
    return parentCommand(
        name = parentCommandBuilder.name,
        description = parentCommandBuilder.description,
        aliases = parentCommandBuilder.aliases,
        playerOnly = parentCommandBuilder.playerOnly,
        playerOnlyMessage = parentCommandBuilder.playerOnlyMessage,
        usageMessage = parentCommandBuilder.usageMessage,
        permission = parentCommandBuilder.permission,
        permissionMessage = parentCommandBuilder.permissionMessage,
        opVisibleHelp = parentCommandBuilder.opVisibleHelp,
        runnable = runnable
    )
}

inline fun ParentCommand.childCommand(
    name: String,
    description: String = "",
    aliases: List<String> = listOf(),
    arguments: List<Argument> = listOf(),
    playerOnly: Boolean = false,
    playerOnlyMessage: String = "",
    opOnly: Boolean = false,
    opOnlyMessage: String = "",
    usageMessage: String = "",
    permission: String = "",
    permissionMessage: String = "",
    shouldDisplay: Boolean = false,
    commandExecutor: CommandExecutor? = null,
    tabCompleter: TabCompleter? = null,
    crossinline runnable: ChildCommand.() -> Unit = {},
): ChildCommand {
    return childCommand(
        name,
        description,
        aliases,
        arguments,
        playerOnly,
        playerOnlyMessage,
        opOnly,
        opOnlyMessage,
        usageMessage,
        permission,
        permissionMessage,
        shouldDisplay,
        commandExecutor,
        tabCompleter
    ).also(runnable)
}

inline fun ParentCommand.childCommand(
    childCommandBuilder: ChildCommandBuilder, crossinline runnable: ChildCommand.() -> Unit = {},
): ChildCommand {
    return childCommand(
        name = childCommandBuilder.name,
        description = childCommandBuilder.description,
        aliases = childCommandBuilder.aliases,
        arguments = childCommandBuilder.arguments,
        playerOnly = childCommandBuilder.playerOnly,
        playerOnlyMessage = childCommandBuilder.playerOnlyMessage,
        usageMessage = childCommandBuilder.usageMessage,
        permission = childCommandBuilder.permission,
        permissionMessage = childCommandBuilder.permissionMessage,
        commandExecutor = childCommandBuilder.commandExecutor,
        tabCompleter = childCommandBuilder.tabCompleter,
        runnable = runnable
    )
}

inline fun arguments(crossinline block: ArgumentsBuilder.() -> Unit): List<Argument> {
    val builder = ArgumentsBuilder()
    builder.block()
    return builder.arguments
}

fun List<Argument>.simpleJoinToString(): String {
    return joinToString(" ") {
        if (it.required) {
            Message.RequiredFormat.toLocalization(
                "%argument_name%" to it.name,
                "%argument_description%" to it.description
            )
        } else {
            Message.OptionalFormat.toLocalization(
                "%argument_name%" to it.name,
                "%argument_description%" to it.description
            )
        }
    }
}