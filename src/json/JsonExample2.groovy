package json

import com.thoughtworks.xstream.annotations.XStreamImplicit

@ToJson
class Person {

    String name
    String phone

    @XStreamImplicit
    List<String> entity
}

def list = [] << "item1" << "item2"

def person1 = new Person(name:"Felipe",phone:"502123456",entity:list)
println person1

def json = person1.toString()

def person2 = Person.createInstanceFrom(json)
println person2

assert person1.toString() == person2.toString()



