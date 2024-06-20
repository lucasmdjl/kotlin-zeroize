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

/**
 * A zeroable wrapper around a [ByteArray].
 *
 * This class provides a secure way to handle and zero out byte arrays, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying byte array to be zeroed.
 */
@JvmInline
public value class ZeroableByteArray(public val inner: ByteArray) : Zeroable {
    override fun zero(): Unit = inner.zero()
}

/**
 * A zeroable wrapper around an [IntArray].
 *
 * This class provides a secure way to handle and zero out int arrays, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying int array to be zeroed.
 */
@JvmInline
public value class ZeroableIntArray(public val inner: IntArray) : Zeroable {
    override fun zero(): Unit = inner.zero()
}
/**
 * A zeroable wrapper around an [LongArray].
 *
 * This class provides a secure way to handle and zero out long arrays, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying long array to be zeroed.
 */
@JvmInline
public value class ZeroableLongArray(public val inner: LongArray) : Zeroable {
    override fun zero(): Unit = inner.zero()
}

/**
 * A zeroable wrapper around a [CharArray].
 *
 * This class provides a secure way to handle and zero out character arrays, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying character array to be zeroed.
 */
@JvmInline
public value class ZeroableCharArray(public val inner: CharArray) : Zeroable {
    override fun zero(): Unit = inner.zero()
}

/**
 * A zeroable wrapper around a [FloatArray].
 *
 * This class provides a secure way to handle and zero out float arrays, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying float array to be zeroed.
 */
@JvmInline
public value class ZeroableFloatArray(public val inner: FloatArray) : Zeroable {
    override fun zero(): Unit = inner.zero()
}

/**
 * A zeroable wrapper around a [DoubleArray].
 *
 * This class provides a secure way to handle and zero out double arrays, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying double array to be zeroed.
 */
@JvmInline
public value class ZeroableDoubleArray(public val inner: DoubleArray) : Zeroable {
    override fun zero(): Unit = inner.zero()
}

/**
 * A zeroable wrapper around a [BooleanArray].
 *
 * This class provides a secure way to handle and zero out boolean arrays, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying boolean array to be zeroed.
 */
@JvmInline
public value class ZeroableBooleanArray(public val inner: BooleanArray) : Zeroable {
    override fun zero(): Unit = inner.zero()
}

/**
 * A zeroable wrapper around an [UByteArray].
 *
 * This class provides a secure way to handle and zero out unsigned byte arrays, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying unsigned byte array to be zeroed.
 */
@JvmInline
public value class ZeroableUByteArray(public val inner: UByteArray) : Zeroable {
    override fun zero(): Unit = inner.zero()
}

/**
 * A zeroable wrapper around an [UIntArray].
 *
 * This class provides a secure way to handle and zero out unsigned int arrays, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying unsigned int array to be zeroed.
 */
@JvmInline
public value class ZeroableUIntArray(public val inner: UIntArray) : Zeroable {
    override fun zero(): Unit = inner.zero()
}

/**
 * A zeroable wrapper around an [ULongArray].
 *
 * This class provides a secure way to handle and zero out unsigned long arrays, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying unsigned long array to be zeroed.
 */
@JvmInline
public value class ZeroableULongArray(public val inner: ULongArray) : Zeroable {
    override fun zero(): Unit = inner.zero()
}

/**
 * A zeroable wrapper around an array of zeroable elements.
 *
 * This class provides a secure way to handle and zero out arrays of zeroable elements, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying array to be zeroed.
 */
@JvmInline
public value class ZeroableArray<Z : Zeroable?>(public val inner: Array<Z>) : Zeroable {
    override fun zero() {
        inner.filterNotNull().zeroAll()
    }
}

/**
 * A zeroable wrapper around a collection of zeroable elements.
 *
 * This class provides a secure way to handle and zero out collections of zeroable elements, ensuring
 * that sensitive information is properly cleared from memory.
 *
 * @property inner The underlying collection to be zeroed.
 */
@JvmInline
public value class ZeroableCollection<Z: Zeroable?, C: Collection<Z>>(public val inner: C): Zeroable {
    override fun zero() {
        inner.filterNotNull().zeroAll()
    }
}
