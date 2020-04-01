import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File
import kotlin.test.assertEquals

const val TEST_CASE_PATH = "src/test/resources"


class SolverTest {
    private val solver = Solver()

    @ParameterizedTest
    @CsvSource(
        "1, 1276",
        "2, 2520",
        "3, 1087",
        "4, 1621",
        "5, 2",
        "6, 12",
        "7, 17"
    )
    fun `test with files`(fileNum: Int, ans: Int) {
        val testCase = File(TEST_CASE_PATH)
            .resolve("test$fileNum")
            .readLines()
            .map {
                val tokens = it.split(" ").map(String::toInt)
                Train(tokens[0], tokens[1], tokens[2], tokens[3])
            }
        assertEquals(solver.solve(testCase), ans)
    }
}
