public class RailFence
{

    public static void main(String[] args)
    {
        int key = 3;
        String txt = "attack";
        String encrypted, decrypted;

        System.out.println("Plain text: " + txt);

        encrypted = RailFence.encrypt(txt, key);
        System.out.println("Encrypted: " + encrypted);

        decrypted = RailFence.decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }

    public static String encrypt(String txt, int key)
    {
        String res = "";
        int row = key;
        int column = txt.length();
        char[][] matrix = new char[row][column];
        boolean direction = false;
        // false = upward
        // true = downward

        int j = 0;
        for(int i=0; i<column; i++) //matrix visiting in column order and putting the character of plaintext
        {
            if(j==0||j==row-1) // at edge
                direction=!direction;

            matrix[j][i]=txt.charAt(i);

            if(direction)
                j++; // downward
            else
                j--; // upward
        }

        // read based on row
        for(int i=0; i<row; i++)
            for(j=0; j<column; j++)
                if(Character.isLetterOrDigit(matrix[i][j]))
                    res += matrix[i][j];

        return res;
    }

    public static String decrypt(String txt, int key)
    {
        String res = "";
        boolean direction = false;
        int j=0;
        int row = key;
        int column = txt.length();
        char[][] matrix = new char[row][column];

        //first of all mark the rails position by * in the matrix
        for(int i=0;i<column;i++)
        {
            if(j==0||j==row-1)
                direction=!direction;

            matrix[j][i]='*';

            if(direction)
                j++;
            else
                j--;

        }

        //now enter the character of cipheetext in the matrix positon that have * symbol
        int index = 0;

        for(int i=0;i<row;i++)
            for(int k=0;k<column;k++)
                if(matrix[i][k]=='*'&&index<txt.length())
                    matrix[i][k]=txt.charAt(index++);


        direction = false;
        j = 0;

        for(int i=0;i<column;i++)
        {
            if( j==0||j==row-1)
                direction=!direction;


            res += matrix[j][i];


            if(direction)
                j++;
            else
                j--;

        }

        return res;
    }

    public static void print(char[][] matrix)
    {
        System.out.println(matrix[0][0]);
        System.out.print(" ");
        for(int j=0; j<matrix[0].length; j++)
            System.out.print("- ");
        System.out.println();

        for(int i=0; i<matrix.length; i++)
        {
            System.out.print("|");

            for(int j=0; j<matrix[i].length; j++)
            {
                if(Character.isLetterOrDigit(matrix[i][j]))
                    System.out.print(matrix[i][j] + " ");
                else
                    System.out.print("  ");
            }
            System.out.print("|");
            System.out.println();
        }

        System.out.print(" ");
        for(int j=0; j<matrix[0].length; j++)
            System.out.print("- ");
        System.out.println();
    }

}
