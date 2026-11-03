package com.example

import com.example.data.model.Difficulty
import com.example.data.model.QuestionType
import com.example.data.repository.CodeData
import com.example.data.repository.CurriculumData
import com.example.data.repository.ExamData
import com.example.data.repository.McqData
import com.example.data.repository.RevisionData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testCurriculumTopicsCompleteness() {
        // Must contain all 3 modules
        val mod1 = CurriculumData.getTopicsForModule("mod1")
        val mod2 = CurriculumData.getTopicsForModule("mod2")
        val mod3 = CurriculumData.getTopicsForModule("mod3")

        assertTrue("Module 1 must have topics", mod1.isNotEmpty())
        assertTrue("Module 2 must have topics", mod2.isNotEmpty())
        assertTrue("Module 3 must have topics", mod3.isNotEmpty())

        assertEquals(CurriculumData.topics.size, mod1.size + mod2.size + mod3.size)

        // Verify each topic has required sections
        CurriculumData.topics.forEach { topic ->
            assertTrue(topic.title.isNotBlank())
            assertTrue(topic.simpleDefinition.isNotBlank())
            assertTrue(topic.detailedExplanation.isNotBlank())
            assertTrue(topic.stepByStepProcess.isNotEmpty())
            assertTrue(topic.realWorldExamples.isNotEmpty())
            assertTrue(topic.advantages.isNotEmpty())
            assertTrue(topic.disadvantages.isNotEmpty())
            assertTrue(topic.applications.isNotEmpty())
            assertTrue(topic.conclusion.isNotBlank())
            assertTrue(topic.examPoints.isNotEmpty())
        }
    }

    @Test
    fun testExamQuestionsCompleteness() {
        val twoMarks = ExamData.getQuestionsForType(QuestionType.VERY_SHORT)
        val fiveMarks = ExamData.getQuestionsForType(QuestionType.SHORT)
        val tenMarks = ExamData.getQuestionsForType(QuestionType.LONG)
        val viva = ExamData.getQuestionsForType(QuestionType.VIVA)

        assertTrue(twoMarks.isNotEmpty())
        assertTrue(fiveMarks.isNotEmpty())
        assertTrue(tenMarks.isNotEmpty())
        assertTrue(viva.isNotEmpty())

        ExamData.questions.forEach { q ->
            assertTrue(q.question.isNotBlank())
            assertTrue(q.simpleAnswer.isNotBlank())
            assertTrue(q.keywords.isNotEmpty())
            assertTrue(q.rememberPoints.isNotEmpty())
        }
    }

    @Test
    fun testMcqData() {
        assertTrue(McqData.questions.size >= 12)
        McqData.questions.forEach { mcq ->
            assertEquals(4, mcq.options.size)
            assertTrue(mcq.correctIndex in 0..3)
            assertTrue(mcq.explanation.isNotBlank())
        }

        val beginner = McqData.getQuestionsForDifficulty(Difficulty.BEGINNER)
        val intermediate = McqData.getQuestionsForDifficulty(Difficulty.INTERMEDIATE)
        val advanced = McqData.getQuestionsForDifficulty(Difficulty.ADVANCED)

        assertTrue(beginner.isNotEmpty())
        assertTrue(intermediate.isNotEmpty())
        assertTrue(advanced.isNotEmpty())
    }

    @Test
    fun testProgrammingExamples() {
        assertEquals(7, CodeData.examples.size)
        CodeData.examples.forEach { code ->
            assertTrue(code.title.isNotBlank())
            assertTrue(code.code.contains("#include"))
            assertTrue(code.lineExplanations.isNotEmpty())
            assertTrue(code.expectedOutput.isNotBlank())
            assertTrue(code.realWorldApplication.isNotBlank())
        }
    }

    @Test
    fun testRevisionData() {
        assertTrue(RevisionData.comparisonTables.isNotEmpty())
        assertTrue(RevisionData.quickRevisionItems.isNotEmpty())

        RevisionData.comparisonTables.forEach { table ->
            assertTrue(table.rows.isNotEmpty())
        }
    }
}
