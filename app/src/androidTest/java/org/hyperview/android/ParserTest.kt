package org.hyperview.android

import arrow.core.Either
import arrow.core.None
import arrow.core.Some
import org.hyperview.android.types.HXMLElement
import org.hyperview.android.types.HXMLError
import org.hyperview.android.types.HXMLTag
import org.hyperview.android.types.ScreenAttributes
import org.hyperview.android.types.ViewAttributes
import org.hyperview.android.types.tagError
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
        val expected = Either.Right(HXMLElement.HvDoc(children = listOf()))
        val actual = parse(input)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseRootElementFragment() {
        val input = "<view></view>"
        val expected = Either.Right(
            HXMLElement.HvView(
                attributes = ViewAttributes(),
                children = mutableListOf()
            )
        )
        val actual = parse(input)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseScreenElement() {
        val input = "<doc><screen></screen></doc>"
        val expected = Either.Right(
            HXMLElement.HvDoc(
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
            HXMLElement.HvDoc(
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
        val expected = Either.Left(tagError(HXMLTag.SCREEN, HXMLTag.SCREEN))
        val actual = parse(input)
        assertEquals(expected, actual)
    }
}
