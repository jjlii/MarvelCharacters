package com.example.domain.failure

sealed class Failure {
    object ServerError : Failure()
    object Unknown: Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
}