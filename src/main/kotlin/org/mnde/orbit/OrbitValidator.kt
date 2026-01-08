package org.mnde.orbit

@Suppress("UNCHECKED_CAST")
internal fun validateInternal(input: Any?): ValidationResult {
    if (input !is Map<*, *>) {
        return ValidationResult(false, ErrorCode.ERR_NOT_OBJECT)
    }

    if (input["orbit"] != "1.0") {
        return ValidationResult(false, ErrorCode.ERR_ORBIT_VERSION)
    }

    val id = input["id"]
    if (id !is String || id.isBlank()) {
        return ValidationResult(false, ErrorCode.ERR_ID_INVALID)
    }

    val action = input["action"]
    if (action !is String || action.isBlank()) {
        return ValidationResult(false, ErrorCode.ERR_ACTION_INVALID)
    }

    val payload = input["payload"]
    if (payload !is Map<*, *>) {
        return ValidationResult(false, ErrorCode.ERR_PAYLOAD_INVALID)
    }

    return when (action) {
        "open" -> {
            val url = payload["url"]
            if (url !is String || url.isBlank()) {
                ValidationResult(false, ErrorCode.ERR_OPEN_URL_REQUIRED)
            } else {
                ValidationResult(true)
            }
        }
        else -> ValidationResult(false, ErrorCode.ERR_ACTION_UNKNOWN)
    }
}
