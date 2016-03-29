object Start{

    val r = scala.util.Random

    class Grid(xDim:Int , yDim:Int){

        var coords = Array.ofDim[Int](xDim,yDim);
        
        def printGrid(){          
            coords foreach { row => row foreach print; println }
        }
        
    }

    /*
        An array of Ints with associated probability values,
        when get() is called it will return an element based on those probabilities
        WARNING: Almost certainly an off by 1 error in there.        
    */
    class ProbabilityArray(t: Array[Pair[Int, Int]]){
            var myArr = t;
            def getOne() : Int ={
                var index = r.nextInt(101); //precision up to the hundredths place
                var sum = 0;
                for(p <- myArr){
                    if(index <= sum + p._2){
                        return p._1;
                    }else{
                        sum += p._2;
                    }
                }
                return myArr(myArr.length-1)._1;
            }

    }


    /*


    */
    def probArrUnitTest(){
            //Providing this array will result in a 50/50 probability of 1 or 0.
            var coinFlip = Array[Pair[Int,Int]](
                Pair[Int, Int](1, 10),
                Pair[Int, Int](2, 10),
                Pair[Int, Int](3, 20),
                Pair[Int, Int](4, 30),
                Pair[Int, Int](5, 30)
            );
            var G = new ProbabilityArray(coinFlip)
            for(i <- 1 to 100000){
                println(G.getOne());
            }
    }

    class StateMachine(startState: Int, t: Array[ProbabilityArray]){
        var table = t;
        var currentState = startState;
        def transition() : Int = {
            currentState = table(currentState).getOne();
            return currentState;
        }
        def printMachine(){          
            //table foreach { row => row foreach print; println }
        }
    }

    def main(args: Array[String]){
        var quarters = new ProbabilityArray(
                    Array[Pair[Int,Int]](
                        Pair[Int, Int](0, 25),
                        Pair[Int, Int](1, 25),
                        Pair[Int, Int](2, 25),
                        Pair[Int, Int](3, 25)
                    )
                );
        var g = new StateMachine(
            0,
            Array[ProbabilityArray](quarters, quarters, quarters, quarters)
        );

        for(i <- 1 to 100){
            println(g.transition());
        }
        //g.printMachine();
    }

}
