/*
 * Kotlin Zeroize: a zeroing micro-library for Kotlin.
 * Copyright (C) 2025 Lucas M. de Jong Larrarte
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.lucasmdjl.zeroize

/**
 * An exception class that aggregates one or multiple exceptions that occur during the zeroing process.
 *
 * @property exceptions A list of exceptions that occurred during zeroing.
 */
public class ZeroingException(exceptions: List<Exception>) :
    RuntimeException("One or more exceptions occurred during zeroing") {
    init {
        exceptions.forEach(this::addSuppressed)
    }
}
