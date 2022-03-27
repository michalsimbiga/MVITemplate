package com.msimbiga.domain.models

object Mocks {

    val planetMock = Planet("name", "url")
    val pageMock = Page(2,4,"next", null)

    val charMock = Character(
        1,
        "Test",
        "alive",
        "specie",
        "type",
        "gender",
        origin = planetMock,
        location = planetMock,
        "image",
        listOf("asdsdas"),
        "Created",
        "asdasd"
    )
}
