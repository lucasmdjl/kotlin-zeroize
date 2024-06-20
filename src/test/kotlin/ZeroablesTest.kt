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

@file:OptIn(ExperimentalUnsignedTypes::class)
package io.github.lucasmdjl.zeroize

import io.mockk.Runs
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

public class ZeroableByteArrayTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableByteArray(ByteArray(5) { 1 })
        zeroable.zero()
        zeroable.inner.forEach { assertEquals(0, it) }
    }
}

public class ZeroableIntArrayTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableIntArray(IntArray(5) { 1 })
        zeroable.zero()
        zeroable.inner.forEach { assertEquals(0, it) }
    }
}

public class ZeroableLongArrayTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableLongArray(LongArray(5) { 1 })
        zeroable.zero()
        zeroable.inner.forEach { assertEquals(0, it) }
    }
}

public class ZeroableCharArrayTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableCharArray(CharArray(5) { 'a' })
        zeroable.zero()
        zeroable.inner.forEach { assertEquals('\u0000', it) }
    }
}

public class ZeroableBooleanArrayTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableBooleanArray(BooleanArray(5) { true })
        zeroable.zero()
        zeroable.inner.forEach { assertEquals(false, it) }
    }
}

public class ZeroableFloatArrayTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableFloatArray(FloatArray(5) { 4.2f })
        zeroable.zero()
        zeroable.inner.forEach { assertEquals(0f, it) }
    }
}

public class ZeroableDoubleArrayTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableDoubleArray(DoubleArray(5) { 4.2 })
        zeroable.zero()
        zeroable.inner.forEach { assertEquals(0.0, it) }
    }
}

public class ZeroableUByteArrayTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableUByteArray(UByteArray(5) { 1U })
        zeroable.zero()
        zeroable.inner.forEach { assertEquals(0U, it) }
    }
}

public class ZeroableUIntArrayTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableUIntArray(UIntArray(5) { 1U })
        zeroable.zero()
        zeroable.inner.forEach { assertEquals(0U, it) }
    }
}

public class ZeroableULongArrayTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableULongArray(ULongArray(5) { 1U })
        zeroable.zero()
        zeroable.inner.forEach { assertEquals(0U, it) }
    }
}

public class ZeroableArrayTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableArray(Array(5) {
            val mock = mockk<Zeroable>()
            every { mock.zero() } just Runs
            mock
        })
        zeroable.zero()
        zeroable.inner.forEach { mock ->
            verify(exactly = 1) { mock.zero() }
            confirmVerified(mock)
        }
    }
    @Test
    public fun zero_whenEmpty() {
        val zeroable = ZeroableArray(arrayOf<Zeroable>())
        assertDoesNotThrow { zeroable.zero() }
    }
    @Test
    public fun zero_whenExceptions() {
        val zeroables = Array(5) { i ->
            val zeroable = mockk<Zeroable>()
            if (i % 2 == 0) {
                every { zeroable.zero() } throws RuntimeException("Exception: $i")
            } else {
                every { zeroable.zero() } just Runs
            }
            zeroable
        }
        val zeroableArray = ZeroableArray(zeroables)
        val exception = assertThrows<ZeroingException> { zeroableArray.zero() }
        zeroables.forEach { zeroable ->
            verify { zeroable.zero() }
        }
        assertEquals(3, exception.exceptions.size)
    }
}

public class ZeroableCollectionTest {
    @Test
    public fun zero() {
        val zeroable = ZeroableCollection(List(5) {
            val mock = mockk<Zeroable>()
            every { mock.zero() } just Runs
            mock
        })
        zeroable.zero()
        zeroable.inner.forEach { mock ->
            verify(exactly = 1) { mock.zero() }
            confirmVerified(mock)
        }
    }
    @Test
    public fun zero_whenEmpty() {
        val zeroable = ZeroableCollection(setOf<Zeroable>())
        assertDoesNotThrow { zeroable.zero() }
    }

    @Test
    public fun zero_whenExceptions() {
        val zeroables = List(5) { i ->
            val zeroable = mockk<Zeroable>()
            if (i % 2 == 0) {
                every { zeroable.zero() } throws RuntimeException("Exception: $i")
            } else {
                every { zeroable.zero() } just Runs
            }
            zeroable
        }
        val zeroableCollection = ZeroableCollection(zeroables)
        val exception = assertThrows<ZeroingException> { zeroableCollection.zero() }
        zeroables.forEach { zeroable ->
            verify { zeroable.zero() }
        }
        assertEquals(3, exception.exceptions.size)
    }
}
