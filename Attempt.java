package sayitahminoyunu;
//her tahminin kaydı tutulur
class Attempt {
    //enum-tahmin sonucunu temsil eder
    public enum Result {
        HIGH,//tahmin hedeften büyük
        LOW,//tahmin hedeften küçük
        CORRECT//tahmin doğru
    }
    private int guessValue;//tahmin edilen sayı
    private Result result;//tahmin sonucu: HIGH/LOW/CORRECT
    private int attemptNo;//deneme numarası
    private long timestampMillis;//tahmin zamanı(milisaniye)
    public Attempt(int guessValue, Result result, int attemptNo) {
        this.guessValue = guessValue;
        this.result = result;
        this.attemptNo = attemptNo;
        this.timestampMillis = System.currentTimeMillis();//şu anki zamanı kaydeder
    }
    //getter metotları
    public int getGuessValue() { return guessValue; }
    public Result getResult() { return result; }
    public int getAttemptNo() { return attemptNo; }
    public long getTimestampMillis() { return timestampMillis; }
    @Override
    public String toString() {
        return "Attempt " + attemptNo + ": guessed " + guessValue + " → " + result;
    }
}