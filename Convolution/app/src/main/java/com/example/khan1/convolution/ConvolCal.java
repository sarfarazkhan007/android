package com.example.khan1.convolution;

import android.graphics.Color;
import android.icu.text.StringPrepParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class ConvolCal extends AppCompatActivity
{

    private static Button circlebtn,linearbtn;
    private static EditText xedt1,hedt2,yedt3;


    //was giving error because  it was called before the gui is build or called

    /*circlebtn = (Button) findViewById(R.id.button);
    /*Button circlebtn=(Button) findViewById(R.id.button);
    EditText xedt1 = (EditText) findViewById(R.id.editText);
    EditText hedt2 = (EditText) findViewById(R.id.editText2);
    EditText yedt3 = (EditText) findViewById(R.id.editText3);
    */

    int[] x = new int[50];
    int[] h = new int[15];
    int[] y = new int[100];
    int i;
    int j;
    int count;
    String xsample,hsample;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convol_cal);
        //
        circlebtn = (Button) findViewById(R.id.button);
        linearbtn= (Button) findViewById(R.id.linearbtn);
        xedt1 = (EditText) findViewById(R.id.editText);
        hedt2 = (EditText) findViewById(R.id.editText2);
        yedt3 = (EditText) findViewById(R.id.editText3);
        xedt1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                xedt1.setText("");
                hedt2.setText("");
                yedt3.setText("");
            }
        }
        );

        hedt2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hedt2.setText("");
                yedt3.setText("");
            }
        }
        );
        circularMatrix();
    }


    public void circularMatrix()
    {
        xedt1.setOnClickListener(new View.OnClickListener()
                                 {
                                     @Override
                                     public void onClick(View v)
                                     {
             xedt1.setText("");
             yedt3.setText("");
         }
                                 }
        );

        hedt2.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v)
                                     {
                     hedt2.setText("");
                     yedt3.setText("");
                 }
                                 }
        );


        circlebtn.setOnClickListener(new View.OnClickListener()
        {

             @Override
             public void onClick(View v)
             {
                 xhlength();

                 if (i != j) {
                     if (i < j) {
                         for (int start = i; start < j; start++) {
                             x[start] = 0;
                         }
                         i = j;
                     } else {
                         for (int start = j; start < i; start++) {
                             h[start] = 0;
                         }
                         j = i;
                     }
                 }
                 displayOutput();
                 yedt3.setTextColor(Color.parseColor("#ff669900"));
             }


        }
        );



        linearbtn.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View v)
             {
                 xhlength();


                 int padd = i+j-1;
                // System.out.println("length to be padd :- " + padd);


                 // For padding 0's

                 if(j < padd) //padding 0's
                 {
                     for (int start = j ; start < padd ; start++)
                     {
                         h[start] = 0;
                     }
                     j=padd;
                 }


                 if(i < padd) //padding 0's
                 {
                     for (int start = i; start < padd; start++) {
                         x[start] = 0;
                     }
                     i = padd;
                 }

                 displayOutput();
                 yedt3.setTextColor(Color.parseColor("#3f51b5"));

             }


         }
        );
    }

    public void xhlength()
    {
        yedt3.setText("");

        Arrays.fill(x, 0);
        Arrays.fill(y, 0);
        Arrays.fill(h, 0);
        i = 0;
        j = 0;
        count = 0;
        try
        {

            xsample = xedt1.getText().toString();
            for (String w : xsample.split("\\s"))
            {
                x[i++] = Integer.parseInt(w);
            }
        }
        catch(NumberFormatException e)
        {
            Toast.makeText(ConvolCal.this, " Input can only be numbers in x(n)" ,Toast.LENGTH_SHORT).show();
            Arrays.fill(x, 0);
            Arrays.fill(y, 0);
            //Arrays.fill(h, 0);
            xedt1.setText("");
            yedt3.setText("");
        }



        try
        {
            hsample = hedt2.getText().toString();
            for (String w : hsample.split("\\s"))
            {
                h[j++] = Integer.parseInt(w);
            }
        }
        catch(NumberFormatException e)
        {
            Toast.makeText(ConvolCal.this, " Input can only be numbers in h(n)" ,Toast.LENGTH_SHORT).show();
            Arrays.fill(y, 0);
            Arrays.fill(h, 0);
            hedt2.setText("");
            yedt3.setText("");
            return;
        }


    }

    public void displayOutput()
    {
        int[][] xmatrix = new int[i][j];
        //Arrays.fill(xmatrix, 0);
        for (int row = 0; row < i; row++)
        {
            for (int col = 0; col < i; col++)
            {
                if (i <= count)
                {
                    count = 0;
                }
                else
                {
                    xmatrix[col][row] = x[count++];
                    if (i == count || count > i)
                    {
                        count = 0;
                    }
                }
            }
            count = i - row - 1;
        }

        for (int row = 0; row < i; row++)
        {
            for (int col = 0; col < i; col++)
            {
                y[row] += xmatrix[row][col] * h[col];
            }
        }

        answer = "";
        answer = "{ ";
        for (int row = 0; row < i; row++)
        {
            // System.out.print(y[row]+" ");
            answer += y[row] + " ";
        }
        //System.out.print("}");
        answer += "}";

        yedt3.setText(answer);

    }


}
