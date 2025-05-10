# 📘 Dictionary Implementation (Java)

This project implements a custom `Dictionary<K, V>` data structure in Java using **chain hashing** for collision resolution and maintaining **FIFO order** for keys and values. All core operations aim for **O(1) time complexity**.

---

## 🧩 Features

| Method                        | Description                                                                                                              |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------------ |
| `void insert(K key, V value)` | Inserts a key-value pair. Throws `KeyAlreadyExistException` if the key exists, or `NullKeyException` if the key is null. |
| `V delete(K key)`             | Deletes the key-value pair and returns the value. Throws `KeyNotFoundException` or `NullKeyException`.                   |
| `V update(K key, V value)`    | Updates value for a given key. Returns old value. Throws `KeyNotFoundException` or `NullKeyException`.                   |
| `V get(K key)`                | Retrieves the value associated with the key. Throws `KeyNotFoundException` or `NullKeyException`.                        |
| `int size()`                  | Returns the number of key-value pairs.                                                                                   |
| `K[] keys()`                  | Returns all keys in FIFO order.                                                                                          |
| `V[] values()`                | Returns all values in FIFO order.                                                                                        |
| `long hash(K key)`            | Returns hash of the key using **Polynomial Rolling Hash Function**.                                                      |

---

## 🔁 Polynomial Rolling Hash Function

Used in `hash(K key)`:

```
hash(s) = (s[0] + s[1]·p + s[2]·p² + ... + s[n-1]·pⁿ⁻¹) mod m
```

Where:

* `s[i] = ASCII value of s[i] + 1`
* `p = 131` (prime > 128 to cover all ASCII)
* `m = hash table size`

---

## 🛠 Design Notes

* **Chain Hashing** is used for collision resolution via linked lists.
* **FIFO Order:** Maintained using insertion sequence in the lists.
* **Time Complexity:** All major operations are O(1) average case due to hashing.

---

## 🧪 Exceptions

* `KeyAlreadyExistException`: Thrown on duplicate key insertion.
* `KeyNotFoundException`: Thrown when key is not found in delete/update/get.
* `NullKeyException`: Thrown when a `null` key is used.

---

## 📂 File Structure

| File                            | Description                                                  |
| ------------------------------- | ------------------------------------------------------------ |
| `Dictionary.java`               | Main interface and implementation.                           |
| `EntryNode.java`                | Node class for chain hashing (contains key, value, pointer). |
| `KeyAlreadyExistException.java` | Custom exception.                                            |
| `KeyNotFoundException.java`     | Custom exception.                                            |
| `NullKeyException.java`         | Custom exception.                                            |

