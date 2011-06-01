package xml

//Simple Example
@ToXml
class Person {
    String name, phone
}

def person1 = new Person(name:"Felipe",phone:"502123456")
println person1

String xml = person1.toString()
def person2 = Person.createInstanceFrom(xml)
println person2

assert person1.toString() == person2.toString()


