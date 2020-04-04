class AminoAcidLL{
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL(){

  }


  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  AminoAcidLL(String inCodon){

    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    counts = new int[codons.length];
    incrCodons(inCodon);

  }

  /********************************************************************************************/
  /* Helper method to increment the codon counter
   */
  public void incrCodons(String c){
    for(int i = 0; i < codons.length; i++){
      if(codons[i].equals(c)){
        counts[i]++;
      }
    }
  }

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops,
   * if not passes the task to the next node.
   * If there is no next node, add a new node to the list that would contain the codon.
   */
  private void addCodon(String inCodon){
    if(aminoAcid == AminoAcidResources.getAminoAcidFromCodon(inCodon)){
      incrCodons(inCodon);
    }
    else{
      if(next != null){
        next.addCodon(inCodon);
      }
      else{
        next = new AminoAcidLL(inCodon);
      }
    }
  }


  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount() {
    int total = 0;
    for(int i = 0; i < counts.length; i++){
      total += counts[i];
    }
    return total;
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){
    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    int diff = 0;
    for(int i=0; i<codons.length; i++){
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts. 
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList) {//use total count
    if(!inList.isSorted()){
      System.out.print("List not sorted");
      return -1;
    }
    if(next == null && inList.next == null){
      return totalDiff(inList);
    }
    if(inList.next == null){
      return totalCount() + next.aminoAcidCompare(inList);
    }
    if(next == null){
      return totalCount() + aminoAcidCompare(inList.next);
    }

    return totalDiff(inList) + (next.aminoAcidCompare(inList.next));
  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){// use codonDiff
    if(!inList.isSorted()){
      System.out.print("List not sorted");
      return -1;
    }
    if(next == null && inList.next == null){
      return totalDiff(inList);
    }
    if(inList.next == null){
      return totalCount() + next.codonCompare(inList);
    }
    if(next == null){
      return totalCount() + codonCompare(inList.next);
    }

    return codonDiff(inList) + (next.codonCompare(inList.next));
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    if(next == null){
      return new char[]{aminoAcid};
    }

    char[] a = next.aminoAcidList();
    char[] result = new char[a.length + 1];

    for(int i = 0; i < a.length; i++){
        result[i + 1] = a[i];
    }
    result[0] = aminoAcid;
    return result;
  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    if(next == null){
      return new int[]{totalCount()};
    }

    int[] a = next.aminoAcidCounts();
    int[] result = new int[a.length + 1];

    for(int i = 0; i < a.length; i++){
      result[i + 1] = a[i];
    }
    result[0] = totalCount();
    return result;
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    if(next == null){
      return true;
    }
    else if(aminoAcid > next.aminoAcid){
      return false;
    }
    return next.isSorted();
  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){
    AminoAcidLL result = new AminoAcidLL(inSequence.substring(0, 3));

    while(inSequence.length() > 0) {
      String word = inSequence.substring(0, 3);

      if(word.length() != 0){
        result.addCodon(word);
        inSequence = inSequence.substring(3);
      }
      else{
        inSequence = "";
      }
    }
    return result;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList){

    if(inList.isSorted()){
      return inList;
    }
    AminoAcidLL iter = inList;
    AminoAcidLL min;
    AminoAcidLL traverse;
    AminoAcidLL previous;
    AminoAcidLL start = inList;
    
    while(iter.next != null){
      min = iter;
      previous = min;
      traverse = iter.next;
      
      while(traverse != null) {
        if (traverse.aminoAcid < min.aminoAcid) {
          min = traverse;
        }
        traverse = traverse.next;
      }
        if(previous == min){
          iter = iter.next;
          continue;
        }
      
      while(previous.next != min){
        previous = previous.next;
      }

      previous.next = min.next;
      min.next = iter;
      if(start != iter){
        start.next = min;
      }
      start = min;
    }
    return inList;
  }

}



