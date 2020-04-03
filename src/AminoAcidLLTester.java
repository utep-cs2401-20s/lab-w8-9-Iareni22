import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AminoAcidLLTest {
    @Test
    public void createFromRNASequence1(){
        String sequence = "GCUACGGAGCUU";
        AminoAcidLL A = AminoAcidLL.createFromRNASequence(sequence);
        char[] B = A.aminoAcidList();
        for(int i = 0; i < B.length;i++){
            System.out.print(B[i]);
        }
        char[] C = {' ', 'A', 'T', 'E', 'L'};

        assertArrayEquals(C, B);

    }

}