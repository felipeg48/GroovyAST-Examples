package async

@Asynchronous
class Sample{

    String name
    String phone

    @Async
    def expensiveMethod(){
		println "[${Thread.currentThread()}] Started expensiveMethod"
        sleep 15000
        println "[${Thread.currentThread()}] Finished expensiveMethod..."
    }

    @Async
    def otherMethod(){
		println "[${Thread.currentThread()}] Started otherMethod"
        sleep 5000
        println "[${Thread.currentThread()}] Finished otherMethod"
    }
}

println "[${Thread.currentThread()}] Start"
def sample = new Sample(name:"Felipe",phone:"1800-GROOVY")
sample.expensiveMethod()
sample.otherMethod()
println "[${Thread.currentThread()}] Finished"

