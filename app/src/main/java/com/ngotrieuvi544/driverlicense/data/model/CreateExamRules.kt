package com.ngotrieuvi544.driverlicense.data.model

enum class CreateExamRules(
    val licenseType: com.ngotrieuvi544.driverlicense.data.model.LicenseType,
    val numbersOfQuestionByType: Map<com.ngotrieuvi544.driverlicense.data.model.QuestionType, Int>,
    val minimumCorrectToPassExam: Int,
    val totalNumberOfQuestion: Int,
    val examDurationByMinutes: Int,
    val isMixQuestionInMotorbikeExam: Boolean = false
) {
    RULE_A1(
        licenseType = com.ngotrieuvi544.driverlicense.data.model.LicenseType.A1,
        numbersOfQuestionByType = mapOf(
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_CONCEPT_AND_RULES to 9,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_BUSINESS to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.ETHICS_IN_DRIVING to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_TECHNIQUE to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.FIXING_CAR_QUESTION to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SIGNAL to 7,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SITUATION to 7
        ),
        minimumCorrectToPassExam = 21,
        totalNumberOfQuestion = 25,
        examDurationByMinutes = 19,
        isMixQuestionInMotorbikeExam = true
    ),
    RULE_A2(
        licenseType = com.ngotrieuvi544.driverlicense.data.model.LicenseType.A2,
        numbersOfQuestionByType = mapOf(
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_CONCEPT_AND_RULES to 9,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_BUSINESS to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.ETHICS_IN_DRIVING to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_TECHNIQUE to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.FIXING_CAR_QUESTION to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SIGNAL to 7,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SITUATION to 7
        ),
        minimumCorrectToPassExam = 23,
        totalNumberOfQuestion = 25,
        examDurationByMinutes = 19,
        isMixQuestionInMotorbikeExam = true
    ),
    RULE_A3(
        licenseType = com.ngotrieuvi544.driverlicense.data.model.LicenseType.A3,
        numbersOfQuestionByType = mapOf(
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_CONCEPT_AND_RULES to 9,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_BUSINESS to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.ETHICS_IN_DRIVING to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_TECHNIQUE to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.FIXING_CAR_QUESTION to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SIGNAL to 7,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SITUATION to 7
        ),
        minimumCorrectToPassExam = 23,
        totalNumberOfQuestion = 25,
        examDurationByMinutes = 19,
        isMixQuestionInMotorbikeExam = true
    ),
    RULE_A4(
        licenseType = com.ngotrieuvi544.driverlicense.data.model.LicenseType.A4,
        numbersOfQuestionByType = mapOf(
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_CONCEPT_AND_RULES to 9,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_BUSINESS to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.ETHICS_IN_DRIVING to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_TECHNIQUE to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.FIXING_CAR_QUESTION to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SIGNAL to 7,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SITUATION to 7
        ),
        minimumCorrectToPassExam = 23,
        totalNumberOfQuestion = 25,
        examDurationByMinutes = 19,
        isMixQuestionInMotorbikeExam = true
    ),
    RULE_B1(
        licenseType = com.ngotrieuvi544.driverlicense.data.model.LicenseType.B1,
        numbersOfQuestionByType = mapOf(
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_CONCEPT_AND_RULES to 9,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_BUSINESS to 0,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.ETHICS_IN_DRIVING to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_TECHNIQUE to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.FIXING_CAR_QUESTION to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SIGNAL to 9,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SITUATION to 9
        ),
        minimumCorrectToPassExam = 27,
        totalNumberOfQuestion = 30,
        examDurationByMinutes = 20,
    ),
    RULE_B2(
        licenseType = com.ngotrieuvi544.driverlicense.data.model.LicenseType.B2,
        numbersOfQuestionByType = mapOf(
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_CONCEPT_AND_RULES to 10,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_BUSINESS to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.ETHICS_IN_DRIVING to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_TECHNIQUE to 2,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.FIXING_CAR_QUESTION to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SIGNAL to 10,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SITUATION to 10
        ),
        minimumCorrectToPassExam = 32,
        totalNumberOfQuestion = 35,
        examDurationByMinutes = 22,
    ),
    RULE_C(
        licenseType = com.ngotrieuvi544.driverlicense.data.model.LicenseType.C,
        numbersOfQuestionByType = mapOf(
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_CONCEPT_AND_RULES to 10,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_BUSINESS to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.ETHICS_IN_DRIVING to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_TECHNIQUE to 2,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.FIXING_CAR_QUESTION to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SIGNAL to 14,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SITUATION to 11
        ),
        minimumCorrectToPassExam = 36,
        totalNumberOfQuestion = 40,
        examDurationByMinutes = 24,
    ),
    RULE_D(
        licenseType = com.ngotrieuvi544.driverlicense.data.model.LicenseType.D,
        numbersOfQuestionByType = mapOf(
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_CONCEPT_AND_RULES to 10,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_BUSINESS to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.ETHICS_IN_DRIVING to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_TECHNIQUE to 2,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.FIXING_CAR_QUESTION to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SIGNAL to 16,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SITUATION to 14
        ),
        minimumCorrectToPassExam = 41,
        totalNumberOfQuestion = 45,
        examDurationByMinutes = 26,
    ),
    RULE_E(
        licenseType = com.ngotrieuvi544.driverlicense.data.model.LicenseType.E,
        numbersOfQuestionByType = mapOf(
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_CONCEPT_AND_RULES to 10,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_BUSINESS to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.ETHICS_IN_DRIVING to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_TECHNIQUE to 2,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.FIXING_CAR_QUESTION to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SIGNAL to 16,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SITUATION to 14
        ),
        minimumCorrectToPassExam = 41,
        totalNumberOfQuestion = 45,
        examDurationByMinutes = 26,
    ),
    RULE_F(
        licenseType = com.ngotrieuvi544.driverlicense.data.model.LicenseType.F,
        numbersOfQuestionByType = mapOf(
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_CONCEPT_AND_RULES to 10,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_BUSINESS to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.ETHICS_IN_DRIVING to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.DRIVING_TECHNIQUE to 2,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.FIXING_CAR_QUESTION to 1,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SIGNAL to 16,
            com.ngotrieuvi544.driverlicense.data.model.QuestionType.TRAFFIC_SITUATION to 14
        ),
        minimumCorrectToPassExam = 41,
        totalNumberOfQuestion = 45,
        examDurationByMinutes = 26,
    ),
}

fun findCreateExamRuleByLicenseType(licenseType: com.ngotrieuvi544.driverlicense.data.model.LicenseType) : com.ngotrieuvi544.driverlicense.data.model.CreateExamRules {
    return enumValues<com.ngotrieuvi544.driverlicense.data.model.CreateExamRules>().first { it.licenseType == licenseType }
}

fun findCreateExamRuleByLicenseTypeString(licenseTypeString: String) : com.ngotrieuvi544.driverlicense.data.model.CreateExamRules {
    return enumValues<com.ngotrieuvi544.driverlicense.data.model.CreateExamRules>().first { it.licenseType.type == licenseTypeString}
}