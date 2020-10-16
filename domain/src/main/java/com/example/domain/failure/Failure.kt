package com.example.domain.failure

sealed class Failure {
    class ServerError(val message: String) : Failure()
    class Unknown(val message: String): Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
}