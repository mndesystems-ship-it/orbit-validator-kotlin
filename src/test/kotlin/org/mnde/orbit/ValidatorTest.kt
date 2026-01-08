package org.mnde.orbit

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidatorTest {

    @Test
    fun validOpenIntentPasses() {
        val intent = mapOf(
            "orbit" to "1.0",
            "id" to "test-1",
            "action" to "open",
            "payload" to mapOf("url" to "https://example.com")
        )

        val result = validateOrbitIntent(intent)
        assertTrue(result.valid)
    }

    @Test
    fun missingUrlFails() {
        val intent = mapOf(
            "orbit" to "1.0",
            "id" to "test-2",
            "action" to "open",
            "payload" to emptyMap<String, Any>()
        )

        val result = validateOrbitIntent(intent)
        assertFalse(result.valid)
        assertEquals(ErrorCode.ERR_OPEN_URL_REQUIRED, result.code)
    }
}
