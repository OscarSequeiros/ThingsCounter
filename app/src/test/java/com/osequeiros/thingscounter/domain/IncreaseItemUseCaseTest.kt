package com.osequeiros.thingscounter.domain

import com.osequeiros.thingscounter.domain.model.Item
import com.osequeiros.thingscounter.domain.usecases.IncreaseItemQuantityUseCase
import org.junit.Before
import org.junit.Test

class IncreaseItemUseCaseTest {

    private lateinit var item: Item
    private lateinit var itemExpected: Item
    private lateinit var itemNoExpected: Item
    private val useCase = IncreaseItemQuantityUseCase()

    @Before
    fun setUp() {
        item = Item(
            code = 3,
            name = "Manzanas",
            quantity = 3
        )

        itemExpected = Item(
            code = 3,
            name = "Manzanas",
            quantity = 4
        )

        itemNoExpected = Item(
            code = 3,
            name = "Manzanas",
            quantity = 5
        )
    }

    @Test
    fun `validate correct increase`() {
        val testObserver = useCase.execute(item).test()
        testObserver
            .assertValue(itemExpected)
            .dispose()
    }

    @Test
    fun `validate incorrect increase`() {
        val testObserver = useCase.execute(item).test()
        testObserver
            .assertNever(itemNoExpected)
            .dispose()
    }
}