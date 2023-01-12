/*В первые пишу на java. Хорошо ознакомлен с языками С,С++.Хоть синтаксис и похож(по моему мнению), не привычно
* писать в ООП(привык к прцедурному прогамированию).Надеюсь ужасный вид кода и навернека, не самая оптимальная реализация
* кода не испортит ваше мнение.Хоть в данной задаче и можно было составить словарь от -100 до 100 и провести тупое сравнение
* (для двух чисел это достаточно быстро), хотел показать что имею алгоритмическую базу и умею ее использовать.*/

import java.util.Scanner;

public class Main
{
    public static int romantoint(char input)//реализовываем простой словарь римских чисел
    {
        if(input=='I') return 1;
        else if(input=='V') return 5;
        else if(input=='X') return 10;
        else if(input=='L') return 50;
        else if(input=='C') return 100;
        else if(input=='D') return 500;
        else if(input=='M') return 1000;
        else return 0;
    }
    public static int stringtoint(String input)//перобразовываем строку в числа(подразумивает что в фукцию точно попадет
    {                                          //парвильная строка состаящая из арабских и римских чисел)
        int foo;
        try {
            foo = Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
          int end=input.length();
          char[] arr=input.toCharArray();
          int arabion,result=romantoint(arr[end-1]);
          for (int i=end-2;i>=0;i--)
          {
              arabion=romantoint(arr[i]);
              if(arabion<romantoint(arr[i+1])) result-=arabion;
              else result+=arabion;
          }
          foo=result;


        }
        return foo;
    }
    public static String stringtoroman(String input)//перобразовываем строку из арабских чисел в строку из римских чисел(подразумивает что в фукцию точно попадет
    {                                               //парвильная строка состаящая из арабских и римских чисел)
        int foo = Integer.parseInt(input);
        if(foo<1) return "throws Exception //т.к. в римской системе нет отрицательных чисел";
        int min=foo-1,j=0;
        int[] arr={1,4,5,9,10,40,50,90,100,400,500,900,1000};
        String[] str={"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
        String result="";
        do{
            for(int i=0;i<13;i++)
                if((foo-arr[i]<=min)&&(0<=foo-arr[i])) {min=foo-arr[i]; j=i;}
            result+=str[j];
            foo-=arr[j];
            j=0;
            min=foo-1;
        }while (foo!=0);
        return result;
    }
    public static String sum(int ix,int iy,char operation)//просто выполняет мат.операцию между двумя числами
    {
        if(operation=='+')
        {
            ix+=iy;
            return Integer.toString(ix);
        }
        else if(operation=='-')
        {
            ix-=iy;
            return Integer.toString(ix);
        }
        else if(operation=='*')
        {
            ix*=iy;
            return Integer.toString(ix);
        }
        else if(operation=='/') {
            ix /= iy;
            return Integer.toString(ix);
        }
        return null;
    }
    public static String calc(String input)// требуемый метод(делит строку на два операнда и на оператор, преобразует
    {                                      //в строки в int. Выполням сумирования и возвращаем резултат в зависимост
        String x="",y="";                  // от  типа чисел.
        char operation = 0;
        boolean flag=false;
        for (char s:input.toCharArray())
        {
            if(flag==true) y+=s;
            if((s=='*')||(s=='/')||(s=='+')||(s=='-'))
            {
                if(flag==true)
                {
                    return "throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
                }
                flag=true;
                operation=s;

            }
            if(flag==false) x+=s;
        }
        if(operation==0) return "throws Exception //т.к. строка не является математической операцией";
        if((x=="")||(y=="")) return "throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        int ix=stringtoint(x),iy=stringtoint(y);
        if((ix<1)||(ix>10)||(iy<1)||(iy>10)) return "throws Exception //т.к. операнды должны быть больше 0 и меньше 11";
        if((romantoint(x.toCharArray()[0])==0)&&(romantoint(y.toCharArray()[0])==0)) input=sum(ix,iy,operation);
        else if((romantoint(x.toCharArray()[0])!=0)&&(romantoint(y.toCharArray()[0])!=0)) input=stringtoroman(sum(ix,iy,operation));
        else return "throws Exception //т.к. используются одновременно разные системы счисления";
        return input;
    }
    public static void main(String[] args)
    {
        Scanner Sstring=new Scanner(System.in);
        String str=Sstring.nextLine();
        System.out.println(calc(str));
    }
}