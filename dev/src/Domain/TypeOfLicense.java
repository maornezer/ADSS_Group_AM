package Domain;

public enum TypeOfLicense
{
    B,C,D;

    public int LimitWeight(){
        if ( this == B)
            return 2;
        else if (this== C)
        {
            return 8;
        }
        else
            return 15;
    }
    public static TypeOfLicense WhichTypeOfLicense( double w)
    {
        if (w <= B.LimitWeight())
        {
            return B;
        }
        else if (w <= C.LimitWeight())
        {
            return C;
        }
        else return D;

    }
}
