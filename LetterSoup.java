/* To be considered: the complexity of this algorithm is based on the numers of words you like
to write inside the matriz, and how big matriz is, this means if you want to write a big numbers of words 
inside a small matriz, the algorithm will take some time to find the correct state where the whole words 
are inside matriz. And you have to be SURE that the words you like to write inside the matriz
can be writen in  at least one state, this can be solved simple as counting the whole letters of your words 
an comparing if this number is <= that NxN */

import java.util.Random;
class LetterSoup{
    private char [][] matriz;
    private char [][] matrizAux;  
    private String [] words = { "pez", "oso","ave","boa","lobo","gato"}; //words to write inside the matriz
    private char [] abc = {'a','b','c','d','e','f','g','h',
                    'i','j','k','l','m','n','o','p',
                    'q','r','s','t','u','v','x','w','y','z'};
    private Random r = new Random();
    private int row,col, size, cont, cont2; 

    public LetterSoup (int size)  //creates a matriz of order size*size
    {
        this.size = size;
        this.matriz = new char [this.size][this.size];
        this.matrizAux = new char [this.size][this.size];

        this.cleanMatriz(this.matriz); 
        this.setPosition();
    }

    public void cleanMatriz (char [][] matrizToClean)  
    {
        for (int i = 0; i < size; i++)
            for (int j = 0; j< size; j++)
                matrizToClean[i][j] = '0';
            
    }

    public void cleanVector(char [] vector)
    {
        for (int i = 0; i < vector.length; i++)
            vector [i] = '0';
    }

    public char [][] getMatriz ()
    {
        return this.matriz;
    }

    public char getABC (int i)
    {
        return this.abc[i];
    }

    public void randomABC (char [][] matrizR) //filling in matriz of random letters
    {
        int x;
        for(int i = 0; i<this.size; i++)
            for (int j = 0; j<this.size; j++)
                if(matrizR[i][j] == '0'){
                    x = r.nextInt(26);
                    matrizR [i][j] = getABC(x); 
                }
    }

    public int getOnMatrizAux (int r, int c) //filling in with '1' the position on the matriz that the word can't be on it
    {                                        //and returning the number of the positions that contains '1'
        int counting1 = 0;
        this.matrizAux [r][c] = '1';
        
        for (int i = 0; i<this.size; i++)
            for (int j = 0; j<this.size; j++)
                if (this.matrizAux[i][j] == '1')
                    counting1 ++;
        
        return counting1;
    }

    public void setPosition ()  //this function selects randomly the form for the word can be write on matriz
    {                           //if the word selected can't be wrote in any form inside matriz, the main matriz in cleaned
        int rand, x = 0, i = 0; //and start again until the whole words can be write inside matriz
        boolean flag = true;
        char [] vAux = new char [8];
        String word;
       while (i<words.length)
       {
            word = words[i];
            do
            {
                rand = r.nextInt(8); 
                if(vAux[rand] != '1')
                {
                    switch(rand)
                    {
                    case 0: flag = horizontal(word); vAux [0] = '1'; break;    //this set of functions writes the words 
                    case 1: flag = horizontalI(word); vAux [1] = '1'; break;   //inside matriz as they name indicates
                    case 2: flag = vertical(word); vAux [2] = '1'; break;
                    case 3: flag = verticalI(word); vAux [3] = '1'; break;
                    case 4: flag = diagonalL(word); vAux [4] = '1'; break;
                    case 5: flag = diagonalLI(word); vAux [5] = '1'; break;
                    case 6: flag = diagonalR(word); vAux [6] = '1'; break;
                    case 7: flag = diagonalRI(word); vAux [7] = '1'; break;
                    }
                }
                for(int z = 0; z<vAux.length; z++)
                    if(vAux[z] == '1')
                        x++;

                if (x == 8){
                    cleanMatriz(this.matriz);
                    i=0;
                    break;
                }
            }while(flag);
            i++;
            this.cleanVector(vAux);
       } 
    }

