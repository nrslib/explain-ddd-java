package sample._02_pattern._03_domainService._05;

public class Baggage
{
    private final BaggageId id;

    public Baggage(BaggageId id)
    {
        this.id = id;
    }

    public BaggageId getId() {
        return id;
    }
}