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

package pers.ketikai.common.command;

import org.jetbrains.annotations.NotNull;
import pers.ketikai.common.command.annotation.CommandArgument;
import pers.ketikai.common.command.exception.CommandArgumentConversionException;

final class SimpleCommandArgumentConverter implements CommandArgument.Converter<String> {

    public static final SimpleCommandArgumentConverter INSTANCE = new SimpleCommandArgumentConverter();

    @Override
    public @NotNull Class<String> getTargetType() {
        return String.class;
    }

    @Override
    public @NotNull String doConvert(@NotNull CommandContext context, @NotNull String argument) throws CommandArgumentConversionException {
        return argument;
    }

    @Override
    public boolean canCovert(@NotNull CommandContext context, @NotNull String argument) {
        return true;
    }
}
