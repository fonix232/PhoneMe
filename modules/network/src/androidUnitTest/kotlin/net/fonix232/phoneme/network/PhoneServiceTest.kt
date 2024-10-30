package net.fonix232.phoneme.network

import KtorfitServiceCreator
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PhoneServiceTest {

    private lateinit var sut: PhoneService

    @Before
    fun setUp() {
        sut = KtorfitServiceCreator("https://api.restful-api.dev/").createPhoneService()
    }

    @Test
    fun `When getPhones is called Then phone list is returned`() = runTest {
        val phoneList = sut.getPhones()
        assert(phoneList.isNotEmpty())
    }

    @Test
    fun `When getPhonesById is called with one parameter Then one phone is returned`() = runTest {
        val phoneList = sut.getPhonesById(listOf(11))
        assert(phoneList.isNotEmpty())
        assert(phoneList.size == 1)
        assertEquals(phoneList.first().id, 11)
    }

    @Test
    fun `When getPhonesById is called with multiple parameters Then phone list is returned`() = runTest {
        val phoneList = sut.getPhonesById(listOf(1, 2, 3, 5, 7, 11))
        assert(phoneList.isNotEmpty())
        assert(phoneList.size == 6)
        assertEquals(phoneList[2].id, 3)
        assertEquals(phoneList[5].id, 11)
    }

}