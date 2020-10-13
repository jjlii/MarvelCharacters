package com.example.domain.failure

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object Unknown: Failure()
    object Conflict: Failure()
    object NotFound: Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
}