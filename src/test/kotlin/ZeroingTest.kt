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
import kotlin.test.assertIs

public class ZeroingTest {
    @Test
    public fun zero() {
        val zeroable = mockk<Zeroable>()
        every { zeroable.zero() } just Runs
        val zeroable2 = mockk<Zeroable>()
        every { zeroable2.zero() } just Runs

        zeroing(zeroable) {
            zeroable2.zeroAtEnd()
        }

        verify { zeroable.zero() }
        verify { zeroable2.zero() }
    }
    @Test
    public fun zero_whenBlockThrows() {
        val zeroable = mockk<Zeroable>()
        every { zeroable.zero() } just Runs
        val zeroable2 = mockk<Zeroable>()
        every { zeroable2.zero() } just Runs

        assertThrows<IllegalArgumentException> {
            zeroing(zeroable) {
                zeroable2.zeroAtEnd()
                throw IllegalArgumentException()
            }
        }

        verify { zeroable.zero() }
        verify { zeroable2.zero() }
    }
    @Test
    public fun zero_whenZeroingThrows() {
        val zeroable = mockk<Zeroable>()
        every { zeroable.zero() } just Runs
        val zeroable2 = mockk<Zeroable>()
        every { zeroable2.zero() } throws IllegalArgumentException()

        val exception = assertThrows<ZeroingException> {
            zeroing(zeroable) {
                zeroable2.zeroAtEnd()
            }
        }

        verify { zeroable.zero() }
        verify { zeroable2.zero() }
        assertEquals(1, exception.exceptions.size)
        assertIs<IllegalArgumentException>(exception.exceptions[0])
    }
    @Test
    public fun zero_whenBlockAndZeroingThrow() {
        val zeroable = mockk<Zeroable>()
        every { zeroable.zero() } just Runs
        val zeroable2 = mockk<Zeroable>()
        every { zeroable2.zero() } throws IllegalArgumentException()


        val exception = assertThrows<IllegalStateException> {
            zeroing(zeroable) {
                zeroable2.zeroAtEnd()
                throw IllegalStateException()
            }
        }

        verify { zeroable.zero() }
        verify { zeroable2.zero() }
        assertEquals(1, exception.suppressed.size)
        val suppressed = exception.suppressed[0]
        assertIs<ZeroingException>(suppressed)
        assertEquals(1, suppressed.exceptions.size)
        assertIs<IllegalArgumentException>(suppressed.exceptions[0])
    }
}
