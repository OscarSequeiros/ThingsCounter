package com.osequeiros.thingscounter.domain

import com.osequeiros.thingscounter.domain.exceptions.ForbiddenDecreaseException
import com.osequeiros.thingscounter.domain.model.Item
import com.osequeiros.thingscounter.domain.usecases.DecreaseItemQuantityUseCase
import org.junit.Before
import org.junit.Test

class DecreaseItemUseCaseTest {

    private lateinit var item: Item
    private lateinit var itemExpected: Item
    private lateinit var itemWithoutQuantity: Item
    private val useCase = DecreaseItemQuantityUseCase()

    @Before
    fun setUp() {
        item = Item(
            code = 3,
            name = "Manzanas",
            quantity = 5
        )

        itemExpected = Item(
            code = 3,
            name = "Manzanas",
            quantity = 4
        )

        itemWithoutQuantity = Item(
            code = 3,
            name = "Manzanas",
            quantity = 0
        )
    }

    @Test
    fun `validate correct decrease`() {
        val testObserver = useCase.execute(item).test()
        testObserver
            .assertValue(itemExpected)
            .dispose()
    }

    @Test
    fun `validate correct exception`() {
        val testObserver = useCase.execute(itemWithoutQuantity).test()
        testObserver
            .assertFailure(ForbiddenDecreaseException::class.java)
            .dispose()
    }

    @Test
    fun `validate incorrect decrease`() {
        val testObserver = useCase.execute(item).test()
        testObserver
            .assertNever(item)
            .dispose()
    }
}