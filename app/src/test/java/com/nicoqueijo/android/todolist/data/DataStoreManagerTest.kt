package com.nicoqueijo.android.todolist.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DataStoreManagerTest {

    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var subject: DataStoreManager

    @BeforeEach
    fun setUp() {
        dataStore = mockk()
        subject = DataStoreManager(
            dataStore = dataStore,
        )
    }

    @Nested
    inner class IsFirstLaunch {

        @Test
        fun `given no preference set, when isFirstLaunch called, then returns true`() = runTest {
            val preferences: Preferences = mockk {
                every {
                    get(DataStoreManager.PreferencesKeys.FIRST_LAUNCH)
                } returns null
            }
            coEvery {
                dataStore.data
            } returns flowOf(preferences)

            val actual = subject.isFirstLaunch()

            actual.shouldBeTrue()
        }

        @Test
        fun `given first launch preference is true, when isFirstLaunch called, then returns true`() =
            runTest {
                val preferences: Preferences = mockk {
                    every {
                        get(DataStoreManager.PreferencesKeys.FIRST_LAUNCH)
                    } returns true
                }
                coEvery {
                    dataStore.data
                } returns flowOf(preferences)

                val actual = subject.isFirstLaunch()

                actual.shouldBeTrue()
            }

        @Test
        fun `given first launch preference is false, when isFirstLaunch called, then returns false`() =
            runTest {
                val preferences: Preferences = mockk {
                    every {
                        get(DataStoreManager.PreferencesKeys.FIRST_LAUNCH)
                    } returns false
                }
                coEvery {
                    dataStore.data
                } returns flowOf(preferences)

                val actual = subject.isFirstLaunch()

                actual.shouldBeFalse()
            }
    }
}
