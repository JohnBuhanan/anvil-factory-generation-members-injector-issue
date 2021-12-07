package example.gradle

import java.lang.reflect.Proxy

/**
 *  Creates a stub of an interface. The stub is the requested interface type that
 *  will throw an exception if any of the functions on it are called.
 *
 *  Usage: `val classUnderTest = MyService(client = stub())`
 *
 *  If you need the stub to provide fake data for tests it can be provided with implementation details
 *  by using the `by` keyword when creating an object.
 *
 *  ```
 *  val stubRepository = object : MyRepositoryInterface by stub() {
 *  override fun getAccounts() : List<Accounts> = listOf()
 *  }
 *
 *  val classUnderTest = MyService(repository = stubRepository)
 *  ```
 *
 *  See StubKtTest.kt for more example usages.
 */
inline fun <reified T : Any> stub(): T =
    Proxy.newProxyInstance(
        T::class.java.classLoader,
        arrayOf<Class<*>>(T::class.java)
    ) { _, _, _ -> throw NotImplementedError("Stub!") } as T
