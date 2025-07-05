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

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

public class ZeroerTest {
    @Test
    public fun zero() {
        val zeroables = Array(2) {
            val mock = mockk<Zeroable>()
            every { mock.zero() } just Runs
            mock
        }
        val zeroer = Zeroer(*zeroables)
        val zeroables2 = List(3) {
            val mock = mockk<Zeroable>()
            every { mock.zero() } just Runs
            mock
        }
        zeroables2.forEach {
            with(zeroer) {
                it.zeroAtEnd()
            }
        }

        zeroer.zero()

        zeroables.forEach { zeroable ->
            verify { zeroable.zero() }
        }
        zeroables2.forEach { zeroable ->
            verify { zeroable.zero() }
        }
    }

    @Test
    public fun zero_whenExceptions() {
        val zeroables = Array(2) { i ->
            val zeroable = mockk<Zeroable>()
            if (i % 2 == 0) {
                every { zeroable.zero() } throws RuntimeException("Exception: $i")
            } else {
                every { zeroable.zero() } just Runs
            }
            zeroable
        }
        val zeroer = Zeroer(*zeroables)
        val zeroables2 = List(3) { i ->
            val zeroable = mockk<Zeroable>()
            if (i % 2 == 0) {
                every { zeroable.zero() } throws RuntimeException("Exception: ${i + 2}")
            } else {
                every { zeroable.zero() } just Runs
            }
            zeroable
        }
        zeroables2.forEach {
            with(zeroer) {
                it.zeroAtEnd()
            }
        }

        val exception = assertThrows<ZeroingException> { zeroer.zero() }

        zeroables.forEach { zeroable ->
            verify { zeroable.zero() }
        }
        zeroables2.forEach { zeroable ->
            verify { zeroable.zero() }
        }
        assertEquals(3, exception.exceptions.size)
    }
}
