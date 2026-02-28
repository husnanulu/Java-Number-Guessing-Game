package sayitahminoyunu;
//oyun içi süreyi takip eder
class Countdown {
    private int totalSeconds;//toplam süre(saniye)
    private long startMillis;//başlangıç zamanı(milisaniye)
    private boolean started;
    public Countdown(int totalSeconds) {
        this.totalSeconds = totalSeconds;
        this.started = false;
    }
    public void start() {
        this.startMillis = System.currentTimeMillis();
        this.started = true;
    }
    public int remainingSeconds() {
        if (!started) return totalSeconds;//başlamadıysa tüm süre var
        long elapsedMillis = System.currentTimeMillis() - startMillis;
        //geçen süreyi hesaplar
        long elapsedSeconds = elapsedMillis / 1000;//milisaniyeyi saniyeye çevir
        int remaining = totalSeconds - (int)elapsedSeconds;
        return remaining;
    }
    //süre dolup dolmadığını kontrol eder
    public boolean isExpired() {
        return remainingSeconds() <= 0;
    }
}
