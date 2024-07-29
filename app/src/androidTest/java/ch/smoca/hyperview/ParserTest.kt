package ch.smoca.hyperview

import arrow.core.Either
import arrow.core.None
import arrow.core.Some
import ch.smoca.hyperview.types.HXMLRoot
import ch.smoca.hyperview.types.HXMLError
import ch.smoca.hyperview.types.HXMLElement
import ch.smoca.hyperview.types.ScreenAttributes
import org.junit.Assert.assertEquals
import org.junit.Test

class ParserTest {
    @Test
    fun testParseRootInvalidHyperviewXML() {
        val input = ""
        val expected = Either.Left(HXMLError("HvView '${input}' is not valid Hyperview XML"))
        val actual = parse(input)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseRootElementNonExistent() {
        val input = "<hello></hello>"
        val expected = Either.Left(HXMLError("<hello> is not a valid root element"))
        val actual = parse(input)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseRootElementInvalid() {
        val input = "<screen></screen>"
        val expected = Either.Left(HXMLError("<screen> is not a valid root element"))
        val actual = parse(input)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseRootElementDocument() {
        val input = "<doc></doc>"
        val expected = Either.Right(HXMLRoot.View(children = listOf()))
        val actual = parse(input)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseRootElementFragment() {
        val input = "<view></view>"
        val expected = Either.Right(HXMLRoot.Fragment(children = listOf()))
        val actual = parse(input)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseScreenElement() {
        val input = "<doc><screen></screen></doc>"
        val expected = Either.Right(
            HXMLRoot.View(
                children =
                listOf(
                    HXMLElement.HvScreen(
                        children = listOf(),
                        attributes = ScreenAttributes(id = None)
                    )
                )
            )
        )
        val actual = parse(input)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseScreenElementsWithAttribute() {
        val input = "<doc><screen id='Yasamin'></screen></doc>"
        val expected = Either.Right(
            HXMLRoot.View(
                children =
                listOf(
                    HXMLElement.HvScreen(
                        children = listOf(),
                        attributes = ScreenAttributes(id = Some("Yasamin"))
                    )
                )
            )
        )
        val actual = parse(input)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseNestedScreenElement() {
        val input = "<doc><screen><screen></screen></screen></doc>"
        val expected = Either.Left(HXMLError("<screen> can only be a child of <doc>"))
        val actual = parse(input)
        assertEquals(expected, actual)
    }
}
