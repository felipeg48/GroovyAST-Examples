package async

@Asynchronous
class Sample{

    String name
    String phone

    @Async
    def expensiveMethod(){
        sleep 15000
        println "[New Thread] Finished expensiveMethod..."
    }

    @Async
    def other(){
        sleep 5000
        println "[Another Thread] Hello"
    }
}

println "[Main] Start"
def sample = new Sample(name:"Felipe",phone:"1800-GROOVY")
sample.expensiveMethod()
sample.other()
println "[Main] Finished"

