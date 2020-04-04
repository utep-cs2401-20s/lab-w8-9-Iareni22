import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AminoAcidLLTest {
    /*
    Test Case 1 - sort() method
    This test case is meant to make sure the sort method works properly and efficiently without leaving any nodes in the
    incorrect order alphabetically.
     */
    @Test // Passed
    public void sort1(){
        String sequence = "GCUUACUGU";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        char[] B = {'A', 'C', 'T'};
        char[] C = AminoAcidLL.sort(A).aminoAcidList();
        assertArrayEquals(B, C);
    }

    /*
       Test Case 2 - aminoAcidList() method
       This test case is meant to make sure the aminoAcidList method correctly converts the list of nodes into an array
       of characters with. The method also ensures the createFromRNASequence method does its job well and with no errors.
        */
    @Test // Passed
    public void aminoAcidList1(){
        String sequence = "GCCUACUGU";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        char[] B = {'A', 'T', 'C'};
        char[] C = A.aminoAcidList();

        assertArrayEquals(B, C);
    }

    /*
   Test Case 3 - aminoAcidCompare() method
   This test case is meant to make sure the aminoAcidCompare method works well and is able to compare a node list of
   equal sizes with different counts. Since the method is not sorted to begin with I also use the sort method once again
   to ensure proper function of the method.
    */
    @Test // Passed
    public void aminoAcidCompare1(){
        String sequence = "GCCUACGCCUGUGCC";
        String test = "GCCUACUGU";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        AminoAcidLL B = AminoAcidLL.createFromRNASequence(test);
        AminoAcidLL.sort(B);
        assertEquals(2,A.aminoAcidCompare(B));
    }

    /*
  Test Case 4 - aminoAcidCompare() method
  This test case is meant to make sure the aminoAcidCompare method works well and is able to compare a node list of
  different sizes with different counts. The method will use a testing list which is longer in length than the original.
   */
    @Test // Passed
    public void aminoAcidCompare2(){
        String sequence = "GCCUACGCCUGUGCC";
        String test = "GCCUACUGUUGUUGUGCCGAGGAG";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        AminoAcidLL B = AminoAcidLL.createFromRNASequence(test);
        AminoAcidLL.sort(B);
        assertEquals(4,A.aminoAcidCompare(B));
    }

    /*
   Test Case 5 - aminoAcidCompare() method
   This test case compares two lists once again but this time of different lengths. In this case the starting ist is
   longer, making use of the statements comparing the length of the lists.
    */
    @Test // Passed
    public void aminoAcidCompare3(){
        String sequence = "GCCUACGCCUGUGCCGAG";
        String test = "GCCUACUGU";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        AminoAcidLL B = AminoAcidLL.createFromRNASequence(test);
        AminoAcidLL.sort(B);
        assertEquals(3,A.aminoAcidCompare(B));
    }

    /*
   Test Case 6 - codonCompare() method
   This test case is meant to ensure the condonCompare method works well and is able to compare the codons individually
   rather than the previous method that did amino acids. It is also being tested with equal length lists.
    */
    @Test // Passed
    public void codonCompare1(){
        String sequence = "GCCUACGCCUGUGCCGAG";
        String test = "GCCUACUGU";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        AminoAcidLL B = AminoAcidLL.createFromRNASequence(test);
        AminoAcidLL.sort(B);
        assertEquals(3,A.aminoAcidCompare(B));
    }

    /*
   Test Case 7 - codonCompare() method
   This test case is meant to ensure the condonCompare method works well once again, but this time with lists of varying
   sizes, with the testing list being longer.
    */
    @Test // Passed
    public void codonCompare2(){
        String sequence = "GCCUACUGU";
        String test = "GCCUACGCCUGUGCCGAGACG";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        AminoAcidLL B = AminoAcidLL.createFromRNASequence(test);
        AminoAcidLL.sort(B);
        assertEquals(4,A.aminoAcidCompare(B));
    }

    /*
   Test Case 8 - codonCompare() method
   This test case is meant to ensure the condonCompare method works well once again, but this time with lists of varying
   sizes, with the starting list being longer.
    */
    @Test // Passed
    public void codonCompare3(){
        String sequence = "GCCUACGCCUGUGCCGAGACGGCCUACUGU";
        String test = "GCCUACUGU";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        AminoAcidLL B = AminoAcidLL.createFromRNASequence(test);
        AminoAcidLL.sort(B);
        assertEquals(7,A.aminoAcidCompare(B));
    }

    /*
   Test Case 1 - isSorted() method
   This test case is meant to make sure the isSorted method works well and can tell whenever a list is out of alphabetical
   order. The list in this case is not in order so the result should be false.
    */
    @Test // Passed
    public void isSorted1(){
        String sequence = "GCUUACUGU";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        assertFalse(A.isSorted());
    }

    /*
  Test Case 1 - isSorted() method
  This test case is meant to make sure the isSorted method works well like the previous one did, but instead I will sort
  the method and expect my result to be true.
   */
    @Test // Passed
    public void isSorted2(){
        String sequence = "GCUUACUGU";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        AminoAcidLL.sort(A);
        assertTrue(A.isSorted());
    }
    

}