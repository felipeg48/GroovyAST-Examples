package json

@ToJson
class Person {

    String name
    String phone
}

def person1 = new Person(name:"Felipe",phone:"502123456")
println person1


def json = person1.toString()

def person2 = Person.createInstanceFrom(json)
println person2

assert person1.toString() == person2.toString()


