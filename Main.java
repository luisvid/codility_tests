import java.lang.reflect.Array;
import java.math.*;
import java.util.Arrays;

public class Main {
 
    public static void main(String args[]) {

        // System.out.println(
        //     isPalindromeUsingStringBuilder("abcddcbaa")
        //     );

        // System.out.println(BinaryGap(529));

        // int a[]={1,2,3,4,5};
        // System.out.println(cyclicRotation(a, 2));
    
        // System.out.println(oddOccurrencesInArray(new int[] { 9, 3, 9, 3, 9, 7, 9 }));

        // System.out.println(frogImp(10, 70, 30));

        // System.out.println(permMissingElem(new int[] { 5, 3, 1, 2 }));

        // System.out.println(tapeEquilibrium(new int[] { 3, 1, 2, 4, 3}));
        // System.out.println(tapeEquilibrium(new int[] { 3, 1}));

        // System.out.println(permCheck(new int[] { 4, 1, 3, 2 }));
        // System.out.println(permCheck(new int[] { 4, 1, 3 }));
        
        // System.out.println(frogRiverOne( 5, new int[] { 1,3,1,4,2,3,5,4 }));

        // System.out.println(Arrays.toString(maxCounters(5, new int[] { 3, 4, 4, 6, 1, 4, 4 })));

        // System.out.println(missingInteger(new int[] { 1, 3, 6, 4, 1, 2 }));
        // System.out.println(missingInteger(new int[] { 1, 2, 3 }));
        
        // [0, 1, 3, -2, 0, 1, 0, -3, 2, 3]
        System.out.println(deeestPit(new int[] { 0, 1, 3, -2, 0, 1, 0, -3, 2, 3 }));
      
    }

    // deeestPit
    public static int deeestPit(int[] A) {
        
        int N = A.length;

		int depth = -1;
		int P, Q, R;
        int i = 0, j, k;
        
		while (i < N - 2) {
			P = A[i];
			j = i + 1;
			int p = P;
			while (j < N - 1 && A[j] < p) {
				p = A[j++];
			}
			if (j == N - 1) {
				break;
			}
			if (j > i + 1) {
				Q = A[j - 1];
			} else {
				i++;
				continue;
			}
			k = j;
			int q = Q;
			while (k < N && A[k] > q) {
				q = A[k++];
			}

			if (k > j) {
				R = A[k - 1];
				depth = Math.max(depth, Math.min(P - Q, R - Q));
				i = k - 1;
			} else {
				i = j - 1;
			}
		}

		return Math.max(depth, -1);
	}

    // isPalindromeUsingStringBuilder
    public static boolean isPalindromeUsingStringBuilder(String text) {
        String clean = text.replaceAll("\\s+", "").toLowerCase();
        StringBuilder plain = new StringBuilder(clean);
        StringBuilder reverse = plain.reverse();
        return (reverse.toString()).equals(clean);
    }

    // find longest sequence of zeros in binary representation of an integer.
    public static int BinaryGap(int arrayLenght) {
        int finalGap = 0;
        //1 is  0
        if (arrayLenght == 1) {
            return 0;
        }
        //use the shortest way to convert to a binary representation
        char binaryRep[] = Integer.toBinaryString(arrayLenght).toCharArray();
        System.out.println(binaryRep);

        int tempGap=0; 
        //the binary representation must start with 1 we continue counting the 0 while 
        // we found a new one, and then return the longest binary gap 
        // the binary numbers ending on 0 cant be counted because it should be 1 0000..1 
        // to be a valid gap
        for (int x = 0; x < binaryRep.length; x++) {
            if(binaryRep[x]=='0'){
                tempGap++;
                continue;
            }else if(binaryRep[x]=='1'){
                if(tempGap>finalGap)
                finalGap=tempGap;
                tempGap=0;
            }
            
        }
        return finalGap;
    }

    // cyclicRotation
    public static int[] cyclicRotation (int[] A, int K) {
        
        // Create a second array 
        int [] rotated_A = new int[A.length];
        
        // number of elements in A
        int arrayLenght = A.length;

        // if A is empty OR has 1 element OR the number of rotations would simply return the original array
        if (arrayLenght == 0 || arrayLenght == 1 || K % arrayLenght == 0) {
            return A;
        }
        else {
            // modify K to avoid wasting processing time
            if (K > arrayLenght) {
                K = K % arrayLenght;
            }

            // iterate through rotated_A and populated with the rotated values from A
            for(int i=0; i< arrayLenght; i++){
                int new_position = (i + K) % arrayLenght; // using "mod" to do Cyclic Rotation           
                rotated_A[new_position] = A[i]; // put A[i] to the new position
            }
            
            return rotated_A;        	
        }
    }

    // The main challenge of this question is the XOR operations: X^X=0, and 0^X=X. 
    // Time: O(N)
	// Space: O(1)
	public static int oddOccurrencesInArray(int[] A) {
		// write your code in Java SE 8
		int elem = 0;

		for (int i = 0; i < A.length; i++) {
			elem ^= A[i];
		}
		return elem;
    }
    
    // The main challenge of this question is the XOR operations: X^X=0, and 0^X=X. 
    // Logically, the addition and subtraction operations also are able to do this work. 
    // But taking the overflow in computer into consideration, they become a very bad choice.

