# Zeroize Library

A Kotlin library for securely zeroing various types of resources, ensuring that sensitive data is properly cleared from memory. The library provides a consistent API for zeroing arrays and collections of primitive and unsigned types, as well as supporting zeroing operations within a scope.

## Features

- Zeroing support for various array types (`ByteArray`, `IntArray`, `LongArray`, `CharArray`, `FloatArray`, `DoubleArray`, `BooleanArray`, `UByteArray`, `UIntArray`, `ULongArray`)
- Zeroing support for arrays and collections of zeroable elements
- Exception aggregation during zeroing operations
- Convenient `zeroing` function to ensure resources are zeroed after use

## Installation

### Gradle

```kotlin
dependencies {
    implementation("dev.lucasmdjl:kotlin-zeroize:0.1.0")
}
```

### Maven

```xml
<dependency>
    <group>dev.lucasmdjl</group>
    <artifact>kotlin-zeroize</artifact>
    <version>0.1.0</version>
</dependency>
```

## Example Usage

```kotlin
fun main() {
    // Manual zeroing using extension functions
    val byteArray = ByteArray(5) { 1 }
    byteArray.zero()
    println(byteArray.joinToString()) // Prints: 0, 0, 0, 0, 0

    // Automatic zeroing
    val intArray = IntArray(5) { 1 }
    zeroing(intArray.asZeroable()) {
        println(intArray.joinToString()) // Prints: 1, 1, 1, 1, 1
        val longArray = longArrayOf(1, 2, 3, 4).zeroAtEnd()
        println(longArray.joinToString()) // Prints: 1, 2, 3, 4
    } // intArray and longArray are now zeroed
}
```
