package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Merge-Sorted-Array-b70a5df43622438da99f163857dadbae
 */
public class MergeSortedArray {
    //nums.length = m+n
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int idx = m + n - 1;

        while(i >= 0 && j >= 0)
        {
            if(nums1[i] > nums2[j]){
                nums1[idx] = nums1[i];
                i--;
            } else {
                nums1[idx] = nums2[j];
                j--;
            }
            idx--;
        }

        //put remainder
        while(i >= 0){
            nums1[idx] = nums1[i];
            idx--;
            i--;
        }
        while(j >= 0){
            nums1[idx] = nums2[j];
            idx--;
            j--;
        }
    }
}
