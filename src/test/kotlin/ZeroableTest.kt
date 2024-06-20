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

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

public class ZeroableTest {
    @Test
    public fun close() {
        val zeroable = mockk<Zeroable>()
        every { zeroable.close() } answers { callOriginal() }
        every { zeroable.zero() } just Runs
        zeroable.close()
        verify { zeroable.zero() }
    }

    @Test
    public fun zeroAll() {
        val zeroables = List(5) {
            val zeroable = mockk<Zeroable>()
            every { zeroable.zero() } just Runs
            zeroable
        }
        zeroables.zeroAll()
        zeroables.forEach { zeroable ->
            verify { zeroable.zero() }
        }
    }

    @Test
    public fun zeroAll_whenExceptions() {
        val zeroables = List(5) { i ->
            val zeroable = mockk<Zeroable>()
            if (i % 2 == 0) {
                every { zeroable.zero() } throws RuntimeException("Exception: $i")
            } else {
                every { zeroable.zero() } just Runs
            }
            zeroable
        }
        val exception = assertThrows<ZeroingException> { zeroables.zeroAll() }
        zeroables.forEach { zeroable ->
            verify { zeroable.zero() }
        }
        assertEquals(3, exception.exceptions.size)
    }
}
