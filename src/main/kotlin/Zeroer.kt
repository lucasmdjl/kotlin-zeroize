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
 * A class that manages zeroable resources. Rather than using this class directly,
 * users should prefer using [zeroing].
 *
 * @param toZero The initial zeroable resources to zero.
 */
public class Zeroer(vararg toZero: Zeroable) : Zeroable {
    private val toZero: MutableList<Zeroable> = mutableListOf(*toZero)
    /**
     * Adds a zeroable resource to be zeroed when this Zeroer instance is zeroed.
     *
     * @return The zeroable resource.
     */
    public fun <Z : Zeroable> Z.zeroAtEnd(): Z {
        toZero.add(this)
        return this
    }

    /**
     * Zeroes all resources managed by this instance.
     */
    override fun zero() {
        toZero.zeroAll()
    }
}

/**
 * Executes a block of code with a collection of zeroable resources, ensuring they are zeroed afterwards.
 *
 * This function allows dynamic addition of zeroable instances within [block].
 *
 * @param zeroables The zeroable resources to manage.
 * @param block The block of code to execute.
 * @return The result of the block.
 */
public inline fun <T> zeroing(vararg zeroables: Zeroable, block: Zeroer.() -> T): T {
    return Zeroer(*zeroables).use(block)
}
