#Letter Soup Java Algorithm 

To be considered: the complexity of this algorithm is based on the numers of words you like to write inside the matrix, 
and how big matrix is, this means if you want to write a big numbers of words inside a small matrix, 
the algorithm will take some time to find the correct state where the whole words are inside matrix. 
And you have to be SURE that the words you like to write inside the matrix can be writen in  at least one state, 
this can be solved simple as counting the whole letters of your words and comparing if this number is <= that NxN


##How to use this algorithm:

*Make an instance of LetterSoup class, just passing to the constructor the order of the matrix that you like to create, and now you can get the letter soup matrix with just one method, .

```java
public static void main(String[ ] arg)
{
    LetterSoup Game = new LetterSoup(10);
    char [][]matrix = Game.getMatrix();

    for(int i = 0; i < matrix.length; i++)
    {
        for(int j = 0; j < matrix[i].length; j++)
            System.out.print(matrix[i][j]+" "); 

        System.out.println("\n");
   }
            
}
```

* To choose which words are going to be inside the matrix, modify this Class atribute

```java
private String [] words = { "pez", "oso","ave","boa","lobo","gato"};
```
