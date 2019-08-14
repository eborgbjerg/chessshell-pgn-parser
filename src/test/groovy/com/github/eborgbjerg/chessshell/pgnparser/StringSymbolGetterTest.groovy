package com.github.eborgbjerg.chessshell.pgnparser

import spock.lang.Specification

class StringSymbolGetterTest extends Specification {

    def 'parse tiny string'() {
        given:
        def symbolGetter = new StringSymbolGetter('1.e4')

        when:
        symbolGetter.readSymbol()
        then:
        symbolGetter.symbolType == PgnSymbolType.MOVE_NUMBER_INDICATOR

        when:
        symbolGetter.readSymbol()
        then:
        symbolGetter.symbolType == PgnSymbolType.MOVE_TEXT // todo  ponder efficiency, avoidance of copying etc.
    }

}
