package Domain;

public enum TypeOfLicense
{
    B,C,D;

    public double LimitWeight()
    {
        double noLimit = Double.MAX_VALUE;
        if(this == B)
        {
            return 2000;
        }
        else if (this== C)
        {
            return 10000;
        }
        else
            return noLimit;
    }
    public static TypeOfLicense WhichTypeOfLicense(double w)
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
