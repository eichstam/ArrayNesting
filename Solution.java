import java.util.HashSet;
import java.util.Iterator;

/**
 *
565. Array Nesting

A zero-indexed array A consisting of N different integers is given.
The array contains all integers in the range [0, N - 1].

Sets S[K] for 0 <= K < N are defined as follows:

S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.

Sets S[K] are finite for each K and should NOT contain duplicates.

Write a function that given an array A consisting of N integers, return
the size of the largest set S[K] for this array.

Example 1:

Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation: 
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}

Note:

    N is an integer within the range [1, 20,000].
    The elements of A are all distinct.
    Each element of array A is an integer within the range [0, N-1].

 *
 */

public class Solution {
    public int arrayNesting(int[] nums) {
        int maxSize = 0;
        int[] maxSizeArray = new int[nums.length];
        int i = 0;
        
        for(i = 0; i < nums.length; i++) {
          maxSizeArray[i] = 0;
        }
        HashSet<Integer> currHashSet = new HashSet<Integer>();
        for(i = 0; i < nums.length; i++) {
          if(maxSizeArray[i] == 0) {
            int currValue = nums[i];
            currHashSet.clear();
            while(!currHashSet.contains(currValue)) {
              currHashSet.add(currValue);
              currValue = nums[currValue];
            }
            if(currHashSet.size() > maxSize) {
              maxSize = currHashSet.size();
            }
            Iterator it = currHashSet.iterator();
            while(it.hasNext()) {
              int k = (int) it.next();
              maxSizeArray[k] = currHashSet.size();
            }
          }
        }
        return maxSize;
    }
    
    public static void main(String[] args) {
      Solution s = new Solution();
      int maxSize = 0;
      
      int[] nums1 = {5,4,0,3,1,6,2};
      maxSize = s.arrayNesting(nums1);
      System.out.println("maxSize: " + maxSize);

      int[] nums2 = {};
      maxSize = s.arrayNesting(nums2);
      System.out.println("maxSize: " + maxSize);

      int[] nums3 = {0};
      maxSize = s.arrayNesting(nums3);
      System.out.println("maxSize: " + maxSize);

    }
}
