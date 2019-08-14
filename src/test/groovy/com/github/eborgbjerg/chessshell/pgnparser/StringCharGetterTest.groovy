package com.github.eborgbjerg.chessshell.pgnparser

import spock.lang.Specification

class StringCharGetterTest extends Specification {

    def 'minimal test'() {
        given:
        def getter = new StringCharGetter('1.')

        when:
        def first = getter.char
        then:
        first == '1' as char
        !getter.done

        when:
        def second = getter.char
        then:
        second == '.' as char
        getter.done
    }
}
