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

package io.github.lucasmdjl.zeroize

/**
 * Interface representing a zeroable resource, which can be cleared to zero.
 * Implements [AutoCloseable], ensuring zeroing on close; however, it is
 * generally recommended to use [zeroing] for this.
 */
public interface Zeroable : AutoCloseable {
    /**
     * Zeroes the resource, clearing its contents.
     *
     * This method should ensure that the contents of the resource are securely overwritten,
     * preventing any sensitive data from being recoverable.
     */
    public fun zero()

    /**
     * Closes the resource by zeroing its contents.
     */
    override fun close() {
        zero()
    }
}

/**
 * Zeroes all elements in the iterable, collecting and aggregating any exceptions that occur.
 *
 * This function attempts to zero all elements in the iterable, collecting any exceptions
 * thrown during the process. If any exceptions are collected, a [ZeroingException] containing all
 * the collected exceptions is thrown after attempting to zero all elements.
 *
 * @throws ZeroingException if one or more exceptions occur during zeroing.
 */
internal fun <Z : Zeroable> Iterable<Z>.zeroAll() {
    val exceptions = mutableListOf<Exception>()
    forEach { z ->
        try {
            z.zero()
        } catch (e: Exception) {
            exceptions.add(e)
        }
    }
    if (exceptions.isNotEmpty()) {
        throw ZeroingException(exceptions)
    }
}
