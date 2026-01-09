Orbit Protocol — Kotlin Validator

Authoritative Kotlin / JVM reference validator for Orbit Protocol v1.0

What this is

This repository contains the official Kotlin implementation of the Orbit Protocol v1.0 validator.

Its job is intentionally narrow:

Answer one question:
Is this JSON a valid Orbit v1.0 intent?

Nothing more. Nothing less.

It does not execute actions.
It does not interpret business logic.
It does not perform side effects.

This library exists so every Orbit consumer can rely on the same rules, enforced the same way, every time.

What Orbit is (short version)

Orbit is a minimal, fail-closed intent protocol.

It defines:

how an intent is expressed

how it is validated

how ambiguity is rejected

Orbit does not define:

how intents are executed

what actions mean

what a system should do

Those decisions belong to the runtime (Android, server, embedded device, etc.).

Design principles

This validator follows strict rules:

Fail-closed
Unknown fields are rejected. Ambiguity is not tolerated.

Deterministic
The same input always produces the same result.

Schema-driven
Validation is explicit and machine-checkable.

Side-effect free
No I/O. No logging. No execution. No globals.

Reference-grade
Other implementations should match this behavior exactly.

If behavior is unclear, the validator rejects the input.

What this library does

✅ Parses JSON
✅ Validates Orbit v1.0 structure
✅ Enforces fail-closed rules
✅ Returns stable, machine-readable violations

What this library does not do

❌ Execute intents
❌ Perform actions
❌ Provide defaults
❌ Guess meaning
❌ “Fix” malformed input

If you need execution, see a reference app or runtime implementation.

API overview

The public API is intentionally small.

val result = OrbitValidator.validate(jsonString)

when (result) {
    is ValidationResult.Valid -> {
        // Safe to proceed
    }
    is ValidationResult.Invalid -> {
        result.violations.forEach {
            println(it.code)
        }
    }
}

ValidationResult
sealed interface ValidationResult {
    object Valid : ValidationResult
    data class Invalid(val violations: List<Violation>) : ValidationResult
}

Violation
data class Violation(
    val code: String,      // Stable, machine-readable
    val message: String,   // Human-readable
    val pointer: String?   // JSON Pointer (if applicable)
)


Violations are:

ordered deterministically

suitable for logging, UI, or telemetry

stable across versions of the same protocol

JVM compatibility

Target JVM: Java 17

Language: Kotlin (JVM)

You may run this on newer JDKs, but it is compiled for maximum compatibility.

Android compatibility

This library is not Android-specific, but is Android-safe.

It contains:

no Android dependencies

no reflection tricks

no restricted APIs

It can be safely used in Android apps as a pure validation dependency.

Versioning policy

1.x — Orbit Protocol v1 (frozen)

Breaking changes only occur with new protocol versions

Validator versions track protocol versions

This repository will not silently change validation rules.

Relationship to other Orbit projects

Typical usage:

Orbit JSON
   ↓
orbit-validator-kotlin   ← this library
   ↓ Valid
Runtime / App / Service
   ↓
Execution logic


This validator is the single source of truth for Orbit v1 validation.

License

MIT License.

You are free to:

use it

modify it

embed it

ship it

Attribution is appreciated but not required.

Status

Protocol: Orbit v1.0 (frozen)

Validator: Reference-grade

Execution: Out of scope

Final note

If you find yourself wanting this validator to:

execute something

guess intent

accept malformed input

you are asking it to do the wrong job.

Orbit is designed to be boring at the protocol layer
so runtimes can be creative safely.
