import groovy.transform.Immutable

@Immutable final class ImmutableGroovyDog {
    String name
}

ImmutableGroovyDog billy = new ImmutableGroovyDog('Billy')
billy.name = 'Buster'
