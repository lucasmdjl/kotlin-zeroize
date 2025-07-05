/*
 * Zeroize: zeroing library.
 * Copyright (C) 2024 Lucas M. de Jong Larrarte
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.lucasmdjl.zeroize

/**
 * An exception class that aggregates one or multiple exceptions that occur during the zeroing process.
 *
 * @property exceptions A list of exceptions that occurred during zeroing.
 */
public class ZeroingException(public val exceptions: List<Exception>) : RuntimeException("One or more exceptions occurred during zeroing") {
    override fun toString(): String {
        return super.toString() + exceptions.joinToString("\n", "\n") { "- $it" }
    }
}