    // la otra solución es saber que la suma de 1 a n es (n * (n+1)) // 2, a eso le resto la suma
    // del array pasado por parámetro y me el entero faltante
	public static int permMissingElem (int[] A) {
        // write your code in Java SE 8
        
        // number of elements in A
        int arrayLenght = A.length;

		int xorSum = 0;

		for (int index = 0; index < A.length; index++) {
			xorSum = xorSum ^ A[index] ^ (index + 1);
		}
		return xorSum ^ (arrayLenght + 1);
    }
    
    // frogImp
    public static int frogImp(int X, int Y, int D) {
        
        if ((Y - X) % D == 0) {
            return (Y - X) / D;
        }
        
        return (Y - X) / D + 1;
    }    

    // The variable of head stores the sum of the heading part of the tape. 
    // And the variable of tail stores the sum of tailing part. 
    // Then, we move the index from 2nd position to the last 2nd position. 
    // Every time we move the index, we adjust both head and tail, compute and compare the difference.
    public static int tapeEquilibrium(int[] A) {

        int sumHead = 0;
        int sumTail = 0;
        int minDif = 0;
        int arrayLenght = A.length;

        sumHead = A[0];

        for (int i =1;i<A.length;i++) {
            sumTail += A[i];
        }

        minDif = Math.abs(sumHead - sumTail);

        for (int P = 1; P < (arrayLenght-1); P++){
            sumHead += A[P];
            sumTail -=A[P];
            if (Math.abs(sumHead - sumTail) < minDif)
                minDif = Math.abs(sumHead - sumTail);
        }

        return  minDif;  

    }

	// Time: O(N)
	// Space: O(N)
	// permCheck
	public static int permCheck(int[] A) {
		// write your code in Java SE 8
		boolean[] seen = new boolean[A.length + 1];

		// repeated elements
		for (int i = 0; i < A.length; i++) {
			if(A[i] < 1 || A[i] > A.length) return 0;   // Out of range
			if(seen[A[i]] == true) return 0;            // met before
			else seen[A[i]] = true;                     // first time meet
		}
		
		return 1;
	}

    // Find the earliest time when a frog can jump to the other side of a river.
    // Concept here is to create a counter with number of jumps needed to cross the river 
    // and an array that is long as a river initialed in false. When the leaf falls on a 
    // specific place, we check if the the leaf already fell to that spot and if not we decrease 
    // counter. When counter hits zero, that is the earliest time the frog can get to other side
    public static int frogRiverOne(int X, int[] A) {
        
        int steps = X;

        boolean[] bitmap = new boolean[steps+1];

        for(int i = 0; i < A.length; i++){
            if(!bitmap[A[i]]){
                bitmap[A[i]] = true;
                steps--;
            }
            if(steps == 0) return i;
        }
        return -1;
    }

    // We could use lazy-write to improve the performance. 
    // When receiving the max_counter command, we record the current-max value, 
    // but do not change the list content. Only when we are going to return the 
    // list or increace a specific element, we will apply the stored value to 
    // the corresponding element(s).
    public static int[] maxCounters(int N, int[] A) {
        
        int[] resultArray = new int[N]; // The list to be returned
        int max_counter = 0;            // The used value in previous max_counter command
        int current_max = 0;            // The current maximum value of any counter
        int command ;                   // 1 ≤ command ≤ N, then operation is increase(X), 
                                        // N + 1 then operation is max_counter
                                        // also command is the position in the resultArray

        for (int i = 0; i < A.length; i++) {
            command = A[i];
            if (command >= 1 && command <= N) {     // 1 <= command <= N
                // increase(X) command
                if (max_counter > resultArray[command-1]) {
                    // lazy write
                    resultArray[command-1] = max_counter;
                }
                resultArray[command-1]++;   // Increase(X)

                if (current_max < resultArray[command-1]) {
                    current_max = resultArray[command-1];   // update to new current_max
                }
            } else {
                // max_counter command
                // just record the current maximum value for later write
                max_counter = current_max;
            }
        }

        for (int j = 0; j < resultArray.length; j++) {
            if (resultArray[j] < max_counter ) {
                // This element has never been used/updated after previous max_counter command
                resultArray[j] = max_counter;
            }
        }

        return resultArray;
    }

    // Solve it with Pigeonhole principle.
    // There are N integers in the input. So for the
    // first N+1 positive integers, at least one of
    // them must be missing.
    public static int missingInteger (int[] A) {

        // variable representing the number of elements in A
        int arrayLenght = A.length;

        // We only care about the first N+1 positive integers.
        // occurrence[i] is for the integer i+1.
        boolean [] occurrence = new boolean[arrayLenght + 1];

        // iterate through A and update occurrence accordingly 
        for (int i = 0; i < arrayLenght; i++) {
            if (A[i] >= 1 && A[i] <= arrayLenght + 1) {
                // update occurrence to show that A contains that number
                occurrence[A[i]-1] = true;
            }
        }

        // Find out the missing minimal positive integer.
        for (int j = 0; j < occurrence.length ; j++) {
            // return the first value in occurrence that is false
            System.out.println(occurrence[j]);
            if (occurrence[j] == false) {
                return j+1;
            }
        }
        
        //error
        return -1;
    
    }

    // maxSlice
    public static maxSlice(){};

}

