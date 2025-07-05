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

@file:OptIn(ExperimentalUnsignedTypes::class)

package dev.lucasmdjl.zeroize

/**
 * Fills this [ByteArray] with zeros.
 */
public fun ByteArray.zero() {
    fill(0)
}

/**
 * Wraps this [ByteArray] in a [ZeroableByteArray] instance.
 */
public fun ByteArray.asZeroable(): ZeroableByteArray {
    return ZeroableByteArray(this)
}

/**
 * Registers this [ByteArray] to be zeroed at the end of the scope.
 *
 * @return The original [ByteArray].
 */
context(zeroer: Zeroer)
public fun ByteArray.zeroAtEnd(): ByteArray {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}

/**
 * Fills this [IntArray] with zeros.
 */
public fun IntArray.zero() {
    fill(0)
}

/**
 * Wraps this [IntArray] in a [ZeroableIntArray] instance.
 */
public fun IntArray.asZeroable(): ZeroableIntArray {
    return ZeroableIntArray(this)
}

/**
 * Registers this [IntArray] to be zeroed at the end of the scope.
 *
 * @return The original [IntArray].
 */
context(zeroer: Zeroer)
public fun IntArray.zeroAtEnd(): IntArray {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}

/**
 * Fills this [LongArray] with zeros.
 */
public fun LongArray.zero() {
    fill(0)
}

/**
 * Wraps this [LongArray] in a [ZeroableLongArray] instance.
 */
public fun LongArray.asZeroable(): ZeroableLongArray {
    return ZeroableLongArray(this)
}

/**
 * Registers this [LongArray] to be zeroed at the end of the scope.
 *
 * @return The original [LongArray].
 */
context(zeroer: Zeroer)
public fun LongArray.zeroAtEnd(): LongArray {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}

/**
 * Fills this [CharArray] with null characters.
 */
public fun CharArray.zero() {
    fill('\u0000')
}

/**
 * Wraps this [CharArray] in a [ZeroableCharArray] instance.
 */
public fun CharArray.asZeroable(): ZeroableCharArray {
    return ZeroableCharArray(this)
}

/**
 * Registers this [CharArray] to be zeroed at the end of the scope.
 *
 * @return The original [CharArray].
 */
context(zeroer: Zeroer)
public fun CharArray.zeroAtEnd(): CharArray {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}

/**
 * Fills this [BooleanArray] with `false` values.
 */
public fun BooleanArray.zero() {
    fill(false)
}

/**
 * Wraps this [BooleanArray] in a [ZeroableBooleanArray] instance.
 */
public fun BooleanArray.asZeroable(): ZeroableBooleanArray {
    return ZeroableBooleanArray(this)
}

/**
 * Registers this [BooleanArray] to be zeroed at the end of the scope.
 *
 * @return The original [BooleanArray].
 */
context(zeroer: Zeroer)
public fun BooleanArray.zeroAtEnd(): BooleanArray {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}

/**
 * Fills this [FloatArray] with zeros.
 */
public fun FloatArray.zero() {
    fill(0f)
}

/**
 * Wraps this [FloatArray] in a [ZeroableFloatArray] instance.
 */
public fun FloatArray.asZeroable(): ZeroableFloatArray {
    return ZeroableFloatArray(this)
}

/**
 * Registers this [FloatArray] to be zeroed at the end of the scope.
 *
 * @return The original [FloatArray].
 */
context(zeroer: Zeroer)
public fun FloatArray.zeroAtEnd(): FloatArray {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}

/**
 * Fills this [DoubleArray] with zeros.
 */
public fun DoubleArray.zero() {
    fill(0.0)
}

/**
 * Wraps this [DoubleArray] in a [ZeroableDoubleArray] instance.
 */
public fun DoubleArray.asZeroable(): ZeroableDoubleArray {
    return ZeroableDoubleArray(this)
}

/**
 * Registers this [DoubleArray] to be zeroed at the end of the scope.
 *
 * @return The original [DoubleArray].
 */
context(zeroer: Zeroer)
public fun DoubleArray.zeroAtEnd(): DoubleArray {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}

/**
 * Fills this [UByteArray] with zeros.
 */
public fun UByteArray.zero() {
    fill(0U)
}

/**
 * Wraps this [UByteArray] in a [ZeroableUByteArray] instance.
 */
public fun UByteArray.asZeroable(): ZeroableUByteArray {
    return ZeroableUByteArray(this)
}

/**
 * Registers this [UByteArray] to be zeroed at the end of the scope.
 *
 * @return The original [UByteArray].
 */
context(zeroer: Zeroer)
public fun UByteArray.zeroAtEnd(): UByteArray {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}

/**
 * Fills this [UIntArray] with zeros.
 */
public fun UIntArray.zero() {
    fill(0U)
}

/**
 * Wraps this [UIntArray] in a [ZeroableUIntArray] instance.
 */
public fun UIntArray.asZeroable(): ZeroableUIntArray {
    return ZeroableUIntArray(this)
}

/**
 * Registers this [UIntArray] to be zeroed at the end of the scope.
 *
 * @return The original [UIntArray].
 */
context(zeroer: Zeroer)
public fun UIntArray.zeroAtEnd(): UIntArray {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}

/**
 * Fills this [ULongArray] with zeros.
 */
public fun ULongArray.zero() {
    fill(0U)
}

/**
 * Wraps this [ULongArray] in a [ZeroableULongArray] instance.
 */
public fun ULongArray.asZeroable(): ZeroableULongArray {
    return ZeroableULongArray(this)
}

/**
 * Registers this [ULongArray] to be zeroed at the end of the scope.
 *
 * @return The original [ULongArray].
 */
context(zeroer: Zeroer)
public fun ULongArray.zeroAtEnd(): ULongArray {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}

/**
 * Zeros all elements in this [Array] of zeroable elements.
 */
public fun <Z : Zeroable?> Array<Z>.zero() {
    filterNotNull().zeroAll()
}

/**
 * Wraps this [Array] in a [ZeroableArray] instance.
 */
public fun <Z : Zeroable?> Array<Z>.asZeroable(): ZeroableArray<Z> {
    return ZeroableArray(this)
}

/**
 * Registers this [Array] to be zeroed at the end of the scope.
 *
 * @return The original [Array].
 */
context(zeroer: Zeroer)
public fun <Z : Zeroable?> Array<Z>.zeroAtEnd(): Array<Z> {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}

/**
 * Zeros all elements in this [Collection] of zeroable elements.
 */
public fun <Z : Zeroable?, C : Collection<Z>> C.zero() {
    filterNotNull().zeroAll()
}

/**
 * Wraps this [Collection] in a [ZeroableCollection] instance.
 */
public fun <Z : Zeroable?, C : Collection<Z>> C.asZeroable(): ZeroableCollection<Z, C> {
    return ZeroableCollection(this)
}

/**
 * Registers this [Collection] to be zeroed at the end of the scope.
 *
 * @return The original [Collection].
 */
context(zeroer: Zeroer)
public fun <Z : Zeroable?, C : Collection<Z>> C.zeroAtEnd(): C {
    with(zeroer) {
        return asZeroable().zeroAtEnd().inner
    }
}
