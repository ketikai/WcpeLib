/*
 * Copyright 2025 ketikai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pers.ketikai.common.bukkit.command;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Entity;
import org.bukkit.permissions.Permissible;
import org.jetbrains.annotations.NotNull;
import pers.ketikai.common.command.CommandSender;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class BukkitCommandSender implements CommandSender {

    public static BukkitCommandSender of(@NotNull Permissible holder) {
        Objects.requireNonNull(holder, "holder must not be null.");
        return new BukkitCommandSender(holder);
    }

    @NonNull
    private final Permissible holder;

    @Override
    public @NotNull UUID getUniqueId() {
        if (holder instanceof Entity) {
            return ((Entity) holder).getUniqueId();
        }
        return CONSOLE_UUID;
    }

    @Override
    public boolean isAdministrator() {
        return holder.isOp();
    }

    @Override
    public boolean hasPermission(@NotNull String permission) {
        Objects.requireNonNull(permission, "permission must not be null.");
        return holder.hasPermission(permission);
    }
}