    public boolean horizontal (String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while(condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.col + word.length() )<= this.size)
            {
                this.cont = 0; this.cont2 = 0;
                for (int i = col; i < (word.length()+col); i++)
                {
                    if (this.matriz[this.row][i] == '0' || matriz[this.row][i] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                }
                if(this.cont == word.length()){
                    
                    for(int i = 0; i<word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
                        this.col++;
                    }
                    return false;    
                }
            }
            condicion = getOnMatrizAux(this.row,this.col);
        }
        return true;
    }

    public boolean horizontalI(String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while(condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.col +1) - word.length() >= 0)
            {
                this.cont = 0; this.cont2 = 0;
                int x = col;
                while (x>=((this.col+1)-word.length())){ 
                    if (this.matriz[this.row][x] == '0' || this.matriz[this.row][x] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                    x--;
                }
                if(this.cont == word.length()){
                    for(int i = 0; i<word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
                        this.col--;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row, this.col);
        }
        return true;
    }

    public boolean vertical (String word)
    {   
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if(this.row + word.length() <= this.size)
            {
                this.cont = 0; this.cont2 = 0;
                for (int i = this.row; i < (word.length()+this.row); i++) //;<=;
                {
                    if (this.matriz[i][this.col] == '0' || this.matriz[i][this.col] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                }
                if (this.cont  == word.length()){
                    for(int i = 0; i<word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
                        this.row ++;
                    }
                    return false;
                }
                
            }
            condicion = getOnMatrizAux(this.row,this.col);
        }
        return true;
    }

    public boolean verticalI (String word)
    {   
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.row + 1) - word.length() >= 0)
            {
                this.cont = 0; this.cont2 = 0;
                for (int i = this.row; i >= (this.row + 1) - word.length(); i--) //;<=;
                {
                    if (this.matriz[i][this.col] == '0' || this.matriz[i][this.col] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                }
                if (cont  == word.length()){
                    for(int i = 0; i<word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
                        this.row --;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row,this.col);
        }
        return true;
    }

    public boolean diagonalL(String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.row + 1) - word.length() >= 0 && this.col + word.length() <= this.size)
            {
                this.cont = 0; this.cont2 = 0;
                int x = this.row, j = this.col;
                while(x >= ((this.row + 1) - word.length()) && j <= (this.col + word.length()))
                {   
                    if(this.matriz[x][j] == '0' || this.matriz[x][j] == word.charAt(cont2))
                        this.cont++;

                    this.cont2++;
                    x--; j++;
                }
                if(this.cont == word.length())
                {
                    for(int i = 0; i < word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
                        this.row--; this.col++;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row,this.col);
        }
        return true;
    }

    public boolean diagonalLI(String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if(this.row + word.length() <= this.size && (this.col + 1) - word.length() >= 0)
            {
                this.cont = 0; this.cont2 = 0;
                int x = this.row, j = this.col;
                while(x <= (this.row +  word.length()) && j >= ((this.col+1) - word.length()))
                {   
                    if(this.matriz[x][j] == '0' || this.matriz[x][j] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                    x++; j--;
                }
                if(this.cont == word.length())
                {
                    for(int i = 0; i < word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
                        this.row++; this.col--;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row, this.col);
        }
        return true;
    }

    public boolean diagonalR(String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.row + 1) - word.length() >= 0 && (this.col + 1) - word.length() >= 0)
            {
                cont = 0; cont2 = 0;
                int x = this.row, j = this.col;
                while(x >= ((this.row + 1) -  word.length()) && j >= ((this.col + 1) - word.length()))
                {   
                    if(this.matriz[x][j] == '0' || this.matriz[x][j] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                    x--; j--;
                }
                if(this.cont == word.length())
                {
                    for(int i = 0; i < word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
                        this.row--; this.col--;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row, this.col);
        }
        return true;
    }

    public boolean diagonalRI(String word)
    {  
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.row + word.length()) <= this.size && (this.col + word.length()) <= this.size)
            {
                this.cont = 0; this.cont2 = 0;
                int x = this.row, j = this.col;
                while(x < (this.row + word.length()) && j < (this.col + word.length()))
                {   
                    if(this.matriz[x][j] == '0' || this.matriz[x][j] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                    x++; j++;
                }
                if(this.cont == word.length())
                {
                    for(int i = 0; i < word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
                        this.row++; this.col++;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row, this.col);
        }
        return true;
    }
}
