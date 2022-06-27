package demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

@Controller
class HtmlController{
	@GetMapping("/")
	fun blog(model: Model): String{
		model["title"] = "Blog"
		return "blog"
	}
}

fun LocalDateTime.format() = this.format(englishDateFormatter)

private val daysLookup = (1..31).associate { it.toLong() to getOrdinal(it) }

private val englishDateFormatter = DateTimeFormatterBuilder()
	.appendPattern("yyyy-MM-dd")
	.appendLiteral(" ")
	.appendText(ChronoField.DAY_OF_MONTH, daysLookup)
	.appendLiteral(" ")
	.appendPattern("yyyy")
	.toFormatter(Locale.ENGLISH)

private fun getOrdinal(n: Int) = when {
	n in 11..13 -> "${n}th"
	n % 10 == 1 -> "${n}st"
	n % 10 == 2 -> "${n}nd"
	n % 10 == 3 -> "${n}rd"
	else -> "${n}th"
}

fun String.toSlug() = toLowerCase()
	.replace("\n", " ")
	.replace("[^a-z\\d\\s]".toRegex(), " ")
	.split(" ")
	.joinToString("-")
	.replace("-+".toRegex(), "-")

