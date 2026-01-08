package org.mnde.orbit

data class ValidationResult(
    val valid: Boolean,
    val code: ErrorCode? = null
)

enum class ErrorCode {
    ERR_NOT_OBJECT,
    ERR_ORBIT_VERSION,
    ERR_ID_INVALID,
    ERR_ACTION_INVALID,
    ERR_ACTION_UNKNOWN,
    ERR_PAYLOAD_INVALID,
    ERR_OPEN_URL_REQUIRED
}

fun validateOrbitIntent(input: Any?): ValidationResult {
    return validateInternal(input)
}
