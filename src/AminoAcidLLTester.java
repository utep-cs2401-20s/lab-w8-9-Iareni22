import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AminoAcidLLTest {
    @Test
    public void isSorted1(){
        String sequence = "GCCUACUGU";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        char[] B = {'\0', 'A', 'C', 'T'};
        char[] C = AminoAcidLL.sort(A).aminoAcidList();
    }

    @Test
    public void aminoAcidList1(){
        String sequence = "GCCUACUGU";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        char[] B = {'A', 'T', 'C'};
        char[] C = A.aminoAcidList();

        assertArrayEquals(B, C);
    }

}